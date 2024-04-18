package com.embarkx.jobms.service;


import com.embarkx.jobms.dto.JobWithCompanyDTO;
import com.embarkx.jobms.model.Company;
import com.embarkx.jobms.model.Job;
import com.embarkx.jobms.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JonServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<JobWithCompanyDTO> findAll() {
        ArrayList<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();

        List<Job> jobs = jobRepository.findAll();

//        jobs.stream().forEach(job -> {
//            jobWithCompanyDTOS.add(convertToDTO(job));
//        });

//        for (Job job:jobs){
//            jobWithCompanyDTOS.add(convertToDTO(job));
//        }

//        return jobWithCompanyDTOS;

        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    private JobWithCompanyDTO convertToDTO(Job job) {
        Company company = restTemplate.getForEntity("http://COMPANY-MS:8081/companies/" + job.getCompanyId(), Company.class).getBody();
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
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
