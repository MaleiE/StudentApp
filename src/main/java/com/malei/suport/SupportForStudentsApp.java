package com.malei.suport;

import com.malei.entities.Role;
import com.malei.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class SupportForStudentsApp {

    public boolean userIsAdmin(Student student){
        boolean isAdmin=false;
        if (student!=null){
            for (Role role: student.getRoles()){
                if (role.getType().equals("ADMIN")){
                    isAdmin=true;
                }
            }
        }
        return isAdmin;
    }

    public boolean userIsUser(Student student){
        boolean isUser=false;
        if (student!=null){
            for (Role role: student.getRoles()){
                if (role.getType().equals("USER")){
                    isUser=true;
                }
            }
        }
        return isUser;
    }
}
