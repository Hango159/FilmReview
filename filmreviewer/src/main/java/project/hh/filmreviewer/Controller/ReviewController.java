package project.hh.filmreviewer.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.hh.filmreviewer.Model.Reviews;
import project.hh.filmreviewer.Model.Films;
import project.hh.filmreviewer.Services.ReviewService;
import project.hh.filmreviewer.Services.FilmService;
import project.hh.filmreviewer.Services.UserService;
import project.hh.filmreviewer.Model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import java.security.Principal;
import java.time.LocalDate;

@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final FilmService filmService;
    private final UserService userService;

    public ReviewController(ReviewService reviewService, FilmService filmService, UserService userService) {
        this.reviewService = reviewService;
        this.filmService = filmService;
        this.userService = userService;
    }

    @GetMapping("/reviews")
    public String getReviews(@RequestParam(value = "filmId", required = false) Long filmId, Model model) {
        Reviews review = new Reviews();
        if (filmId != null) {
            Films film = filmService.getFilmById(filmId);
            review.setFilm(film);
        }
        model.addAttribute("review", review);
        return "reviews"; // reviews.html
    }

    // 2. Procesează datele din formular
    @PostMapping("/savereview")
    public String saveReview(@ModelAttribute("review") Reviews review, Principal principal) {
        if (review.getReviewDate() == null) {
            review.setReviewDate(LocalDate.now());
        }
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            review.setUser(user);
        }
        reviewService.addReview(review);
        return "redirect:/films";
    }

    @PostMapping("/deletereview/{id}")
    public String deleteReview(@PathVariable Long id, Principal principal) {
        Reviews review = reviewService.getReviewById(id);
        if (review != null && principal != null) {
            User currentUser = userService.findByUsername(principal.getName());

            boolean isAdmin = "ROLE_ADMIN".equals(currentUser.getRole());
            boolean isOwner = review.getUser() != null && review.getUser().getUsername().equals(principal.getName());

            if (isAdmin || isOwner) {
                reviewService.deleteReview(id);
            }
        }
        return "redirect:/films";
    }
}