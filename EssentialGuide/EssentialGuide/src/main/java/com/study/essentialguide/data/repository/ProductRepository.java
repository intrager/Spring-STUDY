package com.study.essentialguide.data.repository;

import com.study.essentialguide.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // find by
    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);

    // exists by : 특정 데이터가 존재하는지 확인하는 키워드
    boolean existsByNumber(Long number);

    // count by : 조회 쿼리를 수행한 후 쿼리 결과로 나온 레코드의 개수를 리턴
    long countByName(String name);

    // delete by, remove by : 삭제 쿼리를 수행, 리턴 타입이 없거나 삭제한 횟수를 리턴
    void deleteByNumber(Long number);
    long removeByName(String name);

    /** ...First<number>..., ...Top<number>... : 쿼리를 통해 조회된 결괏값의 개수를 제한.
    * 두 키워드는 동일한 동작을 수행하며, 주제와 By 사이에 위치함.
    * 키워드로 한 번의 동작으로 여러 건을 조회할 때 사용되며, 단건으로 조회하기 위해서는 <number>를 생략하면 됨.
    */
    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);

    // findByNumber 메서드와 동일하게 동작
    Product findByNumberIs(Long number);
    Product findByNumberEquals(Long number);

    Product findByNumberIsNot(Long number);
    Product findByNumberNot(Long number);

    List<Product> findByUpdatedAtNull();
    List<Product> findByUpdatedAtIsNull();
    List<Product> findByUpdatedAtNotNull();
    List<Product> findByUpdatedAtIsNotNull();

    /* (Is)True, (Is)False
     * Product findByisActiveTrue();
     * Product findByisActiveIsTrue();
     * Product findByisActiveFalse();
     * Product findByisActiveIsFalse();
     */

    Product findByNumberAndName(Long number, String name);
    Product findByNumberOrName(Long number, String name);

    List<Product> findByPriceIsGreaterThan(Long price);
    List<Product> findByPriceGreaterThan(Long price);
    List<Product> findByPriceGreaterThanEqual(Long price);
    List<Product> findByPriceIsLessThan(Long price);
    List<Product> findByPriceLessThan(Long price);
    List<Product> findByPriceLessThanEqual(Long price);
    List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

    /* (Is)StartingWith(==StartsWith), (Is)EndingWith(==EndsWith),
     * (Is)Containing(==Contains), (Is)Like
     * 칼럼값에서 일부 일치 여부를 확인하는 조건자 키워드.
     * SQL 쿼리문에서 값의 일부를 포함하는 값을 추출할 때 사용하는 '%' 키워드와 동일한 역할을 하는 키워드.
     * 자동으로 생성되는 SQL문을 보면 Containing 키워드는 문자열의 양 끝.
     * StartingWith 키워드는 문자열의 앞, EndingWith 키워드는 문자열의 끝에 '%'가 배치됨.
     * Like 키워드는 코드 수준에서 메서드를 호출하면서 전달하는 값에 %를 명시적으로 입력
     */

    List<Product> findByNameLike(String name);
    List<Product> findByNameIsLike(String name);
    List<Product> findByNameContains(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameIsContaining(String name);

    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameIsStartingWith(String name);

    List<Product> findByNameEndsWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameIsEndingWith(String name);

    // 쿼리 메서드의 정렬 처리
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);

    // 매개변수를 활용한 쿼리 정렬
    List<Product> findByName(String name, Sort sort);

    // 페이징 처리를 위한 쿼리 메서드 예시
    Page<Product> findByName(String name, Pageable pageable);

    // @Query 어노테이션을 사용하는 메서드
    @Query("SELECT p FROM Product AS p WHERE p.name = ?1")
    List<Product> findByName(String name);

    // @Query 어노테이션과 @Param 어노테이션을 사용한 메서드
    @Query("select p from Product p where p.name = :name")
    List<Product> findByNameParam(@Param("name") String name);

    // 특정 칼럼만 추출하는 쿼리
    @Query("select p.name, p.price, p.stock from Product p where p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);
}
