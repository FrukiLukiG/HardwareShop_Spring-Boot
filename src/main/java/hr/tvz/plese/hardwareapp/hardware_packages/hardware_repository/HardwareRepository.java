package hr.tvz.plese.hardwareapp.hardware_packages.hardware_repository;

import hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes.Hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> save(Hardware hardware);

    Optional<Hardware> update(Hardware hardware);

    void deleteByCode(String code);
}
