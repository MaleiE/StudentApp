package com.malei.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.malei.entities.Student;

@Repository("studentDao")

public class StudentDao extends AbstractDao<Student,Long> {



    @Override
    Criteria getCriteria(Session session) {
        return session.createCriteria(Student.class);
    }
}
