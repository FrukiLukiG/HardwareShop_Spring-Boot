package hr.tvz.plese.hardwareapp.review_packages.review_classes;

public class ReviewDTO {

    private final String title;
    private final String text;
    private final Integer rating;

    public ReviewDTO(String title, String text, Integer rating) {
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Integer getRating() {
        return rating;
    }
}
