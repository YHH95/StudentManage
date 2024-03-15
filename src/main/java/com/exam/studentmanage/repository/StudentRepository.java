package com.exam.studentmanage.repository;

import com.exam.studentmanage.entity.Student;
import com.exam.studentmanage.repository.search.StudentSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//레포지토리는 Jpa레포지터리를 상속받는다.들어가는 값 : <엔티티,엔티티 기본키 타입>
public interface StudentRepository extends JpaRepository<Student,Long>, StudentSearch {





}