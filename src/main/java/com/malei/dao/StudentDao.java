package com.malei.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.malei.entities.Student;

import java.util.List;

@Repository("studentDao")

public class StudentDao extends AbstractDao<Student,Long> {


    @Override
    List getListByUsername(Session session, String username) {

        List students;
        System.out.println("StudentDao : " +username);
        students = session.createQuery("from Student where username= :username")
                .setString("username", username)
                .list();
        System.out.println("StudentDao list : " +students.toString());
        return students;
    }


    @Override
    Criteria getCriteria(Session session) {
        return session.createCriteria(Student.class);
    }


}
