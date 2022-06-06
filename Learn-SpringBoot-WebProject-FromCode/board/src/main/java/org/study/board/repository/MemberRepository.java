package org.study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
