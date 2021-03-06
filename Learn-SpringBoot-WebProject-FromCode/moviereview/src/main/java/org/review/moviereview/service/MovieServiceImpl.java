package org.review.moviereview.service;

import lombok.extern.log4j.Log4j2;
import org.review.moviereview.dto.MovieDTO;
import org.review.moviereview.dto.PageRequestDTO;
import org.review.moviereview.dto.PageResultDTO;
import org.review.moviereview.entity.Movie;
import org.review.moviereview.entity.MovieImage;
import org.review.moviereview.repository.MovieImageRepository;
import org.review.moviereview.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    public MovieServiceImpl(MovieRepository movieRepository, MovieImageRepository movieImageRepository) {
        this.movieRepository = movieRepository;
        this.movieImageRepository = movieImageRepository;
    }

    @Override
    public Long register(MovieDTO movieDTO) {
        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);
        movieImageList.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });
        return movie.getMno();
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);
        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
                (Movie) arr[0],
                (List<MovieImage>)(Arrays.asList((MovieImage) arr[1])),
                (Double) arr[2],
                (Long) arr[3])
        );
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public MovieDTO getMovie(Long mno) {
        List<Object[]> result = movieRepository.getMovieWithAll(mno);

        Movie movie = (Movie) result.get(0)[0]; // Movie ???????????? ?????? ?????? ?????? - ?????? Row??? ????????? ???

        List<MovieImage> movieImageList = new ArrayList<>(); // ?????? ????????? ???????????? MovieImage?????? ??????

        result.forEach(arr -> {
            MovieImage movieImage = (MovieImage) arr[1];
            movieImageList.add(movieImage);
        });

        Double avg = (Double) result.get(0)[2]; // ?????? ?????? - ?????? Row??? ????????? ???
        Long reviewCount = (Long) result.get(0)[3]; // ?????? ?????? - ?????? Row??? ????????? ???

        return entitiesToDTO(movie, movieImageList, avg, reviewCount);
    }
}
