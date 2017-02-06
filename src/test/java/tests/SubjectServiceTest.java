package tests;
/*

import com.malei.entities.Subject;
import com.malei.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = {com.malei.configuration.AppConfig.class})
@Transactional
public class SubjectServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Qualifier("subjectService")
    @Autowired
    GenericService<Subject,Long> subjectService;

    @Test
    public void testSubjectCreate(){
        List<Subject> listBefore = subjectService.getAll();
        Subject subject = new Subject("test");
        subjectService.save(subject);
        List<Subject> listAfter = subjectService.getAll();
        Subject subject1 = listAfter.get(listAfter.size()-1);
        assertTrue(listAfter.size()-listBefore.size()==1);
        assertTrue(subject.getNameSubject().equals(subject1.getNameSubject()));
    }

    @Test
    public void testSubjectUpdate(){
        List<Subject>listBefore = subjectService.getAll();
        Subject subject = listBefore.get(listBefore.size()-1);
        subject.setNameSubject("test");
        System.out.print(subject.getNameSubject());
        subjectService.update(subject);
        List<Subject>listAfter = subjectService.getAll();
        Subject subject1 = listAfter.get(listAfter.size()-1);
        assertTrue(subject.getNameSubject().equals(subject1.getNameSubject()));
    }

    @Test
    public void testSubjectDelete(){
        List<Subject>listBefore = subjectService.getAll();
        System.out.println(listBefore.size());
        Subject subject = listBefore.get(listBefore.size()-1);
        subjectService.delete(listBefore.get(listBefore.size()-1));
        List<Subject>listAfter = subjectService.getAll();
        System.out.println(listAfter.size());
        Subject subject1 = listAfter.get(listAfter.size()-1);
        assertFalse(subject.equals(subject1));
        assertTrue(listBefore.size()-listAfter.size()==1);
    }

    @Test
    public void testSubjectFindByKey(){
        Subject subject = new Subject("test");
        subjectService.save(subject);
        List<Subject> list = subjectService.getAll();
        Subject subject1 = subjectService.getByKey(list.get(list.size()-1).getId());
        assertTrue(subject.getNameSubject().equals(subject1.getNameSubject()));
    }
}
*/