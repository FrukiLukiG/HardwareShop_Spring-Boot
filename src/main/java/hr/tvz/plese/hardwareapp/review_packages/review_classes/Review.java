package hr.tvz.plese.hardwareapp.review_packages.review_classes;

import hr.tvz.plese.hardwareapp.enumerations.GradeEnum;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_classes.Hardware;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "r_id")
    @GeneratedValue
    private Long id;

    @Column(name = "r_title")
    private String title;

    @Column(name = "r_text")
    private String text;

    @Column(name = "r_grade")
    @Enumerated(EnumType.STRING)
    private GradeEnum grade;

    @ManyToOne
    @JoinColumn(name = "h_id")
    private Hardware hardwareToReview;

    public Review(){}

    public Review(Long id, String title, String text, GradeEnum grade) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.grade = grade;
    }

    public Review(String title, String text, GradeEnum grade) {
        this.title = title;
        this.text = text;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public GradeEnum getGrade() {
        return grade;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
