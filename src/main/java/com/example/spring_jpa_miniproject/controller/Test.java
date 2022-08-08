package com.example.spring_jpa_miniproject.controller;

import javax.persistence.*;


/**
 * 아래와 같이 h2, Oracle 등의 DB 에서는 Sequence 전략을 지원하기 때문에 아래처럼 설정하여 사용이 가능함.
 * DB 사전 쿼리문 : CREATE SEQUENCE MEMBER_SEQ START WITH 1 INCREMENT BY 1;
 * @참고 : https://velog.io/@gillog/JPA-%EA%B8%B0%EB%B3%B8-%ED%82%A4-%EC%83%9D%EC%84%B1-%EC%A0%84%EB%9E%B5IDENTITY-SEQUENCE-TABLE
 */

//@Entity
@SequenceGenerator(
        name = "TEST_SEQ_GENERATOR"
        , sequenceName = "MEMBER_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
public class Test {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TEST_SEQ_GENERATOR"
    )
    private Long id;

}
