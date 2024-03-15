package com.exam.studentmanage.repository;

import com.exam.studentmanage.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
