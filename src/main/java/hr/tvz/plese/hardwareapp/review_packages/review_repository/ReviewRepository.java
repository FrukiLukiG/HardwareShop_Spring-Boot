package hr.tvz.plese.hardwareapp.review_packages.review_repository;

import hr.tvz.plese.hardwareapp.review_packages.review_classes.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllBy();

    List<Review> findAllByHardwareToReview_Code(String code);
}
