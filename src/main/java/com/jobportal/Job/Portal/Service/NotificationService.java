package com.jobportal.Job.Portal.Service;


import com.jobportal.Job.Portal.DTO.NotificationDTO;
import com.jobportal.Job.Portal.Entity.Notification;
import com.jobportal.Job.Portal.Exception.JobPortalException;

import java.util.List;

public interface NotificationService {
    public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException;
    public List<Notification> getUnreadNotification(Long userId);
    public void readNotification(Long id) throws JobPortalException;
}
