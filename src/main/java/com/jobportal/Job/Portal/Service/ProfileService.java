package com.jobportal.Job.Portal.Service;

import com.jobportal.Job.Portal.DTO.ProfileDTO;
import com.jobportal.Job.Portal.Exception.JobPortalException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileService  {
    public Long createProfile(String email) throws JobPortalException;
    public ProfileDTO getProfile(Long id) throws JobPortalException;
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;

     public List<ProfileDTO> getAllProfiles();
}
