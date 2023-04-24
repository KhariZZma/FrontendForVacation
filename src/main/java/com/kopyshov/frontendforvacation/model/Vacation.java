package com.kopyshov.frontendforvacation.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vacations")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    private int id;

    @Column(name = "start_date")
    //@Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Column(name = "finish_date")
    //@Temporal(TemporalType.DATE)
    private LocalDate finishDate;

    public Vacation() {
    }


    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
