package com.emabarkx.reviewms.service;

import com.emabarkx.reviewms.model.Review;
import com.emabarkx.reviewms.repo.ReviewRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;

    public ReviewServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public boolean CreateReview(Long companyId, Review review) {
        try {
            if (companyId != null) {
                review.setCompanyId(companyId);
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
    public Review getReviewById(Long id) {
       return reviewRepo.findById(id).orElse(null);
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        boolean exists = reviewRepo.existsById(reviewId);
        if (exists) {
            reviewRepo.deleteById(reviewId);
            return true;
        } else {
            return false;
        }

    }


    @Override
    public boolean updateReview(Long reviewId, Review updateReview) {
        Review review = reviewRepo.findById(reviewId).orElse(null);
        if (review != null) {
            review.setTitle(updateReview.getTitle());
            review.setDescription(updateReview.getDescription());
            review.setRating(updateReview.getRating());
            review.setCompanyId(updateReview.getCompanyId());
            reviewRepo.save(review);
            return true;
        }
        return false;
    }
}
