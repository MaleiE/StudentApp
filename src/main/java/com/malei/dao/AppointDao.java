package com.malei.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.malei.entities.Appoint;

import java.util.List;

@Repository("appointDao")
public class AppointDao extends AbstractDao<Appoint,Long> {
    @Override
    Criteria getCriteria(Session session) {
        return session.createCriteria(Appoint.class);
    }

    List<Appoint> getListByUsername(Session session, String username) {
        return null;
    }
}
