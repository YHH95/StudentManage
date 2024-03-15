package com.exam.studentmanage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity //엔티티임을 나타내는 이노테이션태그
@Getter //게터
@Setter //세터
@AllArgsConstructor //모든 생성자
@NoArgsConstructor //기본 생성자
@Builder //빌더패턴 사용가능
@ToString //투스트링
public class Student {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //제너레이션 타입 = 아이덴티티(마리아디비에서 기본키)
    private Long sno;

    @Column(length = 30, nullable = true)
    private String name;

    @Column(length = 6, nullable = true)
    private String level;
    @Column(length = 6, nullable = true)
    private String classnum;
    @Column(length = 10, nullable = true)
    private String score; //평균성적
    @Column(length = 10, nullable = true)
    private String grade; //태도점수

    @Column(length = 40, nullable = true)
    private String phone;
    @Column(length = 40, nullable = true)
    private String phone2; //비상연락망
    @Column(length = 20, nullable = true)
    private String birthdate;

    @CreatedDate //테이블에 값을 입력할 때 사용
    @Column(name = "regdate" , updatable = false) //updatable false= 업데이트시에 실행 X
    private LocalDateTime regdate;

    @Column(length = 600)
    private String comment;
}
