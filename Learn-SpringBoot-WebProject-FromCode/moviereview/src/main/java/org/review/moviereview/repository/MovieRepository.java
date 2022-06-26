package org.review.moviereview.repository;

import org.review.moviereview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);  // 페이지 처리

    @Query("select m, mi ,avg(coalesce(r.grade,0)), count(r) " +
            "from Movie m left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m " +
            "where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(Long mno);   // 특정 영화 조회회
}

/**
 * [N + 1문제]
 * 1번의 쿼리로 N 개의 데이터를 가져왔는데,
 * N 개의 데이터를 처리하기 위해서 필요한 추가적인 쿼리가 각 N 개에 대해서 수행되는 상황
 * -------
 * 위의 경우 1페이지에 해당하는 10개의 데이터를 가져오는 쿼리 1번과 각 영화의 모든 이미지를 가져오기 위한 10번의 추가적인 쿼리가
 * 실행되는 것임.
 * -------
 * 이렇게 되면 한 페이지를 볼 때마다 11번의 쿼리를 실행하기 때문에 성능에 문제가 생길 수 있음.
 * ==> getListPage에서 max(mi) 를 select하던 것을 mi로 떼어냈음.
 */