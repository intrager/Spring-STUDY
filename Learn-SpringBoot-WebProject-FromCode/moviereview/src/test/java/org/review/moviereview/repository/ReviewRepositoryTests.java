package org.review.moviereview.repository;

import org.junit.jupiter.api.Test;
import org.review.moviereview.entity.Member;
import org.review.moviereview.entity.Movie;
import org.review.moviereview.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {
        // 300개의 리뷰를 등록
        IntStream.rangeClosed(1, 300).forEach(r -> {
            // 영화 번호
            Long mno = (long)(Math.random() * 100 + 1);

            // 리뷰어 번호
            Long mid = ((long)(Math.random() * 100) + 1);
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()* 5) + 1)
                    .text("이 영화에 대한 저의 생각은요 ~ " + r)
                    .build();

            reviewRepository.save(review);
        });
    }
}
