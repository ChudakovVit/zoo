package model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Entity
@Table(name = "AnimalPersonnel")
public class AnimalPersonnel {
    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private Integer animal_personnel;
    @Column
    private Integer animal;
    @Column
    private Integer personnel;

    public AnimalPersonnel() {
    }

    public AnimalPersonnel(Integer animal, Integer personnel) {
        this.animal = animal;
        this.personnel = personnel;
    }

    public AnimalPersonnel(Integer animal_personnel, Integer animal, Integer personnel) {
        this.animal_personnel = animal_personnel;
        this.animal = animal;
        this.personnel = personnel;
    }

    public Integer getAnimal_personnel() {
        return animal_personnel;
    }

    public void setAnimal_personnel(Integer animal_personnel) {
        this.animal_personnel = animal_personnel;
    }

    public Integer getAnimal() {
        return animal;
    }

    public void setAnimal(Integer animal) {
        this.animal = animal;
    }

    public Integer getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Integer personnel) {
        this.personnel = personnel;
    }

    @Override
    public String toString() {
        return "Animal has Personnel= Animal ID:" + animal + ", Personnel ID: " + personnel + "; ";
    }
}
