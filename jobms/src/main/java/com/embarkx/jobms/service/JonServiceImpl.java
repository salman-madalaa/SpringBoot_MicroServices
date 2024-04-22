package com.embarkx.jobms.service;


import com.embarkx.jobms.dto.JobDTO;
import com.embarkx.jobms.mapper.JobMapper;
import com.embarkx.jobms.model.Company;
import com.embarkx.jobms.model.Job;
import com.embarkx.jobms.model.Review;
import com.embarkx.jobms.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    public List<JobDTO> findAll() {
        ArrayList<JobDTO> jobDTOS = new ArrayList<>();

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


    private JobDTO convertToDTO(Job job) {
        Company company = restTemplate.getForEntity("http://COMPANY-MS/companies/" + job.getCompanyId(), Company.class).getBody();
        ResponseEntity<List<Review>> reviews = restTemplate.exchange("http://REVIEW-MS/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {});
        return JobMapper.jobWithCompanyDTO(job,company,reviews.getBody());
    }


    @Override
    public String createJob(Job job) {
        jobRepository.save(job);
        return "job added successfully";
    }

    @Override
    public JobDTO getJobById(Long id) {
       Job job= jobRepository.findById(id).orElse(null);
        return convertToDTO(job);
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
