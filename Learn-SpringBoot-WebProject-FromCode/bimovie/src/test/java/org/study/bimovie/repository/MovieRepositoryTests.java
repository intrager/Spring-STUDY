package org.study.bimovie.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.study.bimovie.entity.MovieEntity;
import org.study.bimovie.entity.PosterEntity;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MovieRepositoryTests {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testInsert() {
        log.info("testInsert.............");

        MovieEntity movieEntity = MovieEntity.builder().title("극한직업").build();

        movieEntity.addPoster(PosterEntity.builder().pname("극한직업 포스터1.jpg").build());
        movieEntity.addPoster(PosterEntity.builder().pname("극한직업 포스터2.jpg").build());

        movieRepository.save(movieEntity);

        log.info(movieEntity.getMno());
    }

    @Test
    @Transactional
    @Commit
    public void testAddPoster() {
        MovieEntity movieEntity = movieRepository.getById(1L);

        movieEntity.addPoster(PosterEntity.builder().pname("극한직업 포스터3.jpg").build());

        movieRepository.save(movieEntity);
    }

    @Test
    @Transactional
    @Commit
    public void testRemovePoster() {
        MovieEntity movieEntity = movieRepository.getById(1L);

        movieEntity.removePoster(2L);

        movieRepository.save(movieEntity);
    }

    @Test
    public void insertMovies() {
        IntStream.rangeClosed(10, 100).forEach(i -> {   // 10부터 100까지 90개
            MovieEntity movieEntity = MovieEntity.builder().title("고전명작" + i).build();

            movieEntity.addPoster(PosterEntity.builder()
                    .pname("고전명작" + i + "포스터1.jpg").build());
            movieEntity.addPoster(PosterEntity.builder()
                    .pname("고전명작" + i + "포스터2.jpg").build());

            movieRepository.save(movieEntity);
        });
    }

    @Test
    public void testPaging1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<MovieEntity> result = movieRepository.findAll(pageable);

        result.getContent().forEach(m -> {
            log.info(m.getMno());
            log.info(m.getTitle());
            log.info(m.getPosterList().size());
            log.info("---------------------------");
        });
    }

    @Test
    public void testPaging2All() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<MovieEntity> result = movieRepository.findAll2(pageable);

        result.getContent().forEach(m -> {
            log.info(m.getMno());
            log.info(m.getTitle());
            log.info(m.getPosterList());
            log.info("-------------------------");
        });
    }

    @Test
    public void testPaging3All() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.findAll3(pageable);

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));

            log.info("----------------------");
        });
    }
}
