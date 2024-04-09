package com.embarkx.firstjobapp.job.service;

import com.embarkx.firstjobapp.job.model.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    String createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJob(Long id);
    boolean updateJob(Long id, Job job);
}
