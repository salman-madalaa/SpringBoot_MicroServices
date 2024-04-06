package com.embarkx.firstjobapp.job.repo;

import com.embarkx.firstjobapp.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
}
