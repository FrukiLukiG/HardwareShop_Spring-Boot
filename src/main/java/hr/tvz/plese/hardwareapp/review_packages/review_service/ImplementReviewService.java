package hr.tvz.plese.hardwareapp.review_packages.review_service;

import hr.tvz.plese.hardwareapp.review_packages.review_classes.Review;
import hr.tvz.plese.hardwareapp.review_packages.review_classes.ReviewDTO;
import hr.tvz.plese.hardwareapp.review_packages.review_repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementReviewService implements ReviewService{

    private final ReviewRepository reviewRepository;

    public ImplementReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAllBy().stream().map(this::mapReviewDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByHardwareCode(final String code) {
        return reviewRepository.findAllByHardwareToReview_Code(code).stream().map(this::mapReviewDTO).collect(Collectors.toList());
    }

    private ReviewDTO mapReviewDTO(final Review review){
        return new ReviewDTO(review.getTitle(), review.getText(), mapGrade(review.getGrade().toString()));
    }

    private Integer mapGrade(final String grade){
        return switch (grade) {
            case "ONE" -> 1;
            case "TWO" -> 2;
            case "THREE" -> 3;
            case "FOUR" -> 4;
            case "FIVE" -> 5;
            default -> null;
        };
    }

}
