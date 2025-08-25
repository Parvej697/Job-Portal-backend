package com.jobportal.Job.Portal.Repository;

import com.jobportal.Job.Portal.Entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile,Long> {

}
