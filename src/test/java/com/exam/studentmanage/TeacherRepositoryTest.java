package com.exam.studentmanage;


import com.exam.studentmanage.entity.Student;
import com.exam.studentmanage.entity.Teacher;
import com.exam.studentmanage.repository.TeacherRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2

public class TeacherRepositoryTest {


    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void registerTest(){

        Teacher teacher = new Teacher();
        teacher.setId("wldud123");
        teacher.setPw("woori");
        teacher.setName("이지영");
        teacher.setSubject("수학");
    }
}
