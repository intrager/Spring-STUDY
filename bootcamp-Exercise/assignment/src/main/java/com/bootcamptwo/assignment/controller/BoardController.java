package com.bootcamptwo.assignment.controller;

import com.bootcamptwo.assignment.domain.BoardVO;
import com.bootcamptwo.assignment.domain.ResultVO;
import com.bootcamptwo.assignment.persistence.BoardMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "board", description = "게시물 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardMapper boardMapper;

    @Operation(summary = "board api", description = "게시판 api 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = BoardVO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @Parameters({
            @Parameter(name = "title", description = "타이틀", example = "과제 연습 중 제목"),
            @Parameter(name = "content", description = "내용", example = "과제 연습 중 내용")
    })

    @ResponseBody
    @GetMapping("")
    public BoardVO getBoard(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content
    ) {
        return new BoardVO();
    }

    @PostMapping("/board")  // /api/board, create
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
    public List<BoardVO> findAllBoard(@RequestParam @Nullable Integer pageNumber, @RequestParam @Nullable Integer pageSize) {
        Integer offset = null;
        if(pageNumber != null && pageSize != null) {
            offset = (pageNumber - 1) * pageSize;
        }
        return boardMapper.findBoard(offset, pageSize);
    }

    @GetMapping("/board/count") // 전체 개수
    public Integer countBoard() {
        return boardMapper.countBoard();
    }

    @PutMapping("/board")
    public ResultVO modifyBoard(@RequestBody BoardVO boardVO) {
        int result = boardMapper.updateBoard(boardVO);
        if(result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }

    @DeleteMapping("/board")
    public ResultVO removeBoard(@RequestParam int id) {
        int result = boardMapper.deleteBoard(id);
        if(result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }
}

/*
클라이언트에서 Pagination 을 구현하기 위해서는
현재 페이지 번호, 페이지 사이즈(크기), 전체 개수 --> 3가지가 필수요소임.
    offset : (현재 페이지 번호 - 1) * 페이지 사이즈  // offset은 0부터 시작
    예) 현재 페이지 번호 : 3, 페이지 사이즈 : 10이면 결과는 20
    전체 개수는 서버로부터 리턴받아야 함.
 */