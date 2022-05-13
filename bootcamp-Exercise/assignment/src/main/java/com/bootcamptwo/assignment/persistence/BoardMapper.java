package com.bootcamptwo.assignment.persistence;

import com.bootcamptwo.assignment.domain.BoardVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
    xml 구문 대신 <script>태그를 이용함
 */
@Mapper
public interface BoardMapper {  // 구동 시 이 인터페이스는 인스턴스로 스프링에 등록됨
    @Insert({"<script>",
        "INSERT INTO board(title, content)",
            "VALUES(#{title}, #{content})",
            "</script>"})
    int insertBoard(BoardVO boardVO);

    @Select({"<script>",
            "SELECT * from board",
            "order by id desc",
            "<if test='offset != null and pageSize != null'>",
            "LIMIT #{offset}, #{pageSize}",
            "</if>",
            "</script>"})
    List<BoardVO> findBoard(Integer offset, Integer pageSize);

    @Select({"<script>",
            "SELECT * from board",
            "where id = #{id}",
            "</script>"})
    BoardVO findOneBoard(int id);

    @Select({"<script>",
            "SELECT count(*) from board",
            "</script>"})
    int countBoard();

    @Update({"<script>",
            "UPDATE board",
            "<trim prefix='set' suffixOverrides=','>",
            "<if test='title != null'>title = #{title},</if>",
            "<if test='content != null'>content = #{content},</if>",
            "</trim>",
            "WHERE id = #{id}",
            "</script>"})
    int updateBoard(BoardVO boardVO);

    @Delete({"<script>",
            "DELETE FROM board",
            "WHERE id = #{id}",
            "</script>"})
    int deleteBoard(int id);
}

/*
    java.sql.SQLSyntaxErrorException: (conn=27) Incorrect string value:
    --> 테이블의 CHARSET이 utf8_general_ci였는데, 입력 데이터에 식별 못 하는 값이 들어감.
    해당 테이블(board, comment)의 charset을 utf8m64로 변경
*/

/*
    org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'PUT' not supported
    --> #[title} 에서  #{title}로 오타 수정
*/