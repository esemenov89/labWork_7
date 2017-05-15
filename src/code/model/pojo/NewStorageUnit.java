package code.model.pojo;

/**
 * Created by admin on 22.04.2017.
 */
public class NewStorageUnit {
    private String author;
    private String title;
    private String publishingHouse;
    private String city;
    private String year;
    private String pagesCount;
    private String isn;
    private String isnOld;

    public String getIsnOld() {
        return isnOld;
    }

    public void setIsnOld(String isnOld) {
        this.isnOld = isnOld;
    }

    private String text;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public String getCity() {
        return city;
    }

    public String getYear() {
        return year;
    }

    public String getPagesCount() {
        return pagesCount;
    }

    public String getIsn() {
        return isn;
    }

    public String getText() {
        return text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPagesCount(String pagesCount) {
        this.pagesCount = pagesCount;
    }

    public void setIsn(String isn) {
        this.isn = isn;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NewStorageUnit() {
    }

    public NewStorageUnit(String author, String title, String publishingHouse, String city, String year, String pagesCount, String isn, String text, String isnOld) {
        this.author = author;
        this.title = title;

        this.publishingHouse = publishingHouse;
        this.city = city;
        this.year = year;
        this.pagesCount = pagesCount;
        this.isn = isn;
        this.isnOld = isnOld;
        this.text = text;
    }

    @Override
    public String toString() {
        return "StorageUnit{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", city='" + city + '\'' +
                ", year=" + year +
                ", pagesCount=" + pagesCount +
                ", isn='" + isn + '\'' +
                ", isnOld='" + isnOld + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
