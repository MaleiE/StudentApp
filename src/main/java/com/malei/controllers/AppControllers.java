package com.malei.controllers;

import com.malei.entities.Appoint;
import com.malei.entities.Student;
import com.malei.entities.Subject;
import com.malei.service.GenericService;
import com.malei.suport.SupportForStudentsApp;
import com.malei.validation.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AppControllers {

    @Qualifier("studentService")
    @Autowired
    GenericService<Student,Long> studentService;
    @Qualifier("subjectService")
    @Autowired
    GenericService<Subject,Long> subjectService;
    @Qualifier("appointService")
    @Autowired
    GenericService<Appoint,Long> appointService;
    @Autowired
    private SupportForStudentsApp support;



    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(){
        boolean principalIsAdmin = support.userIsAdmin(studentService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        boolean principalIsUser = support.userIsUser(studentService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        if (principalIsAdmin){
            return "redirect:/students";
        }
        if (principalIsUser){
            Long id = studentService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
            return "redirect:/updateStudent/"+id;
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        for (Cookie cookie : request.getCookies())
        {
            if (cookie.getName().equals("remember-me")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = {"/students"}, method = RequestMethod.GET)
    public ModelAndView students(){
        ModelAndView mov = new ModelAndView();
        List<Student> students = studentService.getAll();
        mov.addObject("students", students);
        mov.addObject("students", students);
        mov.addObject("student", new Student());
        mov.setViewName("students");
        return mov;
    }

    @RequestMapping(value = "/studentAdd", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse saveStudent(@Valid Student student, BindingResult result){
        ValidationResponse response = new ValidationResponse();
        if (result.hasErrors()){
            response.setHasError(true);
            String errorString="";
            for (ObjectError error: result.getAllErrors()){
                errorString+=error.getDefaultMessage()+"\n";
            }
            response.setStatus(errorString);
            response.setStudent(student);
            return response;
        }else {
            response.setHasError(false);
            response.setStatus("Студент "+student.getFirsName()+" "+student.getLastName()+" успешно добавлен!");
            studentService.save(student);
            response.setStudent(student);
            response.setSubjectList(subjectService.getAll());
            return response;
        }
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
      return new ModelAndView("welcome");
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDeniedPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("accessDenied");
        return mav;
    }

    @RequestMapping(value = "/updateStudent/{id}")
    public ModelAndView updateStudentPage(@PathVariable Long id){
        Student student = studentService.getByKey(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student",student);
        modelAndView.addObject("sublist",subjectService.getAll());
        modelAndView.addObject("applist",student.getAppoints());
        modelAndView.addObject("attend",new Appoint());
        modelAndView.setViewName("studentProfile");
        return modelAndView;
    }

    @RequestMapping(value = "subjectAdd", method = RequestMethod.POST)
    public ModelAndView subjectAdd(@Valid Subject subject,BindingResult result){
        subjectService.save(subject);
        return new ModelAndView(new RedirectView("/subject"));
    }

    @RequestMapping(value = "/updateSubject/{id}")
    public ModelAndView subjectUpdate(@PathVariable Long id,Subject subject,BindingResult result){
        subject.setId(id);
        subjectService.update(subject);
        return new ModelAndView(new RedirectView("/subject"));
    }

    @RequestMapping(value = {"/appointAdd"}, method = RequestMethod.POST)
    public ModelAndView attendSave(Appoint attend, @RequestParam("studentId") String studentId, @RequestParam("subject") String subjectId,BindingResult result){
        Student student = studentService.getByKey(Long.parseLong(studentId));
        Subject subject = subjectService.getByKey(Long.parseLong(subjectId));
        attend.setSubject(subject);
        attend.setStudent(student);
        appointService.save(attend);
        return new ModelAndView(new RedirectView("/updateStudent/"+studentId));
    }

    @RequestMapping(value = "/subject")
    public ModelAndView subjectPage(){
        List<Subject> subjects = subjectService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("subjects",subjects);
        modelAndView.addObject("subject", new Subject());
        modelAndView.setViewName("subjectPage");
        return modelAndView;
    }
    @RequestMapping(value = "/subjectDelete/{id}")
    public ModelAndView subjectDelelte(@PathVariable Long id){
        subjectService.delete(subjectService.getByKey(id));
        return new ModelAndView(new RedirectView("/subject"));
    }
    @RequestMapping(value="/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteStudent(@PathVariable Long id) {
        studentService.delete(studentService.getByKey(id));
    }


    @RequestMapping(value = "/deleteAppoint/{idAppoint}/{idStudent}")
    public ModelAndView appointDelete(@PathVariable Long idAppoint,@PathVariable Long idStudent){
        appointService.delete(appointService.getByKey(idAppoint));
        return new ModelAndView(new RedirectView("/updateStudent/"+idStudent));
    }

}
