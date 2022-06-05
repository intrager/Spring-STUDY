package org.study.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.study.guestbook.entity.Guestbook;
import org.study.guestbook.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer("user" + (i % 10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest() {
        // 존재하는 번호로 테스트
        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if(result.isPresent()) {
            Guestbook guestbook = result.get();

            guestbook.changeTitle("Changed Title...");
            guestbook.changeContent("Changed Content...");

            guestbookRepository.save(guestbook);
        }
    }

    @Test
    public void testQuery1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        /*
            가장 먼저 동적으로 처리하기 위해서 QDomain을 얻어옴. QDomain 클래스를 이용하면
            엔티티 클래스에 선언된 title, content같은 필드들을 변수로 활용할 수 있음.
         */
        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        /*
            BooleanBuilder는 where문에 들어가는 조건들을 넣어주는 컨테이너라고 간주하면 됨.
         */
        BooleanBuilder builder = new BooleanBuilder();

        /*
            원하는 조건은 필드 값과 같이 결합해서 생성함.
            BooleanBuilder 안에 들어가는 값은 com.querydsl.core.types.Predicate 타입이어야 함.
         */
        BooleanExpression expression = qGuestbook.title.contains(keyword);

        /*
            만들어진 조건은 where문에 and나 or같은 키워드와 결합시킴.
         */
        builder.and(expression);

        /*
            BooleanBuilder는 GuestbookRepository에 추가된
            QuerydslPredicateExecutor 인터페이스의 findAll()을 사용할 수 있음.
         */
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery2() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
        BooleanExpression exContent = qGuestbook.content.contains(keyword);
        // BooleanExpression을 결합하는 부분
        BooleanExpression exAll = exTitle.or(exContent);
        // 결합한 것을 BooleanBuilder에 추가
        builder.and(exAll);
        // 이후에 'gno가 0 초과다'라는 조건 추가
        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });

    }
}
