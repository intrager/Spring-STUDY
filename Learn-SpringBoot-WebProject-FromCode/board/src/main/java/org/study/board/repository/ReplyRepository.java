package org.study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.study.board.entity.Board;
import org.study.board.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 게시글(Board) 삭제 시 댓글들 삭제제
    @Modifying
    @Query("delete from Reply r where r.board.bno =:bno")
    void deleteByBno(Long bno);

    // 게시글로 댓글 목록 가져오기
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
