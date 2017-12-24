package model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "Animal")
public class Animal {
    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private Integer animal;
    @Column(name = "feed_id")
    private Integer feedId;
    @Column(name = "home_id")
    private Integer homeId;
    @Column
    private String kind;
    @Column
    private String description;

    public Animal() {
    }

    public Animal(String kind, String description) {
        this.kind = kind;
        this.description = description;
    }

    public Animal(Integer animal, Integer feedId, Integer homeId, String kind, String description) {
        this.animal = animal;
        this.feedId = feedId;
        this.homeId = homeId;
        this.kind = kind;
        this.description = description;
    }

    public Integer getAnimal() {
        return animal;
    }

    public void setAnimal(Integer animal) {
        this.animal = animal;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Integer getHomeId() {
        return homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Животное №" + animal + ": '" + description + "'";
    }
}