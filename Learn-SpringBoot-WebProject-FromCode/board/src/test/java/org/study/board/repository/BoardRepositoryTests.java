package org.study.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.study.board.entity.Board;
import org.study.board.entity.Member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("test" + i + "@induk.ac.kr").build();

            Board board = Board.builder()
                    .title("Title - " + i)
                    .content("Content - " + i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    @Transactional
    @Test
    public void testRead1() {
        /**
         * could not initialize proxy [org.study.board.entity.Member#test100@induk.ac.kr] - no Session
         * -> 무턱대고 Board Entity에서 writer에다가 Lazy를 걸어뒀을 때 발생. 데이터베이스와 추가적인 연결이 필요하다는 뜻.
         * --> 지연 로딩 방식으로 로딩하기 때문에 board 테이블만 가져와서 System.out.println()을 하는 것은 문제가 없지만,
         * board.getWriter()가 member 테이블을 로딩해야 하는데 이미 데이터베이스와의 연결 상태가 끝나있기 때문이다.
         * ---> @Transactional 을 붙여 속성에 따라 다르게 동작하도록 처리하지만, 기본적으로 필요할 때 다시 데이터베이스와의
         * 연결이 생성됨.
         */
        Optional<Board> result = boardRepository.findById(100L); // DB에 존재하는 번호

        Board board = result.get();
        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    public void testReadWithWriter() {
        Object result = boardRepository.getBoardWithWriter(100L);

        Object[] arr = (Object[]) result;

        System.out.println("------------------------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testGetBoardWithReply() {
        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        for(Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3() {
        Object result = boardRepository.getBoardByBno(100L);
        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearch1() {
        boardRepository.search1();
    }

    @Test
    public void testSearchPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);
    }
}
