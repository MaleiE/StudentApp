package com.malei.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "appoint")
public class Appoint implements Serializable {
    private Long id;
    private Student student;
    private Subject subject;


    public Appoint() {
    }

    public Appoint(Long id) {
        this.id=id;
    }

    public Appoint(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
    }

    public Appoint(Long id, Student student, Subject subject) {
        this.id=id;
        this.student = student;
        this.subject = subject;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appoint_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "student_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appoint appoint = (Appoint) o;
        return Objects.equals(this.getId(),appoint.getId())&&
                Objects.equals(student, appoint.student) &&
                Objects.equals(subject, appoint.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(),student, subject);
    }
/*
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Appoint{");
        sb.append("id=").append(this.getId());
        sb.append(", student=").append(student);
        sb.append(", subject=").append(subject);
        sb.append('}');
        return sb.toString();
    }*/
}
