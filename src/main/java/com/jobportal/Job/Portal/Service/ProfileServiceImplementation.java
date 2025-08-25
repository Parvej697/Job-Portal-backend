package com.jobportal.Job.Portal.Service;

import com.jobportal.Job.Portal.DTO.ProfileDTO;
import com.jobportal.Job.Portal.Entity.Profile;
import com.jobportal.Job.Portal.Exception.JobPortalException;
import com.jobportal.Job.Portal.Repository.ProfileRepository;
import com.jobportal.Job.Portal.Utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("profileService")
public class ProfileServiceImplementation implements ProfileService{

    @Autowired
   private ProfileRepository profileRepository;


    @Override
    public Long createProfile(String email ) throws JobPortalException {
        Profile profile = new Profile();
        profile.setId((Utilities.getNextSequence("profiles")));
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();

    }

    @Override
    public ProfileDTO getProfile(Long id) throws JobPortalException {
        return profileRepository.findById(id).orElseThrow(()->new JobPortalException("PROFILE_NOT_FOUND")).toDTO();
    }
    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException {
        if (profileDTO.getId() == null) {
            throw new JobPortalException("PROFILE_ID_MISSING");
        }
        Profile updated = profileRepository.save(profileDTO.toEntity());
        return updated.toDTO();
    }


    @Override
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll().stream().map((x)->x.toDTO()).toList();
    }
}

