package hr.tvz.plese.hardwareapp.hardware_packages.hardware_repository;

import hr.tvz.plese.hardwareapp.enumerations.TypeEnum;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes.Hardware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateHardware {

    private static final Hardware h1 =
            new Hardware("ASUS ROG Strix 570-E","4053156801", 2649.00, TypeEnum.MBO, 21);
    private static final Hardware h2 =
            new Hardware("AMD Ryzen 7 5800X","7603721210", 2892.99, TypeEnum.CPU, 43);
    private static final Hardware h3 =
            new Hardware("Nvidia GeForce RTX 3080","5201307907", 8427.00 , TypeEnum.GPU, 2);
    private static final Hardware h4 =
            new Hardware("Crucial Ballistix 32GB DDR4","9247483505", 1022.40, TypeEnum.RAM, 32);
    private static final Hardware h5 =
            new Hardware("Samsung SSD 4TB 870 EVO","4549854912", 3263.00, TypeEnum.STORAGE, 51);
    private static final Hardware h6 =
            new Hardware("Gigabyte B560M DS3H V2","4746171138", 739.99, TypeEnum.MBO, 27);
    private static final Hardware h7 =
            new Hardware("Intel Core i5-12600K","1237740127", 2537.50, TypeEnum.CPU, 38);
    private static final Hardware h8 =
            new Hardware("AMD Radeon RX 6800 XT","0581947777", 9286.00, TypeEnum.GPU, 1);
    private static final Hardware h9 =
            new Hardware("WD Blue SSD 1TB SN550","3198110391", 795.00, TypeEnum.STORAGE, 49);
    private static final Hardware h10 =
            new Hardware("Kingston HyperX Fury Beast 8GB DDR4","1104857058", 415.00, TypeEnum.RAM, 28);
    private static final Hardware h11 =
            new Hardware("Samsung LC27G55TQWRXEN monitor","9749230089", 2000.00, TypeEnum.OTHER, 67);
    private static final Hardware h12 =
            new Hardware("RAZER Viper Ultimate","8896658986", 721.54, TypeEnum.OTHER, 123);

    public static List<Hardware> getHardware() {
        return new ArrayList<>(Arrays.asList(h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12));
    }

}
