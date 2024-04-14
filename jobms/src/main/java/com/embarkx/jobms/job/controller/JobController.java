package com.embarkx.jobms.job.controller;

import com.embarkx.jobms.job.model.Job;
import com.embarkx.jobms.job.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        String res = jobService.createJob(job);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable("id") Long id) {
        Job job = jobService.getJobById(id);
        if (job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable("id") Long id) {
        if(jobService.deleteJob(id))
            return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);

        return new ResponseEntity("failure in deletion",HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable("id") Long id,@RequestBody Job job) {
        boolean res = jobService.updateJob(id,job);
        if(res)
            return new ResponseEntity<>("Successfully Updated",HttpStatus.OK);
        return new ResponseEntity("failure in updation",HttpStatus.NOT_FOUND);
    }

}
