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
    @Column
    private Integer feed_id;
    @Column
    private Integer home_id;
    @Column
    private String kind;
    @Column
    private String description;

    public Animal(String kind, String description) {
        this.kind = kind;
        this.description = description;
    }

    public Animal() {
    }

    public Integer getAnimal() {
        return animal;
    }

    public void setAnimal(Integer animal) {
        this.animal = animal;
    }

    public Integer getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(Integer feed_id) {
        this.feed_id = feed_id;
    }

    public Integer getHome_id() {
        return home_id;
    }

    public void setHome_id(Integer home_id) {
        this.home_id = home_id;
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
        return "Animal: ID=" + animal + ", Kind='" + kind + "', Description='" + description + "';";
    }
}