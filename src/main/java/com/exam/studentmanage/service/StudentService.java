package com.exam.studentmanage.service;

import com.exam.studentmanage.dto.PageRequestDTO;
import com.exam.studentmanage.dto.PageResponseDTO;
import com.exam.studentmanage.dto.ReplyDTO;
import com.exam.studentmanage.dto.StudentDTO;
import com.exam.studentmanage.entity.Student;

public interface StudentService {


    public Long register(StudentDTO studentDTO);

    public StudentDTO readOne(Long sno);

    public void modify(StudentDTO studentDTO);

    public void delete(Long sno);

    PageResponseDTO<StudentDTO> list(PageRequestDTO pageRequestDTO);

}
