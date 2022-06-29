package org.review.moviereview.controller;

import lombok.extern.log4j.Log4j2;
import org.review.moviereview.dto.MovieDTO;
import org.review.moviereview.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/movie")
@Log4j2
public class MovieController {

    private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
        log.info("movieDTO : " + movieDTO);

        Long mno = movieService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg", mno);
        return "redirect:/movie/list";
    }
}
