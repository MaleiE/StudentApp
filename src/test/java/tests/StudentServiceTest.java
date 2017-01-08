package tests;

import com.malei.entities.Student;
import com.malei.service.GenericService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


import java.util.List;


@ContextConfiguration(classes = {com.malei.configuration.AppConfig.class})
@Transactional
public class StudentServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Qualifier("studentService")
    @Autowired
    GenericService<Student,Long> studentService;


    @DataProvider(name = "dataStudent")
    public Object[][] studentData() {
        return new Object[][]{
                {new Student("test","test",new LocalDate(10/2/1990))},
        };
    }


    @Test
    public void testStudentGetAll(){
        assertNotNull(studentService.getAll());
    }

    @Test(dataProvider = "dataStudent")
    public void testStudentCreate(Student student){
        List<Student> listBefore = studentService.getAll();
        studentService.save(student);
        System.out.println(student);
        List<Student> listAfter = studentService.getAll();
        Student student1 = listAfter.get(listAfter.size()-1);
        assertTrue(listAfter.size()-listBefore.size()==1);
        assertTrue(student.getFirsName().equals(student1.getFirsName()));
        assertTrue(student.getLastName().equals(student1.getLastName()));
        assertTrue(student.getYearRevenue()==student1.getYearRevenue());
    }

    @Test
    public void testStudentUpdate(){
        List<Student>listBefore = studentService.getAll();
        Student student = listBefore.get(listBefore.size()-1);
        student.setFirsName("test");
        studentService.update(student);
        List<Student>listAfter = studentService.getAll();
        Student student1 = listAfter.get(listAfter.size()-1);
        assertTrue(student1.getFirsName().equals(student.getFirsName()));
    }

    @Test
    public void testStudentDelete(){
        List<Student>listBefore = studentService.getAll();
        Student student = listBefore.get(listBefore.size()-1);
        studentService.delete(listBefore.get(listBefore.size()-1));
        List<Student>listAfter = studentService.getAll();
        Student student1 = listAfter.get(listAfter.size()-1);
        assertFalse(student.equals(student1));
        assertTrue(listBefore.size()-listAfter.size()==1);
    }

    @Test(dataProvider = "dataStudent")
    public void testStudentFindByKey(Student student){
        studentService.save(student);
        List<Student> list = studentService.getAll();
        Student student1 = studentService.getByKey(list.get(list.size()-1).getId());
        assertTrue(student.getFirsName().equals(student1.getFirsName()));
        assertTrue(student.getLastName().equals(student1.getLastName()));
        assertTrue(student.getYearRevenue()==student1.getYearRevenue());
    }
}
