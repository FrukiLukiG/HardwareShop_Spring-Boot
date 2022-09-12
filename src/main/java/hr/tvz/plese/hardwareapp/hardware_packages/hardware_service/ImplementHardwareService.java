package hr.tvz.plese.hardwareapp.hardware_packages.hardware_service;

import hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes.Hardware;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes.HardwareDTO;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_controller.HardwareCommand;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImplementHardwareService implements HardwareService {

    private final HardwareRepository hardwareRepository;

    public ImplementHardwareService(HardwareRepository hardwareRepository){
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapHardwareDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(final String code) {
        return hardwareRepository.findByCode(code).map(this::mapHardwareDTO);
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand hardwareCommand){
        return hardwareRepository.save(mapCommandHardware(hardwareCommand)).map(this::mapHardwareDTO);
    }

    @Override
    public Optional<HardwareDTO> update(HardwareCommand hardwareCommand, String code){
        if (hardwareCommand.getCode().equals(code)) {
            return hardwareRepository.update(mapCommandHardware(hardwareCommand)).map(this::mapHardwareDTO);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(final String code){
        hardwareRepository.deleteByCode(code);
    }


    private HardwareDTO mapHardwareDTO(final Hardware hardware){
        return new HardwareDTO(hardware.getName(), hardware.getCode(), hardware.getPrice());
    }

    private Hardware mapCommandHardware(final HardwareCommand hardwareCommand){
        return new Hardware(hardwareCommand.getName(), hardwareCommand.getCode(),
                hardwareCommand.getPrice(), hardwareCommand.getType(), hardwareCommand.getStock());
    }
}




