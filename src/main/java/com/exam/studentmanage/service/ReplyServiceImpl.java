package com.exam.studentmanage.service;


import com.exam.studentmanage.dto.PageRequestDTO;
import com.exam.studentmanage.dto.PageResponseDTO;
import com.exam.studentmanage.dto.ReplyDTO;
import com.exam.studentmanage.entity.Reply;
import com.exam.studentmanage.entity.Student;
import com.exam.studentmanage.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;


    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO,Reply.class);

        Student student=new Student();
        student.setSno(replyDTO.getSno());
        reply.setStudent(student);

        Long rno = replyRepository.save(reply).getRno();

        return rno;
    }

    @Override
    public ReplyDTO readOne(Long rno) {

        Optional<Reply> reply = replyRepository.findById(rno);
        Reply reply1 = reply.orElseThrow();

        ReplyDTO replyDTO = modelMapper.map(reply1,ReplyDTO.class);

        return replyDTO;
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Reply reply=modelMapper.map(replyDTO,Reply.class); //변환
        reply.setStudent(Student.builder().sno(replyDTO.getSno()).build()); //sno 추가
        System.out.println(reply);
        replyRepository.save(reply); //수정
    }

    @Override
    public void delete(Long rno) {

        replyRepository.deleteById(rno);

    }



    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long sno, PageRequestDTO pageRequestDTO) {
        //Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1,pageRequestDTO.getSize(), Sort.by("rno").descending());
        Pageable pageable = pageRequestDTO.getPageable("rno");
        Page<Reply> replyPage = replyRepository.listOfBoard(sno,pageable);

        List<Reply> replyList = replyPage.getContent();
        List<ReplyDTO> DTOList = replyList.stream().map(reply -> modelMapper.map(reply,ReplyDTO.class)).collect(Collectors.toList());

        PageResponseDTO<ReplyDTO> pageResponseDTO = new PageResponseDTO<>(pageRequestDTO, DTOList, (int) replyPage.getTotalElements());

        return pageResponseDTO;
    }
}
