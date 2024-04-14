package com.embarkx.jobms.job.service;


import com.embarkx.jobms.job.model.Job;
import com.embarkx.jobms.job.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JonServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public String createJob(Job job) {
        jobRepository.save(job);
        return "job added successfully";
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job jobObj) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(jobObj.getTitle());
            job.setDescription(jobObj.getDescription());
            job.setMinSalary(jobObj.getMinSalary());
            job.setMaxSalary(jobObj.getMaxSalary());
            job.setLocation(jobObj.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
