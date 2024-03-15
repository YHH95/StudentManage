package com.exam.studentmanage.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentReplyCountDTO {

    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regdate;

    private Long replyCount;
}
