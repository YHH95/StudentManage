package com.exam.studentmanage;


import com.exam.studentmanage.dto.PageRequestDTO;
import com.exam.studentmanage.dto.PageResponseDTO;
import com.exam.studentmanage.dto.ReplyDTO;
import com.exam.studentmanage.entity.Student;
import com.exam.studentmanage.service.ReplyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Test
    public void registerTest(){
        Student student = new Student();
        student.setSno(3L);


        ReplyDTO replyDTO = ReplyDTO.builder().sno(student.getSno()).repler("장구임").replyText("장구내용").build();
        replyService.register(replyDTO);

    }

    @Test
    public void readOneTest(){

        ReplyDTO replyDTO = replyService.readOne(30L);

        log.info(replyDTO);
    }


    @Test
    public void modifyTest(){

        ReplyDTO replyDTO = ReplyDTO.builder().rno(2L).repler("수정된놈").replyText("스ㅜ정내용").build();

        replyService.modify(replyDTO);

        replyDTO = replyService.readOne(replyDTO.getRno());
        log.info(replyDTO);
    }

    @Test

    public void deleteTest(){
        replyService.delete(2L);
    }

    @Test
    public void getListBoardTest(){
        Long bno = 497L;
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        pageRequestDTO.setSize(10);
        pageRequestDTO.setPage(1);

        PageResponseDTO<ReplyDTO> pageResponseDTO = replyService.getListOfBoard(bno,pageRequestDTO);

        log.info(pageResponseDTO.getDtoList());

    }
}
