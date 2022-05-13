package com.bootcamptwo.assignment.controller;

import com.bootcamptwo.assignment.domain.BoardVO;
import com.bootcamptwo.assignment.domain.ResultVO;
import com.bootcamptwo.assignment.persistence.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardMapper boardMapper;

    @PostMapping("/board")  // /api/board
    public ResultVO addBoard(@RequestBody BoardVO boardVO) {    // 요청은 JSON으로 받음
        int result = boardMapper.insertBoard(boardVO);
        // 결과는 ResultVO 타입의 JSON을 리턴
        if(result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }

    @GetMapping("/board/{id}")  // read
    public BoardVO findOne(@PathVariable int id) {
        return boardMapper.findOneBoard(id);
    }

    @GetMapping("/boards")  // read list
    public List<BoardVO> findAllBoard() {
        return boardMapper.findBoard();
    }
}
