package com.emabarkx.reviewms.repo;

import com.emabarkx.reviewms.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {
        List<Review> findByCompanyId(Long companyId);

        Review findByIdAndCompanyId(Long reviewId,Long companyId);
}
