package com.malei.service;

import com.malei.entities.Role;
import com.malei.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService { // класс пользователя

    @Autowired
    @Qualifier("studentService")
    GenericService<Student,Long> studentService;

    // Ищет пользователя по логину в БД
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentService.findByUsername(username);
        System.out.println("USERNAME : " + username);
        System.out.println("Student : " + student);

        if (student==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }

        return new org.springframework.security.core.userdetails.User(
                username, student.getPassword(), true, true, true, true, getGrantedAuthorities(student));
    }

    // Передает в объект User все роли пользователя взятого из БД
    private Set<GrantedAuthority> getGrantedAuthorities(Student student){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        for (Role role : student.getRoles()){
            System.out.println("Role : " + role);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getType()));
        }
        System.out.println("authorities : " + authorities);
        return authorities;
    }
}
