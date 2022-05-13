package com.bootcamptwo.assignment.persistence;

import com.bootcamptwo.assignment.domain.BoardVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {  // 구동 시 이 인터페이스는 인스턴스로 스프링에 등록됨
    @Insert({"<script>",
        "INSERT INTO board(title, content)",
            "VALUES(#{title}, #{content})",
            "</script>"})
    int insertBoard(BoardVO boardVO);
}

/*
java.sql.SQLSyntaxErrorException: (conn=27) Incorrect string value:
--> 테이블의 CHARSET이 utf8_general_ci였는데, 입력 데이터에 식별 못 하는 값이 들어감.
해당 테이블(board, comment)의 charset을 utf8m64로 변경
 */