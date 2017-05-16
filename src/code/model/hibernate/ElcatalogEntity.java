package code.model.hibernate;

import javax.persistence.*;

/**
 * Created by admin on 12.05.2017.
 */
@Entity
@Table(name = "ELCATALOG", schema = "ADMIN")
public class ElcatalogEntity {
    private String author;
    private String title;
    private String publishingHouse;
    private String city;
    private Long year;
    private Long pagesCount;
    private String isn;

    public ElcatalogEntity() {
    }

    private String text;

    public ElcatalogEntity(String author, String title, String publishingHouse, String city, Long year, Long pagesCount, String isn, String text) {
        this.author = author;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.city = city;
        this.year = year;
        this.pagesCount = pagesCount;
        this.isn = isn;
        this.text = text;
    }

    @Basic
    @Column(name = "AUTHOR", nullable = true, length = 255)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "TITLE", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "PUBLISHING_HOUSE", nullable = true, length = 255)
    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Basic
    @Column(name = "CITY", nullable = true, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "YEAR", nullable = true, precision = 0)
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    @Basic
    @Column(name = "PAGES_COUNT", nullable = true, precision = 0)
    public Long getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Long pagesCount) {
        this.pagesCount = pagesCount;
    }

    @Id
    @Column(name = "ISN", nullable = false, length = 255)
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    public String getIsn() {
        return isn;
    }

    public void setIsn(String isn) {
        this.isn = isn;
    }

    @Basic
    @Column(name = "TEXT", nullable = true)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElcatalogEntity that = (ElcatalogEntity) o;

        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (publishingHouse != null ? !publishingHouse.equals(that.publishingHouse) : that.publishingHouse != null)
            return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (pagesCount != null ? !pagesCount.equals(that.pagesCount) : that.pagesCount != null) return false;
        if (isn != null ? !isn.equals(that.isn) : that.isn != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (publishingHouse != null ? publishingHouse.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (pagesCount != null ? pagesCount.hashCode() : 0);
        result = 31 * result + (isn != null ? isn.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
