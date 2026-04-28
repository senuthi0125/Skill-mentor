package com.stemlink.skillmentor.services;

import com.stemlink.skillmentor.dto.response.AdminSessionResponseDTO;
import java.util.List;

public interface AdminService {
    List<AdminSessionResponseDTO> getAllSessionsForAdmin();
    AdminSessionResponseDTO confirmPayment(Long sessionId);
    AdminSessionResponseDTO markSessionComplete(Long sessionId);
    AdminSessionResponseDTO setMeetingLink(Long sessionId, String meetingLink);
}