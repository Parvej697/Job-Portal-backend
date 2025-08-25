package com.jobportal.Job.Portal.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccountType {
    @JsonProperty("APPLICANT")
    APPLICANT,
    @JsonProperty("EMPLOYER")
    EMPLOYER
}
