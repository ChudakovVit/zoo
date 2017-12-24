package model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "Personnel")
public class Personnel {
    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private Integer personnel;
    @Column
    private String full_name;
    @Column
    private String phone_number;

    public int getPersonnelId() {
        return personnel;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnel = personnelId;
    }

    public String getName() {
        return full_name;
    }

    public void setName(String fullName) {
        this.full_name = fullName;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee: Full Name='" + full_name + "', Phone Number='" + phone_number + "'; ";
    }
}
