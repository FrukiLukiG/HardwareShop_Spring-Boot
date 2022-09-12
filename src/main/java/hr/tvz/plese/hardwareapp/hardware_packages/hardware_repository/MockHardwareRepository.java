package hr.tvz.plese.hardwareapp.hardware_packages.hardware_repository;

import hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes.Hardware;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MockHardwareRepository implements HardwareRepository {

    private final List<Hardware> mockedHardware = CreateHardware.getHardware();

    @Override
    public List<Hardware> findAll() {
        return mockedHardware;
    }

    @Override
    public Optional<Hardware> findByCode(final String code) {
        return mockedHardware.stream().filter(i -> Objects.equals(i.getCode(), code)).findAny();
    }

    @Override
    public Optional<Hardware> save(Hardware hardware){
        boolean isSameCode = mockedHardware.stream()
                .anyMatch(h -> h.getCode().equals(hardware.getCode()));

        if (!isSameCode) {
            mockedHardware.add(hardware);
            return Optional.of(hardware);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> update(Hardware updatedHardware){

        boolean itExists =  mockedHardware.removeIf(h -> h.getCode().equals(updatedHardware.getCode()));

        if(itExists){
            mockedHardware.add(updatedHardware);
            return Optional.of(updatedHardware);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(final String code) {
        mockedHardware.removeIf(h -> h.getCode().equals(code));
    }

}
