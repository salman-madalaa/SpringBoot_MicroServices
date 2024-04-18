package com.emabarkx.reviewms.controller;


import com.emabarkx.reviewms.model.Review;
import com.emabarkx.reviewms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAll(@RequestParam Long companyId) {
        List<Review> allReviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(allReviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam Long companyId, @RequestBody Review review) {
        boolean res = reviewService.CreateReview(companyId, review);
        if (res) {
            return new ResponseEntity<String>("Review Saved Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Review Saved Failure", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> update(@PathVariable Long reviewId, @RequestBody Review review) {
        boolean res = reviewService.updateReview(reviewId, review);
        if (res) {
            return new ResponseEntity<>("Review  updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review update failure", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable Long reviewId) {
        boolean res = reviewService.deleteReview(reviewId);
        if (res) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review deleted failure", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
