package com.exam.studentmanage.dto;


import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;


@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long sno;

    private String name;

    private String level;

    private String classnum;

    private String score; //평균성적

    private String grade; //태도점수

    private String phone;

    private String phone2; //비상연락망

    private String birthdate;

    private LocalDateTime regdate;

    private String comment;
}
