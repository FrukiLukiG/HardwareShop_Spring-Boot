package hr.tvz.plese.hardwareapp.hardware_packages.hardware_controller;

import hr.tvz.plese.hardwareapp.enumerations.TypeEnum;

import javax.validation.constraints.*;

public class HardwareCommand {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Code must not be blank")
    @Pattern (message = "Code must contain 10 numbers", regexp = "[\\d]{10}")
    private String code;

    @NotNull(message = "Price must be entered")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    private TypeEnum type;

    @NotNull(message = "Number of available hardware must be entered")
    @Positive(message = "Number of available hardware must be greater than 0")
    @Max(message = "Number of available hardware cannot be greater than 1000 for 1 specific hardware", value = 1000)
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
