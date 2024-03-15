package com.exam.studentmanage.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageRequestDTO {

    //컨트롤러에서 값을 받을 때 각종 정보를 전부 받는 객체


    @Builder.Default //빌더를 통해 객체를 생성 할 때 기본값임을 의미함
    private int page = 1; //페이지 값
    
    @Builder.Default
    private Integer size = 10; //페이지에 표시되는 항목 사이즈

    private String type; //검색 종류

    private String keyword; //검색어

    private String link; //

    public String[] getTypes(){ //문자열로 받은 type 을 배열로 변환한다.
        if(type==null || type.isEmpty()){ //type이 null이거나 비어있을 경우
            return null;
        }
        return type.split(""); //split = 문자열을 문자로 이루어진 배열로 변경한다.
    }

    public Pageable getPageable(String... props){//페이지어블을 만드는 메소드
        //String... props : 문자열의 가변인자. 여러개의 문자열을 인수로 받을 수 있음.

        return PageRequest.of(this.page-1,this.size, Sort.by(props).descending());
    }




    public String getLink() { // 검색 조건, 페이징 조건 등을 문자열로 구성하는 메소드를 정의합니다.
        if (link == null) { // link 변수가 null인 경우에만 아래의 작업을 수행합니다.
            StringBuilder builder = new StringBuilder(); // StringBuilder 객체를 생성합니다.
            builder.append("page=" + this.page); // 페이지 번호를 StringBuilder에 추가합니다.
            if (type != null && type.length() > 0) { // 검색타입이 null이 아니고 길이가 0보다 큰 경우에만 아래의 작업을 수행합니다.
                builder.append("&type=" + type); // 타입을 StringBuilder에 추가합니다.
            }
            if (keyword != null) { // keyword가 null이 아닌 경우에 아래의 작업을 수행합니다.
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "utf-8")); // 키워드를 UTF-8로 인코딩하여 StringBuilder에 추가합니다.
                } catch (UnsupportedEncodingException e) { // 인코딩 과정에서 예외가 발생할 경우를 처리합니다.
                    e.printStackTrace(); // 예외의 스택 트레이스를 출력합니다.
                }
            }
            link = builder.toString(); // StringBuilder를 문자열로 변환하여 link 변수에 저장합니다.
        }
        return link; // link 변수를 반환합니다.
    }

}


















