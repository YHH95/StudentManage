package com.exam.studentmanage.repository.search;

import com.exam.studentmanage.dto.StudentDTO;
import com.exam.studentmanage.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentSearch {


    Page<Student> search1(Pageable pageable);

    Page<Student> searchAll(String[] types, String keyword, Pageable pageable);

    Page<StudentDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
    //Page, Pageable을 작성할 땐 domain에서 불러온다



}
