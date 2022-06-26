package org.review.moviereview.repository;

import org.junit.jupiter.api.Test;
import org.review.moviereview.entity.Member;
import org.review.moviereview.entity.Movie;
import org.review.moviereview.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

    @Test
    public void testGetMovieReviews() {
        Movie movie = Movie.builder().mno(99L).build();
        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(review -> {
            System.out.print(review.getReviewNo());
            System.out.print("\t" + review.getGrade());
            System.out.print("\t" + review.getText());
            System.out.print("\t" + review.getMember().getEmail());
            System.out.println("-----------------------------------------");
        });
        /*
        could not initialize proxy [org.review.moviereview.entity.Member#97] - no Session
        org.hibernate.LazyInitializationException: could not initialize proxy [org.review.moviereview.entity.Member#97] - no Session
         Review 클래스의 Member에 대한 Fetch 방식이 LAZY이기 때문에, 한 번에 Review 객체와 Member 객체를 조회할 수 없어 발생한 문제
         @Transactional을 적용한다고 해도 각 Review 객체의 getMember().getEmail()을 처리할 때마다
         Member 객체를 로딩해야 하는 문제가 있음.
         => 1) @Query를 이용해서 조인 처리
            2) @EntityGraph를 이용해서 Review 객체를 가져올 때 Member 객체를 로딩하는 방법이 있음.
        */
    }
}
/**
 * @EntityGraph는 엔티티의 특정한 속성을 같이 로딩하도록 표시하는 어노테이션
 * 기본적으로 JPA를 이용하는 경우에는 연관 관계의 FATCH 속성값은 LAZY로 지정하는 것이 일반적
 * @EntityGraph는 이러한 상황에서 특정 기능을 수행할 때만 EAGER 로딩을 하도록 지정할 수 있음.
 */