package com.embarkx.jobms.mapper;

import com.embarkx.jobms.dto.JobDTO;
import com.embarkx.jobms.model.Company;
import com.embarkx.jobms.model.Job;
import com.embarkx.jobms.model.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO jobWithCompanyDTO(Job job, Company company, List<Review> review){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(jobDTO.getMinSalary());
        jobDTO.setMaxSalary(jobDTO.getMaxSalary());
        jobDTO.setLocation(jobDTO.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReview(review);
        return jobDTO;
    }
}
