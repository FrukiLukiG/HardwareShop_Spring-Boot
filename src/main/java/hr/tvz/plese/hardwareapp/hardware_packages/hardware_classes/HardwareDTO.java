package hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes;

public class HardwareDTO {

    private final String name;
    private final String code;
    private final Double price;

    public HardwareDTO(String name, String code, Double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }
}
