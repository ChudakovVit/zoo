package model;


import javax.persistence.*;


@Entity
@Table(name = "Feed")
public class Feed {
    @Id
    private Integer feed;
    @Column
    private String name;
    @Column
    private Integer quantity;

    public Feed() {
    }

    public Feed(Integer feed, String name, Integer quantity) {
        this.feed = feed;
        this.name = name;
        this.quantity = quantity;
    }

    public Integer getFeed() {
        return feed;
    }

    public void setFeed(Integer feed) {
        this.feed = feed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Feed: Name='" + name + "', Quantity=" + quantity + " KG; ";
    }
}
