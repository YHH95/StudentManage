package com.exam.studentmanage.controller;


import com.exam.studentmanage.dto.PageRequestDTO;
import com.exam.studentmanage.dto.PageResponseDTO;
import com.exam.studentmanage.dto.ReplyDTO;
import com.exam.studentmanage.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;


    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Long> register(@RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException{
    //String,Long

        System.out.println("컨트롤러 파라미터로 입력받은 값 : " + replyDTO);


        if (bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        Long rno = replyService.register(replyDTO);
        Map<String,Long> result = new HashMap<>();
        result.put("rno",rno);

        return result;
    }


    @GetMapping(value = "/{rno}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ReplyDTO getRead(@PathVariable("rno") Long rno){


        System.out.println(rno);

        ReplyDTO replyDTO = replyService.readOne(rno);

        return replyDTO;
    }




    @PutMapping(value = "/{rno}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Long> modify(@PathVariable("rno") Long rno,@RequestBody ReplyDTO replyDTO){

        replyDTO.setRno(rno);

        replyService.modify(replyDTO);

        Map<String,Long> result = new HashMap<>();
        result.put("rno",rno);
        return result;
    }


    @DeleteMapping(value = "/{rno}")
    public Map<String,Long> remove(@PathVariable("rno")Long rno){

        log.info(rno+ "번 댓글 삭제됨");
        replyService.delete(rno);

        Map<String,Long> result = new HashMap<>();
        result.put("rno",rno);
        return result;
    }

    @GetMapping(value = "/list/{sno}", consumes = MediaType.ALL_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("sno")Long sno, PageRequestDTO pageRequestDTO){

        log.info(sno);

        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(sno,pageRequestDTO);
        log.info(responseDTO);

        return responseDTO;
    }


}
