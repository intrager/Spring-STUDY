package com.bootcamptwo.assignment.persistence;

import com.bootcamptwo.assignment.domain.CommentVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // Annotation을 붙여서 하는 Mapper 연동은 한 개만 될 줄 알았는데 한 개 이상도 된다는 걸 깨달음
public interface CommentMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({"<script>",
            "INSERT INTO comment(content, board_id)",
            "VALUES(#{content}, #{board_id})",
            "</script>"})
    int insertComment(CommentVO commentVO);

    @Select({"<script>",
            "SELECT * FROM comment",
            "WHERE id = #{id}",
            "</script>"})
    CommentVO findOneComment(int id);

    @Select({"<script>",
            "SELECT * FROM comment",
            "order by id desc",
            "</script>"})
    List<CommentVO> findComment(int board_id);

    @Update({"<script>",
            "UPDATE comment",
            "set content = #{content}",
            "WHERE id = #{id}",
            "</script>"})
    int updateComment(CommentVO commentVO);

    // comment?id=2 , { "id" : 2 }
    @Delete({"<script>",
            "DELETE FROM comment",
            "WHERE id = #{id}",
            "</script>"})
    int deleteComment(int id);
}
