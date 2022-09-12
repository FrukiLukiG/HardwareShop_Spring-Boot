package hr.tvz.plese.hardwareapp.review_packages.review_service;

import hr.tvz.plese.hardwareapp.review_packages.review_classes.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findAllByHardwareCode(final String code);
}
