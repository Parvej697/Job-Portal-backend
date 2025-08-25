package com.jobportal.Job.Portal.Service;

import com.jobportal.Job.Portal.DTO.ApplicantDTO;
import com.jobportal.Job.Portal.DTO.Application;
import com.jobportal.Job.Portal.DTO.JobDTO;
import com.jobportal.Job.Portal.Exception.JobPortalException;


import java.util.List;

public interface JobService {
     public JobDTO postJob( JobDTO jobDTO) throws  JobPortalException ;

     public List<JobDTO> getAllJobs();

     public JobDTO getJob(Long id) throws JobPortalException;

     public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException;

     public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalException ;

    public  void changeAppStatus(Application application) throws JobPortalException;
}
