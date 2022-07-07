package com.study.auth.repository;

import com.study.auth.entity.MemoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {

    @EntityGraph(attributePaths = "writer", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from MemoEntity m where m.mno = :mno")
    Optional<MemoEntity> getWithWriter(Long mno);

    // 작성자에 대한 처리 -> @EntityGraph
    @EntityGraph(attributePaths = {"writer"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from MemoEntity m where m.writer.email = :email")
    List<MemoEntity> getList(String email);
}
