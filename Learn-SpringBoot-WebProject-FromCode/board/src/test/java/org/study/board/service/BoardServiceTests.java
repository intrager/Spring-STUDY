package org.study.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.study.board.dto.BoardDTO;
import org.study.board.dto.PageRequestDTO;
import org.study.board.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        BoardDTO dto = BoardDTO.builder()
                .title("Title - Test")
                .content("Content - Test")
                .writerEmail("test100@induk.ac.kr") // 현재 데이터베이스에 존재하는 이메일
                .build();
        boardService.register(dto);
    }

    @Test
    public void testList() {
       PageRequestDTO pageRequestDTO = new PageRequestDTO();

       PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

       for(BoardDTO boardDTO : result.getDtoList()) {
           System.out.println(boardDTO);
       }
    }

    @Test
    public void testGet() {
        Long bno = 100L;
        BoardDTO boardDTO = boardService.get(bno);

        System.out.println(boardDTO);
    }

    @Test
    public void testRemove() {
        Long bno = 1L;
        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경")
                .content("내용 변경")
                .build();
        boardService.modify(boardDTO);
    }
}
