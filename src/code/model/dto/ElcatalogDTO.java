package code.model.dto;

/**
 * Created by admin on 18.05.2017.
 */
public class ElcatalogDTO {
    private String author;
    private String title;
    private String publishingHouse;
    private String city;
    private Long year;
    private Long pagesCount;
    private String isn;

    private String text;

    public ElcatalogDTO(String author, String title, String publishingHouse, String city, Long year, Long pagesCount, String isn, String text) {
        this.author = author;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.city = city;
        this.year = year;
        this.pagesCount = pagesCount;
        this.isn = isn;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Long pagesCount) {
        this.pagesCount = pagesCount;
    }

    public String getIsn() {
        return isn;
    }

    public void setIsn(String isn) {
        this.isn = isn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
