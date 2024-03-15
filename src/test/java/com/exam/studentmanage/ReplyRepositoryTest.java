package com.exam.studentmanage;

import com.exam.studentmanage.entity.Reply;
import com.exam.studentmanage.entity.Student;
import com.exam.studentmanage.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {


    @Autowired
    private ReplyRepository replyRepository;


    @Test
    public void testInsert(){

        Long sno = 3L;

        Student student = Student.builder().sno(sno).build();

        for(int i=0; i<5; i++){
            Reply reply = Reply.builder().student(student).replyText(i+"번째"+"댓글").repler(i+"번째"+"댓글러").build();
            replyRepository.save(reply);
        }
    }

    @Test
    public void testList(){

        Long sno = 3L;
        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Reply> replyPage = replyRepository.listOfBoard(sno,pageable);

        log.info(replyPage);
        replyPage.getContent().forEach(reply -> log.info(reply));
    }

    @Test
    public void testUpdate(){
        Long sno = 3L;
        Long rno = 2L;
        Student student = Student.builder().sno(sno).build();
        Reply reply = Reply.builder().rno(rno).student(student).replyText("수정").repler("수정된자").build();
        replyRepository.save(reply);
    }

    @Test
    public void readTest(){
        Long rno = 2L;
        Optional<Reply> replyOptional = replyRepository.findById(rno);
        Reply reply = replyOptional.orElseThrow();

        log.info(reply);
    }

    @Test
    public void remove(){
        Long rno = 2L;
        replyRepository.deleteById(rno);
    }

}