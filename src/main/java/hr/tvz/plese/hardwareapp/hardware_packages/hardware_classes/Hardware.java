package hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes;

import hr.tvz.plese.hardwareapp.enumerations.TypeEnum;
import hr.tvz.plese.hardwareapp.review_packages.review_classes.Review;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "hardware")
public class Hardware implements Serializable {

    @Id
    @Column(name = "h_id")
    @GeneratedValue
    private Long id;

    @Column(name = "h_name")
    private String name;

    @Column(name = "h_code")
    private String code;

    @Column(name = "h_price")
    private Double price;

    @Column(name = "h_type")
    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @Column(name = "h_num_available")
    private Integer numAvailable;

    @OneToMany(mappedBy = "hardwareToReview", fetch = FetchType.EAGER)
    private List<Review> reviewList;

    public Hardware(){}

    public Hardware(Long id, String name, String code, Double price, TypeEnum type, Integer numAvailable) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.numAvailable = numAvailable;
    }

    public Hardware(String name, String code, Double price, TypeEnum type, Integer numAvailable) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.numAvailable = numAvailable;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    public TypeEnum getType() {
        return type;
    }

    public Integer getNumAvailable() {
        return numAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
