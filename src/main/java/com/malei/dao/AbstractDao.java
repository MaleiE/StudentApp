package com.malei.dao;


import com.malei.entities.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.ServiceConfigurationError;

public abstract class AbstractDao<T extends Serializable,K> implements GenericDao<T ,K>  {

    @Autowired
    private SessionFactory sessionFactory;


    public Session getSession(){
      return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(T object) {
        getSession().save(object);
    }

    @Override
    public void delete(T object) {
        getSession().delete(object);
    }

    @Override
    public void update(T object) {
        getSession().merge(object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {

        return (List<T>) getCriteria(getSession()).list();

    }

    @Override
    @SuppressWarnings("unchecked")
    public T getByKey(K key) {

        Criteria criteria = getCriteria(getSession());
        criteria.add(Restrictions.eq("id",key));

        return (T) criteria.uniqueResult();
    }
/*
    @SuppressWarnings("unchecked")
    public T getByUsername(String username) {
        System.out.println("AbstractDao : "+username);
        Criteria criteria = getSession().createCriteria(Student.class);
        criteria.add(Restrictions.eq("username", username));

        System.out.println("Критерия : "+criteria.uniqueResult());
        return (T) criteria.uniqueResult();
    }

*/
    public T getByUsername(String username) {
        List<T> list;
        list = getListByUsername(getSession(), username);

        if (list.size() > 0){
            return list.get(0);
        } else {
            return null;
        }
    }
    abstract List<T> getListByUsername(Session session, String username);

    abstract Criteria getCriteria(Session session);

}
