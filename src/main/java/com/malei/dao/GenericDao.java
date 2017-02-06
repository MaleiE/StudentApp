package com.malei.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable,K> {

    public void create(T object);

    public void delete(T object);

    public void update(T object);

    public List<T> getAll();

    public T getByKey(K key);


}
