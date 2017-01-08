package com.malei.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "subjects")
public class Subject extends Model {

    private String nameSubject;
    public Subject() {
    }

    public Subject(Long id) {
        super(id);
    }

    public Subject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Subject(Long id, String nameSubject) {
        super(id);
        this.nameSubject = nameSubject;
    }
    @Size(min = 3,max = 10)
    @Column(name = "subject_name")
    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(this.getId(), subject.getId())&&
                Objects.equals(nameSubject, subject.nameSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(),nameSubject);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subject{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append(", nameSubject='").append(nameSubject).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
