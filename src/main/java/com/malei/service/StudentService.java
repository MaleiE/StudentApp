package com.malei.service;

import com.malei.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.malei.entities.Student;

import java.util.List;

@Service("studentService")
@Transactional
public class StudentService implements GenericService<Student,Long> {


    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;

    @Override
    public void save(Student ob) {
        studentDao.create(ob);
    }

    @Override
    public void update(Student ob) {
        studentDao.update(ob);
    }

    @Override
    public void delete(Student ob) {
        studentDao.delete(ob);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public Student getByKey(Long key) {
        return studentDao.getByKey(key);
    }
}
