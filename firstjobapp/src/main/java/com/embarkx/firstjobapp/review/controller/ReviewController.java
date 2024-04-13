package com.embarkx.firstjobapp.review.controller;

import com.embarkx.firstjobapp.companie.model.Company;
import com.embarkx.firstjobapp.review.model.Review;
import com.embarkx.firstjobapp.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAll(@PathVariable Long companyId) {
        List<Review> allReviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(allReviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@PathVariable Long companyId, @RequestBody Review review) {
        boolean res = reviewService.CreateReview(companyId, review);
        if (res) {
            return new ResponseEntity<String>("Review Saved Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Review Saved Failure", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> update(@PathVariable Long reviewId, @RequestBody Review review, @PathVariable Long companyId) {
        boolean res = reviewService.updateReview(companyId,reviewId, review);
        if (res) {
            return new ResponseEntity<>("Review  updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review update failure", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable Long companyId,@PathVariable Long reviewId) {
        boolean res = reviewService.deleteReview(companyId,reviewId);
        if (res) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review deleted failure", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId,reviewId);
        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
