package com.malei.service;

import java.util.List;


public interface GenericService<T,K> {

    void save(T ob);

    void update(T ob);

    void delete(T ob);

    List<T> getAll();

    T getByKey(K key);
}
