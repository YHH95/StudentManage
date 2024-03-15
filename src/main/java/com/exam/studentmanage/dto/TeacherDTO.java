package com.exam.studentmanage.dto;

import jakarta.persistence.Column;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDTO {

    private Long tno;

    private String name;
    private String id;
    private String pw;
    private String subject;
}
