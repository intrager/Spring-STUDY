package com.study.devstudy.controller;

import com.study.devstudy.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping(path="/get", params="register")
    public String registerForm() {
        log.info("registerForm");
        return "board/register";
    } // http://localhost:8081/board/register

    @PostMapping(path = "/post", params = "register")
    public String register() {
        log.info("register");
        return "board/list";
    }

    @GetMapping(path = "/get", params = "modify")
    public String modifyForm() {
        log.info("modifyForm");
        return "board/modify";
    } // http://localhost:8081/board/modify

//    @PostMapping(path = "/post", params = "modify")
//    public String modify() {
//        log.info("modify");
//        return "board/list";
//    }

    @GetMapping(path = "/get", params = "remove")
    public String removeForm() {
        log.info("removeForm");
        return "board/remove";
    } // http://localhost:8081/board/remove

    @PostMapping(path = "/post", params = "remove")
    public String remove() {
        log.info("remove");
        return "board/list";
    }

    @GetMapping(path = "/get", params = "list")
    public String list() {
        log.info("list");
        return "board/list";
    } // http://localhost:8081/board/list

    @GetMapping(path = "/get", params = "read")
    public String read() {
        log.info("read");
        return "board/read";
    } // http://localhost:8081/board/read/

    /**
     * 위의 @PathVariable을 빼면 {bno}가 매핑이 안 됨
     * 만약, 경로 변수명{bno}과 메서드 파라미터명 bno이 일치하지 않으면
     * @PathVariable옆에 ("bno")이런 식으로 속성값을 놓으면 됨
     */
//    @GetMapping("/read/{bno}")
//    public String read(@PathVariable("bno") int bno) {
//        log.info("read bno : " + bno);
//        return "board/read";
//    } // http://localhost:8081/board/read/100

//    @PutMapping("/{bno}")
//    public ResponseEntity<String> modify(@PathVariable("bno") int bno, @RequestBody Board board) {
//        log.info("modify");
//        ResponseEntity<String> entity = new ResponseEntity<String>("success", HttpStatus.OK);
//
//        return entity;
//    }

    @PutMapping(path = "/{bno}", headers = "X-HTTP-Method-Override=put")
    public ResponseEntity<String> modifyByHeader(@PathVariable("bno") int bno, @RequestBody Board board) {
        log.info("modifyByHeader");

        ResponseEntity<String> entity = new ResponseEntity<String>("success", HttpStatus.OK);

        return entity;
    }

    @PostMapping("/{bno}")
    public ResponseEntity<String> modify(@PathVariable("bno") int bno, @RequestBody Board board) {
        log.info("modify");

        ResponseEntity<String> entity = new ResponseEntity<String>("success", HttpStatus.OK);

        return entity;
    }

    // consumes 속성값에 "application/json" 미디어 타입을 지정한다.
    @PutMapping(path = "/{bno}", consumes = "application/json")
    public ResponseEntity<String> modifyByJson(@PathVariable("bno") int bno, @RequestBody Board board) {
        log.info("modifyByJson");

        ResponseEntity<String> entity = new ResponseEntity<String>("success", HttpStatus.OK);

        return entity;
    }

    // consumes 속성값에 "application/xml" 미디어 타입을 지정한다.
    @PutMapping(path = "/{bno}", consumes = "application/xml")
    public ResponseEntity<String> modifyByXml(@PathVariable("bno") int bno, @RequestBody Board board) {
        log.info("modifyByXml bno : " + bno);
        log.info("modifyByXml board : " + board);

        ResponseEntity<String> entity = new ResponseEntity<String>("success", HttpStatus.OK);

        return entity;
    }

    // produces 속성값을 지정하지 않으면 기본값인 "application/json"미디어 타입으로 지정된다.
    @GetMapping("/{bno}")
    public ResponseEntity<Board> read(@PathVariable("bno") int bno) {
        log.info("read");

        Board board = new Board();
        board.setTitle("제목");
        board.setContents("내용입니다");
        board.setWriter("홍길동");
        board.setRegDate(LocalDateTime.now());

        ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK);

        return entity;
    }

    // produces 속성값에 "application/json" 미디어 타입을 지정한다.
    @GetMapping(path = "/{bno}", produces = "application/json")
    public ResponseEntity<Board> readToJson(@PathVariable("bno") int bno) {
        log.info("readToJson");

        Board board = new Board();
        board.setTitle("제목");
        board.setContents("내용");
        board.setWriter("홍길동");
        board.setRegDate(LocalDateTime.now());

        ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK);

        return entity;
    }

    // produces 속성값에 "application/xml" 미디어 타입을 지정한다.
    @GetMapping(path = "/{bno}", produces = "application/xml")
    public ResponseEntity<Board> readToXml(@PathVariable("bno") int bno) {
        log.info("readToXml");

        Board board = new Board();

        board.setTitle("제목");
        board.setContents("내용");
        board.setWriter("홍길동");
        board.setRegDate(LocalDateTime.now());

        ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK);

        return entity;
    }
}
