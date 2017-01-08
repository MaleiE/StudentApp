package com.malei.entities;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student extends Model {

    private String firsName;
    private String lastName;
    private LocalDate yearRevenue;
    private List<Appoint> appoints = new ArrayList<>(0);

    public Student() {
    }

    public Student(Long id) {
        super(id);
    }

    public Student(String firsName, String lastName, LocalDate yearRevenue) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.yearRevenue = yearRevenue;
    }

    public Student(Long id, String firsName, String lastName, LocalDate yearRevenue) {
        super(id);
        this.firsName = firsName;
        this.lastName = lastName;
        this.yearRevenue = yearRevenue;
    }
    @NotNull
    @Size(min = 3,max = 12)
    @Column(name = "first_name", nullable = false)
    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    @NotNull
    @Size(min = 3,max = 12)
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "enter_year", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    public LocalDate getYearRevenue() {
        return yearRevenue;
    }

    public void setYearRevenue(LocalDate yearRevenue) {
        this.yearRevenue = yearRevenue;
    }
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student",fetch = FetchType.LAZY)
    public List<Appoint> getAppoints() {
        return appoints;
    }

    public void setAppoints(List<Appoint> appoints) {
        this.appoints = appoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(this.getId(),student.getId())&&
                Objects.equals(firsName, student.firsName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(yearRevenue, student.yearRevenue) &&
                Objects.equals(appoints, student.appoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(),firsName, lastName, yearRevenue, appoints);
    }

   @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", firsName='").append(firsName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", yearRevenue=").append(yearRevenue);
        sb.append(", appoints=").append(appoints);
        sb.append('}');
        return sb.toString();
    }
}
