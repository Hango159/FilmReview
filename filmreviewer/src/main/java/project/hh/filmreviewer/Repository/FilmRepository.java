package project.hh.filmreviewer.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import project.hh.filmreviewer.Model.Films;

public interface FilmRepository extends JpaRepository<Films, Long> {

    
    
}
