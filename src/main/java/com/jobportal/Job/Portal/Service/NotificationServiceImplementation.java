package com.jobportal.Job.Portal.Service;

import com.jobportal.Job.Portal.DTO.NotificationDTO;
import com.jobportal.Job.Portal.DTO.NotificationStatus;
import com.jobportal.Job.Portal.Entity.Notification;
import com.jobportal.Job.Portal.Exception.JobPortalException;
import com.jobportal.Job.Portal.Repository.NotificationRepository;
import com.jobportal.Job.Portal.Utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImplementation implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException {
        notificationDTO.setId(Utilities.getNextSequence("notification"));
        notificationDTO.setStatus(NotificationStatus.UNREAD);
        notificationDTO.setTimeStamp(LocalDateTime.now());
        notificationRepository.save(notificationDTO.toEntity());
    }

    @Override
    public List<Notification> getUnreadNotification(Long userId) {
       return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    @Override
    public void readNotification(Long id) throws JobPortalException {
        Notification noti =notificationRepository.findById(id).orElseThrow(()->new JobPortalException("No Notification Found"));
        noti.setStatus(NotificationStatus.READ);
        notificationRepository.save(noti);
    }
}
