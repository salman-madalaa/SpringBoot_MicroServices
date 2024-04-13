package com.embarkx.firstjobapp.review.service;

import com.embarkx.firstjobapp.review.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean CreateReview(Long companyId,Review review);
    Review getReviewById(Long companyId,Long id);
    boolean deleteReview(Long companyId,Long id);
    boolean updateReview(Long companyId,Long reviewId, Review review);
}
