package com.emabarkx.reviewms.service;

import com.emabarkx.reviewms.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean CreateReview(Long companyId,Review review);
    Review getReviewById(Long id);
    boolean deleteReview(Long id);
    boolean updateReview(Long reviewId, Review review);
}
