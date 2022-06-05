package org.study.guestbook.repository;

import org.study.guestbook.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>,
        QuerydslPredicateExecutor<Guestbook>
{
}

/**
 * JPA 쿼리 메서드의 기능과 @Query를 통해서 많은 기능을 구현할 수 있지만,
 * 고정된 형태의 값을 가진다는 단점이 있음
 * --> 단순한 몇 가지의 검색 조건을 만들어야 하는 상황에서는 기본 기능만으로 충분하지만,
 * 복잡한 조합을 이용하는 경우의 ㅜ가 많은 상황에서는 동적으로 쿼리를 생성해서 처리할 수 있는 기능이 필요
 * --> Querydsl을 사용하기로 함. -> 복잡한 검색조건이나 조인, 서브 쿼리 등의 기능도 구현이 가능.
 */