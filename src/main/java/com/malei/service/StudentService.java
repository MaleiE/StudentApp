package com.malei.service;

import com.malei.dao.RoleDao;
import com.malei.dao.StudentDao;
import com.malei.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.malei.entities.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("studentService")
@Transactional
public class StudentService implements GenericService<Student,Long> {


    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;

    @Autowired
    @Qualifier("encoder")
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("roleDao")
    RoleDao roleDao;

    @Override
    public void save(Student ob) {
        ob.setPassword(passwordEncoder.encode(ob.getPassword()));
        Set<Role> roles=new HashSet<>();
        roles.add(roleDao.getUserRole());
        ob.setRoles(roles);
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

    @Override
    public Student findByUsername(String username) {
        return  studentDao.getByUsername(username);
    }
}
