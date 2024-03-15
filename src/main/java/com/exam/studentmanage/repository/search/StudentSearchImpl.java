package com.exam.studentmanage.repository.search;

import com.exam.studentmanage.dto.StudentDTO;
import com.exam.studentmanage.entity.QReply;
import com.exam.studentmanage.entity.QStudent;
import com.exam.studentmanage.entity.Student;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

//SearchImpl은 QuerydslRepositorySupport를 먼저 상속받고 StudentSearch를 implements 한다
public class StudentSearchImpl extends QuerydslRepositorySupport implements StudentSearch{
    public StudentSearchImpl() { //만들어진 생성자는 기본생성자로 만들고 super(엔티티.class)로 정의한다
        super(Student.class);
    }

    @Override
    public Page<Student> search1(Pageable pageable){ //하나의 조건만을 검색하는 메소드

        QStudent student = QStudent.student; //querydsl을 사용할 객체 지정

        JPQLQuery<Student> query = from(student); //JPQL은 코드를 통해 쿼리문을 생성할 수 있게 해줌

        query.where(student.name.contains("호")); //select * from student where name like '%호%'

        List<Student> list = query.fetch(); //쿼리를 fetch로 실행한 후 Student엔티티 리스트에 담는다

        long count = query.fetchCount(); //쿼리 실행 결과의 개수를 count에 담는다

        return null;
    }



    @Override
    public Page<Student> searchAll(String[] types, String keyword, Pageable pageable){
        QStudent student = QStudent.student;
        JPQLQuery<Student> query = from(student);
        //코드로 쿼리문을 만들 수 있게 해주는 JPQL

        if((types != null && types.length > 0) && keyword != null ){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            //BooleanBuilde는 쿼리문 안에 () 가 필요한 상황에 사용

            for (String type : types){
                switch (type){
                    case "n" : //타입이 t라면
                        booleanBuilder.or(student.name.contains(keyword)); //keyword와 비교
                        break;
                    case "l":
                        booleanBuilder.or(student.level.contains(keyword));
                        break;
                        case "c":
                        booleanBuilder.or(student.classnum.contains(keyword));
                        break;
                }
            }//for문끝
            query.where(booleanBuilder);
        }//if문 끝

        query.where(student.sno.gt(0L));
        //bno가 0보다 크다면 (and)

        this.getQuerydsl().applyPagination(pageable,query);
        //페이지네이션 적용

        List<Student> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
        //위에서 완성한 값들을 모두 리턴한다.
        //Page<T>타입을 반환해야하지만 스프링 JPA에서 제공하는 PageImpl이라는 클래스로 반환이 가능하다.
        //new PageImpl<>(실제 목록 List, 페이지정보를 가진 pageable, 카운트한 long 변수)
    }





    @Override
    public Page<StudentDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {


        QStudent student = QStudent.student;
        QReply reply = QReply.reply;


        JPQLQuery<Student> query = from(student);
        System.out.println("JPQLQuery<Student> query = from(student) 실행 " + query);

        query.leftJoin(reply).on(reply.student.eq(student));
        System.out.println("query.leftJoin(reply).on(reply.student.eq(student)) 실행 " + query);
        query.groupBy(student);
        System.out.println("query.groupBy(student) 실행 "+query);


        JPQLQuery<StudentDTO> dtoQuery = query.select(Projections.bean(StudentDTO.class,
                student.sno,student.birthdate,student.comment,student.name, student.grade,student.score,
                student.phone,student.phone2,student.classnum,student.level,student.regdate,
                reply.count().as("replyCount")
                ));
        //Projection : JPQL,Querydsl에서 제공하는 기능. 결과를 바로 DTO로 처리하는 기능이다.

        System.out.println("JPQLQuery<StudentDTO> dtoQuery = query.select(Projections.bean(StudentDTO.class,\n" +
                "                student.sno,student.birthdate,student.comment,student.name, student.grade,student.score,\n" +
                "                student.phone,student.phone2,student.classnum,student.level,student.regdate,\n" +
                "                reply.count().as(\"replyCount\")\n" +
                "                )); 실행 : "+dtoQuery);

        if(types != null && types.length > 0 && keyword != null){ //타입과 검색어가 널이 아닐 때
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type : types){ //가져온 types 배열을 향상된 for문으로
                switch (type){
                    case "n": //type의 값이 n라면
                        booleanBuilder.or(student.name.contains(keyword));
                        break;
                    case "l":
                        booleanBuilder.or(student.level.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(student.classnum.contains(keyword));
                        break;
                    case "s":
                        booleanBuilder.or(student.score.contains(keyword));
                        break;
                    case "g":
                        booleanBuilder.or(student.grade.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder); //for문이 끝났으면 쿼리문에 빌더를 넣음

        }
        query.where(student.sno.gt(0L)); //sno값이 0 이상이라면 (and구문)
        System.out.println("검색식까지 포함한 쿼리문 : "+query);

        this.getQuerydsl().applyPagination(pageable,dtoQuery);
        System.out.println("페이징까지 포함한 쿼리문 : "+dtoQuery);

        List<StudentDTO> list = dtoQuery.fetch(); //쿼리 실행 후 리스트에 넣음
        long count = query.fetchCount(); //리스트의 개수를 count에 넣음


        return new PageImpl<>(list,pageable,count);
        //얻은 값들 반환타입에 맞게 리턴한다
    }



}
