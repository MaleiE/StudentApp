package com.malei.dao;


import com.malei.entities.Subject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("subjectDao")
public class SubjectDao extends AbstractDao<Subject,Long> {
    @Override
    Criteria getCriteria(Session session) {
        return session.createCriteria(Subject.class);
    }
}
