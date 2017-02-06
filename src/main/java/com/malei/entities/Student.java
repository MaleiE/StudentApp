package com.malei.entities;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    private Long id; // Идентификатор
    private String firsName;
    private String lastName;
    private String username; // Логин
    private String password; // Пароль
    private String confirmPassword; // Подтверждение пароля
    private LocalDate yearRevenue;
    private List<Appoint> appoints = new ArrayList<>(0);
    private Set<Role> roles = new HashSet<>(); // Роли студента

    public Student() {
    }

    public Student(Long id) {
        this.id=id;
    }

    public Student(String firsName, String lastName, LocalDate yearRevenue) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.yearRevenue = yearRevenue;
    }
    public Student(String firsName, String lastName) {
        this.firsName = firsName;
        this.lastName = lastName;
    }

    public Student(Long id, String firsName, String lastName, LocalDate yearRevenue) {
        this.id=id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.yearRevenue = yearRevenue;
    }

    public Student(Long id, String firsName, String lastName, String username, String password, String confirmPassword, LocalDate yearRevenue) {
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.yearRevenue = yearRevenue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id")})
    @JsonBackReference
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

  //  @NotNull(message = "Заполни дату")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
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

  /* @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", firsName='").append(firsName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", yearRevenue=").append(yearRevenue);
        sb.append(", appoints=").append(appoints);
        sb.append('}');
        return sb.toString();
    }*/
}
