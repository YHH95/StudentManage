package com.exam.studentmanage.service;

import com.exam.studentmanage.dto.PageRequestDTO;
import com.exam.studentmanage.dto.PageResponseDTO;
import com.exam.studentmanage.dto.ReplyDTO;

public interface ReplyService {

    public Long register(ReplyDTO replyDTO);

    public ReplyDTO readOne(Long rno);

    public void modify(ReplyDTO replyDTO);

    public void delete(Long rno);



    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);


}
