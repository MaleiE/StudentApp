package com.malei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.malei.dao.SubjectDao;
import com.malei.entities.Subject;

import java.util.List;

@Service("subjectService")
@Transactional
public class SubjectService implements GenericService<Subject,Long> {

    @Qualifier("subjectDao")
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public void save(Subject ob) {
        subjectDao.create(ob);
    }

    @Override
    public void update(Subject ob) {
        subjectDao.update(ob);
    }

    @Override
    public void delete(Subject ob) {
        subjectDao.delete(ob);
    }

    @Override
    public List<Subject> getAll() {
        return subjectDao.getAll();
    }

    @Override
    public Subject getByKey(Long key) {
        return subjectDao.getByKey(key);
    }
}
