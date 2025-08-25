package com.jobportal.Job.Portal.JWT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class AuthenticationResponse {
    private final String jwt;
    private Long userId;
    private Long profileId;
}
