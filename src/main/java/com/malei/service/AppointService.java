package com.malei.service;

import com.malei.dao.AppointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.malei.entities.Appoint;

import java.util.List;

@Service("appointService")
@Transactional
public class AppointService implements GenericService<Appoint,Long> {


    @Qualifier("appointDao")
    @Autowired
    private AppointDao appointDao;

    @Override
    public void save(Appoint ob) {
        appointDao.create(ob);
    }

    @Override
    public void update(Appoint ob) {
        appointDao.update(ob);
    }

    @Override
    public void delete(Appoint ob) {
        appointDao.delete(ob);
    }

    @Override
    public List<Appoint> getAll() {
        return appointDao.getAll();
    }

    @Override
    public Appoint getByKey(Long key) {
        return appointDao.getByKey(key);
    }
}
