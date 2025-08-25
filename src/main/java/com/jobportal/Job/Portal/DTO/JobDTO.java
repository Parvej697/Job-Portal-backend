package com.jobportal.Job.Portal.DTO;

import com.jobportal.Job.Portal.Entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {

        @Id
        private Long id;   // Long id (custom sequence)
        private String jobTitle;
        private String company;
        private List<ApplicantDTO> applicants;
        private String about;
        private String experience;
        private String jobType;
        private String location;
        private long packageOffered;
        private LocalDateTime postTime;
        private String description;
        private List<String> skillsRequired;
        private JobStatus jobStatus;
        private Long postedBy;

        public Job toEntity() {
                return new Job(
                        this.id,
                        this.jobTitle,
                        this.company,
                        this.applicants != null ? this.applicants.stream().map(ApplicantDTO::toEntity).toList() : null,
                        this.about,
                        this.experience,
                        this.jobType,
                        this.location,
                        this.packageOffered,
                        this.postTime,
                        this.description,
                        this.skillsRequired,
                        this.jobStatus,
                        this.postedBy
                );
        }
}
