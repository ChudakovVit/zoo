package model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "Personnel")
public class Personnel {
    @Id
    private Integer personnel;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone_number")
    private String phoneNumber;

    public Personnel() {
    }

    public Personnel(Integer personnel, String fullName, String phoneNumber) {
        this.personnel = personnel;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public Integer getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Integer personnel) {
        this.personnel = personnel;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee: Full Name='" + fullName + "', Phone Number='" + phoneNumber + "'; ";
    }
}
