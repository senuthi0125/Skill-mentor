package com.stemlink.skillmentor.dto.response;

import lombok.Data;
import java.util.Date;

@Data
public class AdminSessionResponseDTO {

    private Integer id;
    private Long studentId;
    private Long mentorId;
    private Long subjectId;
    private String studentName;
    private String studentEmail;
    private String mentorName;
    private String subjectName;
    private Date sessionAt;
    private Integer durationMinutes;
    private String sessionStatus;
    private String paymentStatus;
    private String meetingLink;
    private String sessionNotes;
    private String studentReview;
    private Integer studentRating;
    private Date createdAt;
}