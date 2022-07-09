package org.study.bimovie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.study.bimovie.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @EntityGraph(attributePaths = "posterList", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from MovieEntity m")
    Page<MovieEntity> findAll2(Pageable pageable);

    // 원하는 idx 값을 집어넣어서 테스트 해볼 수 있다.
    @Query("select m, p, count(p) from MovieEntity m " +
            "left join PosterEntity p on p.movieEntity = m where p.idx = 1 group by p.movieEntity")
    Page<Object[]> findAll3(Pageable pageable);
}
