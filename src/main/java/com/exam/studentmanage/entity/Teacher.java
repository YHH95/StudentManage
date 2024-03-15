package com.exam.studentmanage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 60, nullable = false)
    private String id;
    @Column(length = 100, nullable = false)
    private String pw;
    @Column(length = 30, nullable = false)
    private String subject;
}
