package com.embarkx.jobms.service;

import com.embarkx.jobms.dto.JobWithCompanyDTO;
import com.embarkx.jobms.model.Job;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    String createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJob(Long id);
    boolean updateJob(Long id, Job job);
}
