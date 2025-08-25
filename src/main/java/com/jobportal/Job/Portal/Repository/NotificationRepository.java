package com.jobportal.Job.Portal.Repository;

import com.jobportal.Job.Portal.DTO.NotificationStatus;
import com.jobportal.Job.Portal.Entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,Long> {
    public List<Notification> findByUserIdAndStatus(Long userId, NotificationStatus status);
}
