package com.embarkx.jobms.service;

import com.embarkx.jobms.dto.JobDTO;
import com.embarkx.jobms.model.Job;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    String createJob(Job job);
    JobDTO getJobById(Long id);
    boolean deleteJob(Long id);
    boolean updateJob(Long id, Job job);
}
