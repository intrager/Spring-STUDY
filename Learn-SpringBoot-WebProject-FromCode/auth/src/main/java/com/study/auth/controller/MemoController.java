package com.study.auth.controller;

import com.study.auth.dto.MemoDTO;
import com.study.auth.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/memos/")
public class MemoController {
    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> register(@RequestBody MemoDTO memoDTO) {
        log.info("--------------------register--------------------");
        log.info(memoDTO);

        Long mno = memoService.register(memoDTO);
        return new ResponseEntity<>(mno, HttpStatus.OK);
    }

    @GetMapping(value = "/{mno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemoDTO> read(@PathVariable("mno") Long mno) {
        log.info("-------------------read--------------------");
        log.info(mno);

        return new ResponseEntity<>(memoService.get(mno), HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MemoDTO>> getList(String email) {
        log.info("------------------get list-----------------");
        log.info(email);

        return new ResponseEntity<>(memoService.getAllWithWriter(email), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{mno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("mno") Long mno) {
        log.info("-----------------remove---------------------");
        log.info(mno);

        memoService.remove(mno);

        return new ResponseEntity<>("removed", HttpStatus.OK);
    }

    @PutMapping(value = "/{mno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody MemoDTO memoDTO) {
        log.info("----------------modify-----------------------");
        log.info(memoDTO);

        memoService.modify(memoDTO);

        return new ResponseEntity<>("modified", HttpStatus.OK);
    }
}
