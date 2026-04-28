package com.stemlink.skillmentor.services.impl;

import com.stemlink.skillmentor.dto.response.AdminSessionResponseDTO;
import com.stemlink.skillmentor.entities.Session;
import com.stemlink.skillmentor.exceptions.SkillMentorException;
import com.stemlink.skillmentor.respositories.SessionRepository;
import com.stemlink.skillmentor.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final SessionRepository sessionRepository;

    @Override
    public List<AdminSessionResponseDTO> getAllSessionsForAdmin() {
        return sessionRepository.findAll()
                .stream()
                .map(this::toAdminDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminSessionResponseDTO confirmPayment(Long sessionId) {
        Session session = findSessionOrThrow(sessionId);
        if (!"pending".equalsIgnoreCase(session.getPaymentStatus())) {
            throw new SkillMentorException(
                    "Cannot confirm payment: current status is '" + session.getPaymentStatus() + "'",
                    HttpStatus.BAD_REQUEST
            );
        }
        session.setPaymentStatus("confirmed");
        session.setSessionStatus("confirmed");
        return toAdminDTO(sessionRepository.save(session));
    }

    @Override
    public AdminSessionResponseDTO markSessionComplete(Long sessionId) {
        Session session = findSessionOrThrow(sessionId);
        if (!"confirmed".equalsIgnoreCase(session.getSessionStatus())) {
            throw new SkillMentorException(
                    "Cannot complete session: current status is '" + session.getSessionStatus() + "'",
                    HttpStatus.BAD_REQUEST
            );
        }
        session.setSessionStatus("completed");
        session.setPaymentStatus("completed");
        return toAdminDTO(sessionRepository.save(session));
    }

    @Override
    public AdminSessionResponseDTO setMeetingLink(Long sessionId, String meetingLink) {
        Session session = findSessionOrThrow(sessionId);
        session.setMeetingLink(meetingLink);
        return toAdminDTO(sessionRepository.save(session));
    }

    private Session findSessionOrThrow(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new SkillMentorException(
                        "Session not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    private AdminSessionResponseDTO toAdminDTO(Session session) {
        AdminSessionResponseDTO dto = new AdminSessionResponseDTO();
        dto.setId(session.getId());
        if (session.getStudent() != null) {
            dto.setStudentName(session.getStudent().getFirstName() + " " + session.getStudent().getLastName());
            dto.setStudentEmail(session.getStudent().getEmail());
        }
        if (session.getMentor() != null) {
            dto.setMentorName(session.getMentor().getFirstName() + " " + session.getMentor().getLastName());
        }
        if (session.getSubject() != null) {
            dto.setSubjectName(session.getSubject().getSubjectName());
        }
        dto.setSessionAt(session.getSessionAt());
        dto.setDurationMinutes(session.getDurationMinutes());
        dto.setSessionStatus(session.getSessionStatus());
        dto.setPaymentStatus(session.getPaymentStatus());
        dto.setMeetingLink(session.getMeetingLink());
        dto.setCreatedAt(session.getCreatedAt());
        return dto;
    }
}