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

    public int getFeedId() {
        return feed;
    }

    public void setFeedId(int feedId) {
        this.feed = feedId;
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
