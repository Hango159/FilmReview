package project.hh.filmreviewer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.hh.filmreviewer.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
