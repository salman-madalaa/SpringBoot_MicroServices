package com.embarkx.jobms.job.dto;

import com.embarkx.jobms.job.model.Company;
import com.embarkx.jobms.job.model.Job;
import lombok.Data;

@Data
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
