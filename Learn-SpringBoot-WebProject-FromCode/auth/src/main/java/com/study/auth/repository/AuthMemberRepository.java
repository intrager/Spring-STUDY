package com.study.auth.repository;

import com.study.auth.entity.AuthMemberEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthMemberRepository extends JpaRepository<AuthMemberEntity, String> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from AuthMemberEntity m where m.fromSocial = :social and m.email =:email")
    Optional<AuthMemberEntity> findByEmail(String email, boolean social);
}
