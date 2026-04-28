package com.stemlink.skillmentor.controllers;

import com.stemlink.skillmentor.dto.response.AdminSessionResponseDTO;
import com.stemlink.skillmentor.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasRole('ADMIN')")
public class AdminController extends AbstractController {

    private final AdminService adminService;

    @GetMapping("/sessions")
    public ResponseEntity<List<AdminSessionResponseDTO>> getAllSessions() {
        return sendOkResponse(adminService.getAllSessionsForAdmin());
    }

    @PatchMapping("/sessions/{id}/confirm-payment")
    public ResponseEntity<AdminSessionResponseDTO> confirmPayment(@PathVariable Long id) {
        return sendOkResponse(adminService.confirmPayment(id));
    }

    @PatchMapping("/sessions/{id}/complete")
    public ResponseEntity<AdminSessionResponseDTO> markComplete(@PathVariable Long id) {
        return sendOkResponse(adminService.markSessionComplete(id));
    }

    @PatchMapping("/sessions/{id}/meeting-link")
    public ResponseEntity<AdminSessionResponseDTO> setMeetingLink(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return sendOkResponse(adminService.setMeetingLink(id, body.get("meetingLink")));
    }
}