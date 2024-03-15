package com.exam.studentmanage.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString(exclude = "student")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student_image")

public class StudentImage extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String imgName;

    private String oriImgName;

    private String repimgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    public void setBoardImg(String oriImgName,String imgName){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
    }
}
