package hr.tvz.plese.hardwareapp.hardware_packages.hardware_service;

import hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes.HardwareDTO;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_controller.HardwareCommand;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    Optional<HardwareDTO> findByCode(String code);

    Optional<HardwareDTO> save(HardwareCommand hardwareCommand);

    Optional<HardwareDTO> update(HardwareCommand hardwareCommand, String code);

    void deleteByCode(String code);
}
