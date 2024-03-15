package com.exam.studentmanage.service;

import com.exam.studentmanage.dto.PageRequestDTO;
import com.exam.studentmanage.dto.PageResponseDTO;
import com.exam.studentmanage.dto.ReplyDTO;
import com.exam.studentmanage.dto.StudentDTO;
import com.exam.studentmanage.entity.Student;
import com.exam.studentmanage.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional //트랜잭션 처리를 해줌

public class StudentServiceImpl implements StudentService{


    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    @Override
    public PageResponseDTO<StudentDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes(); //pageRequestDTO 객체의 타입을 받는다.
        String keyword = pageRequestDTO.getKeyword(); //키워드를 받는다.
        Pageable pageable = pageRequestDTO.getPageable("sno"); //pageable을 만든다.

        Page<Student> result = studentRepository.searchAll(types,keyword,pageable);

        List<Student> studentList = result.getContent();
        List<StudentDTO> dtoList = studentList.stream()
                .map(student -> modelMapper.map(student,StudentDTO.class)).collect(Collectors.toList());
        //page<Student> 를 List<StudentDTO>로 변환하는 식.


        return PageResponseDTO.<StudentDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }


    @Override
    public Long register(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO,Student.class);
        //파라미터로 입력받은 studentDTO를 Student 객체로 변경

        Long sno = studentRepository.save(student).getSno();
        //student 객체의 값으로 save(생성) 실행 후 sno값을 받아서 Long sno에 넣음


        return sno;
        //위에서 받은 sno 리턴
    }

    @Override
    public StudentDTO readOne(Long sno) {
        Optional<Student> student = studentRepository.findById(sno);
        Student student1 = student.orElseThrow();
        //Optional은 값이 null일 경우를 대비하는 클래스
        //sno 값으로 조회 한 후 student의 에러를 처리한 값을 student1에 넣음


        StudentDTO studentDTO = modelMapper.map(student1,StudentDTO.class);
        //student1을 모델매퍼 이용해서 studentDTO로 변환
        return studentDTO;
        //리턴
    }

    @Override
    public void modify(StudentDTO studentDTO) {

        Optional<Student> student = studentRepository.findById(studentDTO.getSno());
        Student student1 = student.orElseThrow();
        //수정할 객체를 찾아야하므로
        //(파라미터로 가져온 studentDTO의 sno값을 받아서) find 실행

        student1.setName(studentDTO.getName());
        student1.setLevel(studentDTO.getLevel());
        student1.setClassnum(studentDTO.getClassnum());
        student1.setComment(studentDTO.getComment());
        student1.setPhone(studentDTO.getPhone());
        student1.setGrade(studentDTO.getGrade());
        student1.setPhone2(studentDTO.getPhone2());
        student1.setScore(studentDTO.getScore());
        student1.setRegdate(studentDTO.getRegdate());
        student1.setBirthdate(studentDTO.getBirthdate());
        //파라미터로 가져온 studentDTO의 값들을 위에서 읽어온 student1 객체에 넣는다

        studentRepository.save(student1);
        //값이 수정된 student1을 save(수정)한다

    }

    @Override
    public void delete(Long sno) {

        studentRepository.deleteById(sno);
        //파라미터로 가져온 sno값을 넣어서 delete 한다

    }



}
