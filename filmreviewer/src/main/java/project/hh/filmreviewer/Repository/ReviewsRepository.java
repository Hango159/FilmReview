package project.hh.filmreviewer.Repository;
import project.hh.filmreviewer.Model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends  JpaRepository<Reviews, Long> {
    
}
