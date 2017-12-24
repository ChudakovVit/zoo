package model;


import javax.persistence.*;


@Entity
@Table(name = "AnimalPersonnel")
public class AnimalPersonnel {
    @Id
    private Integer animal_personnel;
    @ManyToOne(cascade = CascadeType.ALL)
    private Integer animal;
    @ManyToOne(cascade = CascadeType.ALL)
    private Integer personnel;

    public Integer getAnimalPersonnelId() {
        return animal_personnel;
    }

    public void setAnimalPersonnelId(int animalPersonnelId) {
        this.animal_personnel = animalPersonnelId;
    }

    public Integer getAnimalId() {
        return animal;
    }

    public void setAnimalId(Integer animalId) {
        this.animal = animalId;
    }

    public Integer getPersonnelId() {
        return personnel;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnel = personnelId;
    }

    @Override
    public String toString() {
        return "Animal has Personnel= Animal ID:" + animal + ", Personnel ID: " + personnel + "; ";
    }
}
