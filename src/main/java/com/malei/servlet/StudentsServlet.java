package com.malei.servlet;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.malei.entities.Appoint;
import com.malei.entities.Student;
import com.malei.entities.Subject;
import com.malei.service.GenericService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;

import java.util.Set;



public class StudentsServlet extends HttpServlet {

    @Autowired
    @Qualifier("studentService")
    GenericService<Student,Long>  studentService;

    @Autowired
    @Qualifier("subjectService")
    GenericService<Subject,Long>  subjectService;

    @Autowired
    @Qualifier("appointService")
    GenericService<Appoint,Long>  appointService;

    @Autowired
    private ValidatorFactory validatorFactory;

    public Validator getValidator(){
        return validatorFactory.getValidator();
    }

    private WebApplicationContext springContext;



    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory=springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);


    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        allRequest(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        allRequest(req,resp);
    }

    private void allRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        System.out.println(url);
        if(url==null){
            url="/app/start";
        }
        System.out.println(url);
        String jsp=null;
        switch (url) {
            case "/app/start":
                jsp = printStudentsMainPage(req);
                break;
            case "/app/studentAdd":
                jsp = printStudentAddPage(req,resp);
                break;
            case "/app/studentAddForm":
                jsp = processingStudentAdd(req,resp);
                break;
            case "/app/appointAdd":
                jsp = processingAppointsAdd(req,resp);
                break;
            case "/app/subject":
                jsp = printSubjectPage(req,resp);
                break;
            case "/app/subjectAdd":
                jsp = processingSubjectAdd(req,resp);
                break;
            case "/app/deleteAppo":
                jsp = deleteAppointsPage(req,resp);
                break;
            case "/app/deleteSt":
                jsp = processingStudentDelete(req,resp);
                break;
            case "/app/updateSt":
                jsp = printUpdateStudent(req,resp);
                break;
            case "/app/studentUpdateForm":
                jsp = processingUpdateStudent(req,resp);
                break;
            case "/app/deleteSub":
                jsp = deleteSubject(req,resp);
                break;
            case "/app/updateSub":
                jsp = updateSubject(req,resp);
                break;

        }

        forward(jsp,req,resp);
    }

    private void forward(String form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        getServletContext().getRequestDispatcher(form).forward(request, response);
    }

    private String printStudentsMainPage(HttpServletRequest request)  {
        request.setAttribute("subjects",subjectService.getAll());
        request.setAttribute("students", studentService.getAll());
        return "/jsp/start.jsp";
    }

    private String printStudentAddPage(HttpServletRequest request,HttpServletResponse response)  {
        return "/jsp/studentAddPage.jsp";
    }

    private String processingStudentAdd(HttpServletRequest request, HttpServletResponse response)  {
        System.out.println("l");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate date = new LocalDate(Integer.parseInt(request.getParameter("entranceYear")),9,1);
        StringBuilder stringBuilder = null;
        try {
           Student student = new Student(firstName,lastName,date);
            stringBuilder=new StringBuilder();
           Set<ConstraintViolation<Student>> constraint = getValidator().validate(student);
           for(ConstraintViolation<Student> constr:constraint){
               stringBuilder.append(constr.getMessage());
               System.out.println(constr.getMessage());
           }

            studentService.save(student);
            request.setAttribute("mess", "Добавлен успешно");
            return printStudentsMainPage(request);

        }catch (ConstraintViolationException e){
            request.setAttribute("error", stringBuilder);
            return printStudentAddPage(request,response);
        }
    }

    private String deleteAppointsPage(HttpServletRequest request,HttpServletResponse response)  {
        System.out.println("удаляем");
        Long idAppoint = Long.parseLong(request.getParameter("appoId"));
        appointService.delete(appointService.getByKey(idAppoint));
        request.setAttribute("studentId",request.getParameter("studentId"));
        request.setAttribute("error","Назаначение удаленно");
        return printUpdateStudent(request,response);
    }

    private String  processingAppointsAdd(HttpServletRequest request,HttpServletResponse response)  {
        if(request.getParameter("studentId")==null||request.getParameter("subjectId")==null){
            return printStudentsMainPage(request);
        }
        Long idStud = Long.parseLong(request.getParameter("studentId"));
        Long idSub = Long.parseLong(request.getParameter("subjectId"));
        Subject subject = subjectService.getByKey(idSub);
        Student student = studentService.getByKey(idStud);
        appointService.save(new Appoint(student,subject));
        return printStudentsMainPage(request);
    }

    private String  printSubjectPage(HttpServletRequest request,HttpServletResponse response)  {
        request.setAttribute("subject0",subjectService.getAll());
        return "/jsp/subjectPage.jsp";
    }

    private String  processingSubjectAdd(HttpServletRequest request,HttpServletResponse response)  {
        String sub = request.getParameter("subjectName");
        StringBuilder stringBuilder = null;
        try {
            Subject subject = new Subject(sub);
            stringBuilder=new StringBuilder();
            Set<ConstraintViolation<Subject>> constraint = getValidator().validate(subject);
            for(ConstraintViolation<Subject> constr:constraint){
                stringBuilder.append(constr.getMessage());
                System.out.println(constr.getMessage());
            }
            subjectService.save(subject);
            request.setAttribute("mess", "Добавлен успешно");
            return  printSubjectPage(request,response);

        }catch (ConstraintViolationException e){
            request.setAttribute("error", "Error "+stringBuilder);
            return printSubjectPage(request,response);
        }
    }

    private String  processingStudentDelete(HttpServletRequest request,HttpServletResponse response)  {
        String s = "Студент удален "+studentService.getByKey(Long.parseLong(request.getParameter("studentId"))).getFirsName();
        studentService.delete(studentService.getByKey(Long.parseLong(request.getParameter("studentId"))));
        request.setAttribute("mess",s);
        return printStudentsMainPage(request);
    }

    private String  processingUpdateStudent(HttpServletRequest request,HttpServletResponse response)  {
        Long id = Long.parseLong(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate date = new LocalDate(Integer.parseInt(request.getParameter("entranceYear")),9,1);
        StringBuilder stringBuilder = null;
        try {
            Student student = new Student(firstName,lastName,date);
            student.setId(id);
            stringBuilder=new StringBuilder();
            Set<ConstraintViolation<Student>> constraint = getValidator().validate(student);
            for(ConstraintViolation<Student> constr:constraint){
                stringBuilder.append(constr.getMessage());
                System.out.println(constr.getMessage());
            }

            studentService.update(student);
            request.setAttribute("mess", "Обнавлен успешно");
            return printStudentsMainPage(request);

        }catch (ConstraintViolationException e){
            request.setAttribute("error", stringBuilder);
            return printUpdateStudent(request,response);
        }
    }
    private String  printUpdateStudent(HttpServletRequest request,HttpServletResponse response)  {
        Student studentBefore = studentService.getByKey(Long.parseLong(request.getParameter("studentId")));
        Long id = Long.parseLong(request.getParameter("studentId"));
        request.setAttribute("appoint",studentBefore.getAppoints());
        request.setAttribute("id",id);
        request.setAttribute("studentFirstName",studentBefore.getFirsName());
        request.setAttribute("studentLastName",studentBefore.getLastName());
        request.setAttribute("studentYear",studentBefore.getYearRevenue().getYear());

        return "/jsp/updateStudntPage.jsp";
    }

    private String deleteSubject(HttpServletRequest request,HttpServletResponse response){
        Long subId = Long.parseLong(request.getParameter("idSub"));
        subjectService.delete(subjectService.getByKey(subId));
        return printSubjectPage(request,response);
    }

    private String updateSubject(HttpServletRequest request,HttpServletResponse response){
        Long subId = Long.parseLong(request.getParameter("idSub"));
        subjectService.update(new Subject(subId,request.getParameter("nameSub")));
        return printSubjectPage(request,response);
    }

}
