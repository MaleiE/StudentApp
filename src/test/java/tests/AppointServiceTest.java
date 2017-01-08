package tests;

import com.malei.entities.Appoint;
import com.malei.entities.Student;
import com.malei.entities.Subject;
import com.malei.service.GenericService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = {com.malei.configuration.AppConfig.class})
@Transactional
public class AppointServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Qualifier("appointService")
    @Autowired
    GenericService<Appoint,Long> appointService;
    @Qualifier("studentService")
    @Autowired
    GenericService<Student,Long> studentService;
    @Qualifier("subjectService")
    @Autowired
    GenericService<Subject,Long> subjectService;

    @Test
    public void testAppointGetAll(){
        assertNotNull(appointService.getAll());
    }

    @Test
    public void testAppointCreate(){

        List<Appoint> listBefore = appointService.getAll();
        Student student = studentService.getAll().get(studentService.getAll().size()-1);
        Subject subject = subjectService.getAll().get(subjectService.getAll().size()-1);
        Appoint appoint = new Appoint(student,subject);
        appointService.save(appoint);
        List<Appoint> listAfter = appointService.getAll();
        Appoint appoint1 = listAfter.get(listAfter.size()-1);
        assertTrue(listAfter.size()-listBefore.size()==1);
        assertTrue(student.getId().equals(appoint1.getStudent().getId()));
        assertTrue(subject.getId().equals(appoint1.getSubject().getId()));
    }

    @Test
    public void testAppointUpdate(){
        List<Appoint>listBefore = appointService.getAll();
        Appoint appoint = listBefore.get(listBefore.size()-1);
        Subject subject1 = appoint.getSubject();
        Subject subject = subjectService.getAll().get(subjectService.getAll().size()-2);
        appoint.setSubject(subject);
        appointService.update(appoint);
        List<Appoint>listAfter = appointService.getAll();
        Appoint appoint1 = listAfter.get(listAfter.size()-1);
        assertTrue(appoint1.getSubject().getNameSubject().equals(subject.getNameSubject()));
    }

    @Test
    public void testAppointDelete(){
        List<Appoint>listBefore = appointService.getAll();
        Appoint appoint = listBefore.get(listBefore.size()-1);
        appointService.delete(listBefore.get(listBefore.size()-1));
        List<Appoint>listAfter = appointService.getAll();
        Appoint appoint1 = listAfter.get(listAfter.size()-1);
        assertFalse(appoint.equals(appoint1));
        assertTrue(listBefore.size()-listAfter.size()==1);
    }

    @Test
    public void testAppointFindByKey(){
        Student student = studentService.getAll().get(studentService.getAll().size()-1);
        Subject subject = subjectService.getAll().get(subjectService.getAll().size()-1);
        Appoint appoint = new Appoint(student,subject);
        appointService.save(appoint);
        List<Appoint> list = appointService.getAll();
        Appoint appoint1 = appointService.getByKey(list.get(list.size()-1).getId());
        assertTrue(appoint.getStudent().getFirsName().equals(appoint1.getStudent().getFirsName()));
        assertTrue(appoint.getSubject().getNameSubject().equals(appoint1.getSubject().getNameSubject()));
    }
}
