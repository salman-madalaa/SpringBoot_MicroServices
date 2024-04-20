package com.embarkx.jobms.repo;

import com.embarkx.jobms.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job,Long> {
}
