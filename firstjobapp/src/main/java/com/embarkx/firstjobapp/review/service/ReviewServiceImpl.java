package com.embarkx.firstjobapp.review.service;

import com.embarkx.firstjobapp.companie.model.Company;
import com.embarkx.firstjobapp.companie.service.CompanyService;
import com.embarkx.firstjobapp.job.model.Job;
import com.embarkx.firstjobapp.review.model.Review;
import com.embarkx.firstjobapp.review.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final CompanyService companyService;
    private final ReviewRepo reviewRepo;

    public ReviewServiceImpl(CompanyService companyService, ReviewRepo reviewRepo) {
        this.companyService = companyService;
        this.reviewRepo = reviewRepo;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public boolean CreateReview(Long companyId, Review review) {
        try {
            Company company = companyService.getById(companyId);
            if (company != null) {
                review.setCompany(company);
                reviewRepo.save(review);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long id) {
        List<Review> reviews = reviewRepo.findByCompanyId(companyId);
       return reviews.stream().filter(review -> review.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean deleteReview(Long companyId,Long reviewId) {
        Company company = companyService.getById(companyId);
        boolean exists = reviewRepo.existsById(reviewId);
        if (company != null && exists) {
//            Review review = reviewRepo.findById(reviewId).orElse(null);
//            company.getReviews().remove(review);
//            companyService.update(companyId,company);
            reviewRepo.deleteById(reviewId);
            return true;
        } else {
            return false;
        }

    }


    @Override
    public boolean updateReview(Long companyId,Long reviewId, Review review) {
        Company company = companyService.getById(companyId);
        if (company != null) {
            Review review1 = reviewRepo.findByIdAndCompanyId(reviewId,companyId);
            review.setCompany(company);
            review.setId(reviewId);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }
}
