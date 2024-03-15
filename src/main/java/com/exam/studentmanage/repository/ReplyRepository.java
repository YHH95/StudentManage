package com.exam.studentmanage.repository;

import com.exam.studentmanage.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply,Long> {


    @Query("select r from Reply r where r.student.sno = :sno")
    Page<Reply> listOfBoard(Long sno, Pageable pageable);

}
