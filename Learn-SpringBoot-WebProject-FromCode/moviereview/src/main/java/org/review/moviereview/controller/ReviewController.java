package org.review.moviereview.controller;

import lombok.extern.log4j.Log4j2;
import org.review.moviereview.dto.ReviewDTO;
import org.review.moviereview.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
        log.info("----------------------list-----------------------");
        log.info("MNO : " + mno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO) {
        log.info("----------------------add MovieReview-----------------------");
        log.info("reviewDTO : " + reviewDTO);

        Long rno = reviewService.register(reviewDTO);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{rno}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long rno, @RequestBody ReviewDTO reviewDTO) {
        log.info("----------------------modify MovieReview-----------------------" + rno);
        log.info("reviewDTO : " + reviewDTO);

        reviewService.modify(reviewDTO);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{rno}")
    public ResponseEntity<Long> removeReview(@PathVariable Long rno) {
        log.info("----------------------delete MovieReview-----------------------");
        log.info("rno : " + rno);

        reviewService.remove(rno);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }
}
