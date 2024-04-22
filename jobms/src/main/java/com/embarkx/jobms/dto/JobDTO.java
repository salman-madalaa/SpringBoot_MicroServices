package com.embarkx.jobms.dto;

import com.embarkx.jobms.model.Company;
import com.embarkx.jobms.model.Review;
import lombok.Data;

import java.util.List;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;
    private Company company;
    private List<Review> review;
}
