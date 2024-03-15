package com.exam.studentmanage;


import com.exam.studentmanage.dto.PageRequestDTO;
import com.exam.studentmanage.dto.PageResponseDTO;
import com.exam.studentmanage.dto.StudentDTO;
import com.exam.studentmanage.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class StudentServiceTest {


    @Autowired
    private StudentService studentService;

    @Test
    public void testRegisterTest(){

        StudentDTO studentDTO = StudentDTO.builder().name("김지용").level("1").classnum("2").grade("80").score("70").phone("010-4848-2525").phone2("010-6312-3834").birthdate("19950611").comment("미친놈").build();
        //빌더패턴으로 값을 넣어서 DTO 생성

        Long sno = studentService.register(studentDTO);
        //register 실행하고 반환타입인 Long sno에 넣는다

        log.info("sno : "+ sno);
    }

    @Test
    public void testReadOneTest(){
        Long sno = 3L;
        StudentDTO studentDTO = studentService.readOne(sno);

        log.info(studentDTO);
    }

    @Test
    public void modifyTest(){

        //수정 할 DTO 객체를 빌더패턴으로 만드는데 기본키인 sno를 적어서 정하고 수정할 값을 넣는다
        StudentDTO studentDTO = StudentDTO.builder().sno(2L).name("신짱구").level("3").classnum("3").grade("30").score("50").phone("010-5853-7441").phone2("010-6312-3834").birthdate("19950622").comment("장난꾸러기").build();

        studentService.modify(studentDTO);
    }


    @Test
    public void deleteTest(){
        studentService.delete(10L);
    }




    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().type("abc").keyword("호").page(1).size(10).build();
        //빌더패턴으로 pageRequestDTO를 만들었음.

        PageResponseDTO<StudentDTO> responseDTO = studentService.list(pageRequestDTO);
        //pageRequestDTO로 서비스의 list 메소드를 실행하여 responseDTO에 넣음

        log.info(responseDTO);
    }




}
