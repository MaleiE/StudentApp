package com.malei.dao;

import com.malei.entities.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDao {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public Role getUserRole(){
        Criteria criteria = getSession().createCriteria(Role.class);
        criteria.add(Restrictions.eq("id", 4L));
        return (Role) criteria.uniqueResult();
    }

    public Role getAdminRole(){
        Criteria criteria = getSession().createCriteria(Role.class);
        criteria.add(Restrictions.eq("id", 3L));
        return (Role) criteria.uniqueResult();
    }
}