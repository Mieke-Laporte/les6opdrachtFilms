package ucll.exercises.les6opdrachtfilms;

public class Film {
    private String title;
    private String description;
    private String summary;
    private Integer year;
    private Integer rating;
    private Float IMDB;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Float getIMDB() {
        return IMDB;
    }

    public void setIMDB(Float IMDB) {
        this.IMDB = IMDB;
    }
}
