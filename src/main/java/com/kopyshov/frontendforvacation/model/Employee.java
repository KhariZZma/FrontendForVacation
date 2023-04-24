package com.kopyshov.frontendforvacation.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "surname")
    private String surname;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "patronymic")
    private String patronymic;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private Set<Vacation> vacations = new HashSet<>();

    public Employee() {
    }

    public Employee(int id, String surname, String firstName, String patronymic) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Set<Vacation> getVacations() {
        return vacations;
    }

}
