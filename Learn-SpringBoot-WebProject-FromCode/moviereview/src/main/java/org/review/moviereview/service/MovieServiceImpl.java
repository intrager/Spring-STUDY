package org.review.moviereview.service;

import lombok.extern.log4j.Log4j2;
import org.review.moviereview.dto.MovieDTO;
import org.review.moviereview.entity.Movie;
import org.review.moviereview.entity.MovieImage;
import org.review.moviereview.repository.MovieImageRepository;
import org.review.moviereview.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
}
