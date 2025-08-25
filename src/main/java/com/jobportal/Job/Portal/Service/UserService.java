package com.jobportal.Job.Portal.Service;

import com.jobportal.Job.Portal.DTO.LoginDTO;
import com.jobportal.Job.Portal.DTO.ResponseDTO;
import com.jobportal.Job.Portal.DTO.UserDTO;
import com.jobportal.Job.Portal.Exception.JobPortalException;
import jakarta.validation.Valid;

public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;

     public UserDTO loginUser( LoginDTO loginDTO)throws JobPortalException;

     public UserDTO getUserByEmail(String email) throws JobPortalException;

      public Boolean sendOtp(String email) throws  Exception;

      public Boolean verifyOtp(String email,String otp) throws JobPortalException;

      public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException;
}
