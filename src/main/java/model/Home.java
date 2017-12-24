package model;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "Home")
public class Home {
    @Id
    private Integer home;
    @Column
    private Integer average_temp;
    @Column
    private Integer average_humidity;
    @Column
    private String description;

    public Home(Integer home, Integer average_temp, Integer average_humidity, String description) {
        this.home = home;
        this.average_temp = average_temp;
        this.average_humidity = average_humidity;
        this.description = description;
    }

    public Integer getHome() {
        return home;
    }

    public void setHome(Integer home) {
        this.home = home;
    }

    public Integer getAverage_temp() {
        return average_temp;
    }

    public void setAverage_temp(Integer average_temp) {
        this.average_temp = average_temp;
    }

    public Integer getAverage_humidity() {
        return average_humidity;
    }

    public void setAverage_humidity(Integer average_humidity) {
        this.average_humidity = average_humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Animal Home: Average Temperature: " +
                average_temp +
                "C, Average Humidity: " +
                average_humidity + "%, " +
                " Quantity: " + description;
    }
}
