package org.review.moviereview.service;

import org.review.moviereview.dto.ReviewDTO;
import org.review.moviereview.entity.Member;
import org.review.moviereview.entity.Movie;
import org.review.moviereview.entity.Review;

import java.util.List;

public interface ReviewService {
    // 영화의 모든 리뷰를 가져온다
    List<ReviewDTO> getListOfMovie(Long mno);

    // 영화 리뷰 추가
    Long register(ReviewDTO movieReviewDTO);

    // 특정 영화리뷰 수정
    void modify(ReviewDTO movieReviewDTO);

    // 영화 리뷰 삭제
    void remove(Long rno);

    default Review dtoToEntity(ReviewDTO movieReviewDTO) {
        Review movieReview = Review.builder()
                .rno(movieReviewDTO.getRno())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(Member.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();
        return movieReview;
    }

    default ReviewDTO entityToDTO(Review movieReview) {
        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                .rno(movieReview.getRno())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getMid())
                .nickname(movieReview.getMember().getNickname())
                .email(movieReview.getMember().getEmail())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate(movieReview.getModDate())
                .build();

        return movieReviewDTO;
    }
}
