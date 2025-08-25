package com.jobportal.Job.Portal.Entity;

import com.jobportal.Job.Portal.DTO.ApplicantDTO;
import com.jobportal.Job.Portal.DTO.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {
    private Long applicantId;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private String resume;
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;

    public ApplicantDTO toDTO() {
        return new ApplicantDTO(this.applicantId,this.name,this.email,this.phone,this.website,this.resume ,
                this.coverLetter,this.timestamp,this.applicationStatus,this.interviewTime);
    }
}
