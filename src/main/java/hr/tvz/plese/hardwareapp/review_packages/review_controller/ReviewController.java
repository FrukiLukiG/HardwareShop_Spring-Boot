package hr.tvz.plese.hardwareapp.review_packages.review_controller;

import hr.tvz.plese.hardwareapp.review_packages.review_classes.ReviewDTO;
import hr.tvz.plese.hardwareapp.review_packages.review_service.ReviewService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @GetMapping(params = "hardwareCode")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<ReviewDTO> getReviewsByHardwareCode(@RequestParam final String hardwareCode){
        return reviewService.findAllByHardwareCode(hardwareCode);
    }
}
