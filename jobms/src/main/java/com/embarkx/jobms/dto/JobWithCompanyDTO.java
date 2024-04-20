package com.embarkx.jobms.dto;

import com.embarkx.jobms.model.Company;
import com.embarkx.jobms.model.Job;
import lombok.Data;

@Data
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
