package com.jobportal.Job.Portal.Repository;

import com.jobportal.Job.Portal.Entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, Long> {
    List<Job> findByPostedBy(Long postedBy);
}
