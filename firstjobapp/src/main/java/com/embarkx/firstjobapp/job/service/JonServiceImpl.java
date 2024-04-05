package com.embarkx.firstjobapp.job.service;

import com.embarkx.firstjobapp.job.model.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JonServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;


    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public String createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
        return "job added successfully";
    }

    @Override
    public Job getJobById(Long id) {

        for (Job job : jobs) {
            if (job.getId() == id)
                return job;
        }

        return null;
    }

    @Override
    public boolean deleteJob(Long id) {
//        for (Job job : jobs) {
//            if (job.getId() == id)
//                if(jobs.remove(job))
//                    return true;
//        }
//        return false;

        // another way --

        Iterator<Job> jobIterator = jobs.iterator();

        while (jobIterator.hasNext()) {
            Job job = jobIterator.next();
            if (job.getId() == id)
                jobIterator.remove();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job jobObj) {

        Iterator<Job> jobIterator = jobs.iterator();

        while (jobIterator.hasNext()) {
            Job job = jobIterator.next();
            if (job.getId() == id) {
                job.setTitle(jobObj.getTitle());
                job.setDescription(jobObj.getDescription());
                job.setMinSalary(jobObj.getMinSalary());
                job.setMaxSalary(jobObj.getMaxSalary());
                job.setLocation(jobObj.getLocation());
                return true;
            }
        }
        return false;

    }
}
