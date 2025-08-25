package com.jobportal.Job.Portal.Service;

import com.jobportal.Job.Portal.DTO.LoginDTO;
import com.jobportal.Job.Portal.DTO.NotificationDTO;
import com.jobportal.Job.Portal.DTO.ResponseDTO;
import com.jobportal.Job.Portal.DTO.UserDTO;
import com.jobportal.Job.Portal.Entity.OTP;
import com.jobportal.Job.Portal.Entity.User;
import com.jobportal.Job.Portal.Exception.JobPortalException;
import com.jobportal.Job.Portal.Repository.NotificationRepository;
import com.jobportal.Job.Portal.Repository.OTPRepository;
import com.jobportal.Job.Portal.Repository.UserRepository;
import com.jobportal.Job.Portal.Utility.Data;
import com.jobportal.Job.Portal.Utility.Utilities;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImplements implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private NotificationService notificationService;

    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        Optional<User>  optional=userRepository.findByEmail(userDTO.getEmail());
        if(optional.isPresent()) throw new JobPortalException("USER_FOUND");

        userDTO.setProfileId(profileService.createProfile(userDTO.getEmail()));
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = userDTO.toEntity();
        userRepository.save(user);

        return userDTO;   // ✅ Ab profileId frontend ko milega
    }


    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user= userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(loginDTO.getPassword(),user.getPassword()))throw new JobPortalException("INVALID_CREDENTIALS");
        return user.toDTO();
    }


    @Override
    public Boolean sendOtp(String email) throws Exception {
        User user =userRepository.findByEmail(email).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mm= javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm,true);
        message.setTo(email);
        message.setSubject("Your OTP code");
        String genOtp=Utilities.generateOTP();
        OTP otp =new OTP(email,genOtp, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText(Data.getMessageBody(genOtp,user.getName()),true);
        javaMailSender.send(mm);
        return true;
    }

    @Override
    public Boolean verifyOtp(String email,String otp) throws JobPortalException {
        OTP otpEntity =otpRepository.findById(email).orElseThrow(()->new JobPortalException("OTP_NOT_FOUND"));
        if (!otpEntity.getOtpCode().equals(otp))throw new JobPortalException("OTP_INCORRECT");
        return true;
    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        User user =userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        NotificationDTO noti = new NotificationDTO();
        noti.setUserId(user.getId());
        noti.setMessage("Password Reset Successful");
        noti.setAction("Password Reset");
        notificationService.sendNotification(noti);
        return new ResponseDTO("Password changed successfully.");
    }

    @Scheduled(fixedRate = 60000)
    public void removeExpiredOTPs(){
        LocalDateTime expiry = LocalDateTime.now().minusMinutes(5);
        List<OTP>expiredOTPs =otpRepository.findByCreationTimeBefore(expiry);
        if(!expiredOTPs.isEmpty()){
            otpRepository.deleteAll(expiredOTPs);
            System.out.println("Removed "+expiredOTPs.size()+ "expired OTPs.");
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) throws JobPortalException {
        return userRepository.findByEmail(email).orElseThrow(()->new JobPortalException("USER_NOT_FOUND")).toDTO();
    }

}
