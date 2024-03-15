package com.exam.studentmanage.entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "Reply", indexes = {@Index(name = "idx_reply_student_sno", columnList = "student_sno")})
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "student")
//Reply를 출력할 때 Student를 같이 출력하도록 해야하므로 exclude 해야한다
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne (fetch = FetchType.LAZY)
    private Student student;

    private String replyText;
    private String repler;


    public void changeText(String replyText){
        this.replyText = replyText;
    }


}
