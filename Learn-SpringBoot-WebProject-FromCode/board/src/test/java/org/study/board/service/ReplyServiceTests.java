package org.study.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.study.board.dto.ReplyDTO;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {    // getList()를 테스트하는 기능만을 우선적으로 작성
    @Autowired
    private ReplyService replyService;

    @Test
    public void testGetList() {
        Long bno = 100L;    // 데이터베이스에 존재하는 번호
        List<ReplyDTO> replyDTOList = replyService.getList(bno);
        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
    }
}
