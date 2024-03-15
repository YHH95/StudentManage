package com.exam.studentmanage;


import com.exam.studentmanage.dto.PageRequestDTO;
import com.exam.studentmanage.dto.StudentDTO;
import com.exam.studentmanage.entity.Student;
import com.exam.studentmanage.repository.StudentRepository;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest //테스트환경 만듬
@Log4j2 //로그
public class StudentRepositoryTest {



    @Autowired
    private StudentRepository studentRepository;


    //디폴트 값이 들어간 pageRequestDTO 객체를 만든다.
    PageRequestDTO pageRequestDTO = new PageRequestDTO();



    //insert 테스트. repository의 save 사용
    @Test
    public void registerTest(){

        Student student = new Student();
        student.setName("은지원");
        student.setBirthdate("19950212");
        student.setPhone("010-6733-2311");
        student.setPhone2("010-3733-2812");
        student.setLevel("3");
        student.setClassnum("3");
        student.setScore("40");
        student.setGrade("70");
        student.setComment("");
        studentRepository.save(student);
    }

    //view 테스트. findById(기본키 값) 사용
    @Test
    public void viewTest(){
        log.info(studentRepository.findById(3L));
    }

    //list 테스트 findAll 사용
    @Test
    public void listTest(){
        log.info(studentRepository.findAll());
    }





    @Test
    public void testSearch1(){

        Pageable pageable = PageRequest.of(1,10,Sort.by("sno").descending());
        //페이지어블 만듬. 페이지 번호, 사이즈, 정렬조건, 정렬방향

        studentRepository.search1(pageable); //만든 페이지어블로 search1 메소드 실행
    }



    @Test
    public void testAll(){

        String[] types = {"a","b","c"};

        String keyword = "호";


        //Pageable pageable = PageRequest.of(0,10,Sort.by("sno").descending());
        Pageable pageable = pageRequestDTO.getPageable("sno");

        System.out.println(pageable);


        Page<Student> result = studentRepository.searchAll(types,keyword,pageable);


        log.info("total count는 " + result.getTotalElements());
        log.info("total pages는 " + result.getTotalPages());
        log.info("page number는 " + result.getNumber());
        log.info("page size는 " + result.getSize());

        List<Student> studentList = result.getContent(); //result의 내용 리스트에 담고
        studentList.forEach(student -> log.info(student)); //출력
    }






    //페이징처리 테스트
    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("sno").descending());
        //페이지 번호, 사이즈, 정렬조건, 정렬방향

        Page<Student> result = studentRepository.findAll(pageable);


        log.info("total count는 " + result.getTotalElements());
        log.info("total pages는 " + result.getTotalPages());
        log.info("page number는 " + result.getNumber());
        log.info("page size는 " + result.getSize());

        List<Student> studentList = result.getContent(); //result의 내용 리스트에 담고
        studentList.forEach(student -> log.info(student)); //출력
    }


    @Test
    public void testSearchAllCount(){

        String[] types = {"a,b,c,d"};
        String keyword = "강";

        Pageable pageable = pageRequestDTO.getPageable("sno");

        Page<StudentDTO> result = studentRepository.searchWithReplyCount(types,keyword,pageable);

        result.getContent().forEach(student -> log.info(student));
    }



}
