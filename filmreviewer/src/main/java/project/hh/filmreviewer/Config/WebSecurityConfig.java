package project.hh.filmreviewer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // FOARTE IMPORTANT: Permitem tuturor accesul la pagina de login și resurse
                        // statice
                        .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                        // Doar ADMIN poate adăuga sau șterge filme
                        .requestMatchers("/addfilm", "/save", "/delete/**").hasRole("ADMIN")
                        // Utilizatorii logați pot vedea lista de filme
                        .requestMatchers("/films").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login") // Pagina de logare personalizată
                        .defaultSuccessUrl("/films", true) // Unde te trimite după logare reușită
                        .permitAll())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
}
