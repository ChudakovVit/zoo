package model;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "Home")
public class Home {
    @Id
    private Integer home;
    @Column(name = "average_temp")
    private Integer averageTemp;
    @Column(name = "average_humidity")
    private Integer averageHumidity;
    @Column
    private String description;

    public Home() {
    }

    public Home(Integer home, Integer averageTemp, Integer averageHumidity, String description) {
        this.home = home;
        this.averageTemp = averageTemp;
        this.averageHumidity = averageHumidity;
        this.description = description;
    }

    public Integer getHome() {
        return home;
    }

    public void setHome(Integer home) {
        this.home = home;
    }

    public Integer getAverageTemp() {
        return averageTemp;
    }

    public void setAverageTemp(Integer averageTemp) {
        this.averageTemp = averageTemp;
    }

    public Integer getAverageHumidity() {
        return averageHumidity;
    }

    public void setAverageHumidity(Integer averageHumidity) {
        this.averageHumidity = averageHumidity;
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
                averageTemp +
                "C, Average Humidity: " +
                averageHumidity + "%, " +
                " Quantity: " + description;
    }
}
