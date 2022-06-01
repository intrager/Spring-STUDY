package org.study.ex2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.study.ex2.entity.Memo;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // Spring JPA는 인터페이스 선언만으로도 자동으로 스프링의 빈(bean)으로 동록됨.
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    void deleteMemoByMnoLessThan(Long num);

    /**
     *   JPQL 사용
     */

    @Query("select m from Memo m order by m.mno desc")
    List<Memo> getListDesc();

    /** @Query의 파라미터 바인딩
     * '?1, ?2'와 1부터 시작하는 파라미터의 순서를 이용하는 방식
     * ':xxx'와 같이 ':파라미터 이름'을 활용하는 방식
     * ':#{}'과 같이 자바 빈 스타일을 이용하는 방식
     */
    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :memoText where m.mno = :mno ")
    int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);

    /** @Query의 파라미터 바인딩
     * ':'을 이용할 때 여러 개의 파라미터를 전달하면서 복잡해 질 경우가 있다고 생각되면
     * ':#'을 이용해서 객체를 사용할 수 있음
     */
    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :#{#param.memoText} where m.mno = :#{#param.mno} ")
    int updateMemosText(@Param("param") Memo memo);

    /** @Query와 페이징 처리
     * 쿼리 메서드와 마찬가지로 @Query를 이용하는 경우에도 Pageable 타입의 파라미터를 적용하면
     * 페이징 처리와 정렬에 대한 부분을 작성하지 않을 수 있음.
     * 리턴 타입을 Page<엔티티 타입>으로 지정하는 경우에는 count를 처리하는 쿼리를 적용할 수 있음.
     * 따라서, 밑에 있는 소스처럼 countQuery라는 속성을 적용해 주고 Pageable 타입의 파라미터를 전달하면 됨.
     */
    @Query(value = "select m from Memo m where m.mno > :mno",
            countQuery = "select count(m) from Memo m where m.mno > :mno")
    Page<Memo> getListWithQuery(Long mno, Pageable pageable);

    /** Object[] 리턴
     * 쿼리 메서드의 경우에는 엔티티 타입의 데이터만을 추출하지만, @Query를 이용하는 경우에는
     * 현재 필요한 데이터만을 Object[]의 형태로 선별적으로 추출할 수 있다는 것이 @Query의 장점.
     */
    @Query(value = "select m.mno, m.memoText, CURRENT_DATE from Memo m where m.mno > :mno",
            countQuery = "select count(m) from Memo m where m.mno > :mno")
    Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);

    /** Native SQL 처리
     * 복잡한 JOIN 구문 등을 처리하기 위해서, '데이터베이스에 독립적으로 구현이 가능한' JPA의 장점을 희생하는 대신
     * 데이터베이스 고유의 SQL 구문을 그대로 활용하면 됨.
     */
    @Query(value = "select * from memo where mno > 0", nativeQuery = true)
    List<Object[]> getNativeResult();
}
