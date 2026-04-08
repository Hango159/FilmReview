package project.hh.filmreviewer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.hh.filmreviewer.Model.User;
import project.hh.filmreviewer.Repository.UserRepository;

@SpringBootApplication
public class FilmreviewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmreviewerApplication.class, args);
	}

	@Bean
	public CommandLineRunner setupData(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
		return (args) -> {
			// Gestionare ADMIN
			User admin = repository.findByUsername("admin");
			if (admin == null) {
				admin = new User();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin123"));
			}
			// Ne asigurăm că rolul are prefixul ROLE_
			admin.setRole("ROLE_ADMIN");
			repository.save(admin);

			// Gestionare USER
			User user = repository.findByUsername("user");
			if (user == null) {
				user = new User();
				user.setUsername("user");
				user.setPassword(passwordEncoder.encode("user123"));
			}
			user.setRole("ROLE_USER");
			repository.save(user);
		};
	}
}
