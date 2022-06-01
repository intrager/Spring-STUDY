package org.study.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.study.ex2.entity.Memo;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {
    /**
     * 실제 MemoRepository가 정상적으로 스프링에서 처리되고 의존성 주입에 문제가 없는지 먼저 확인
     */
    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    @Test // Insert
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
    }

    @Test // Select
    public void testSelect() {

        // 데이터베이스에 존재하는 mno
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);   // 데이터베이스 먼저 이용
        System.out.println("=======================================");
        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test // Update
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
        System.out.println(memoRepository.save(memo));
    }

    @Test // Delete
    public void testDelete() {
        Long mno = 100L;
        memoRepository.deleteById(mno);
    }

    @Test // Pagination
    public void testPageDefault() {
        // 1페이지 10개
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println(result);

        System.out.println("--------------------------------------------------------");
        System.out.println("Total Pages : " + result.getTotalPages());  // 총 몇 페이지
        System.out.println("Total Count : " + result.getTotalElements());  // 전체
        System.out.println("Page Number : " + result.getNumber());  // 현재 페이지 번호
        System.out.println("Page Size : " + result.getSize());  // 페이지당 데이터 개수
        System.out.println("has next page? : " + result.hasNext()); // 다음 페이지 존재 여부
        System.out.println("first page? : " + result.isFirst());    // 시작 페이지(0) 여부
        System.out.println("--------------------------------------------------------");
        for(Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test // sort
    public void testSort() {
        Sort sort1 = Sort.by("mno").descending(); // descending()을 붙였으므로 내림차순 정렬
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);    // and를 이용한 연결
        Pageable pageable = PageRequest.of(0, 10, sort1);   // 결합된 정렬 조건 사용
        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    public void testQueryMethods() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        for(Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);
        result.get().forEach(memo -> System.out.println(memo));
    }

    /**
     * @Transactional 과 @Commit은 deleteBy...인 경우
     * 우선은 'select'문으로 해당 엔티티 객체들을 가져오는 작업과 각 엔티티를 삭제하는 작업이 같이 이루어지기 때문에 사용
     */
    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteMemoByMnoLessThan(10L);
    }
}
