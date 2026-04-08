package project.hh.filmreviewer.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import project.hh.filmreviewer.Model.Films;
import project.hh.filmreviewer.Services.FilmService;

@Controller
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping({ "/films" })
    public String getFilms(Model model) {
        model.addAttribute("films", filmService.getAllFilms());
        return "homepage"; // homepage.html
    }

    // 1. Afișează formularul
    @GetMapping("/addfilm")
    public String showAddForm(Model model) {
        // Trimitem un obiect gol de tip Film pe care formularul să îl completeze
        model.addAttribute("film", new Films());
        return "addfilm"; // addfilm.html
    }

    // 2. Procesează datele din formular
    @PostMapping("/save")
    public String saveFilm(@ModelAttribute("film") Films film) {
        filmService.addFilm(film); // Salvează în Supabase prin Repository
        return "redirect:/addfilm"; // Te trimite înapoi cu un mesaj de succes
    }

    @PostMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return "redirect:/films"; // Redirecționează către URL-ul /films
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}
