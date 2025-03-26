package fr.patedor.PFR_Equipe.security;

import fr.patedor.PFR_Equipe.repository.EmployeRepository;
import fr.patedor.PFR_Equipe.service.UtilisateurService;
import jakarta.servlet.Filter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private Filter jwtAuthenticationFilter;

    /**
     * Restriction des URLs selon la connexion utilisateur et leurs rôles
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    // login (tout le monde y a accès)
                    .requestMatchers("/login").permitAll()

                    // Permettre à l'admin uniquement
                    .requestMatchers("/admin/**").hasAnyAuthority("ADM")

                    // Permettre aux employés uniquement
                    .requestMatchers(HttpMethod.GET, "/tables/**").hasAuthority("EMP")
                    .requestMatchers(HttpMethod.GET, "/reservations/**").hasAuthority("EMP")
                    .requestMatchers(HttpMethod.GET, "/commandes/**").hasAuthority("EMP")
                    .requestMatchers(HttpMethod.GET, "/plats/**").hasAuthority("EMP")

                    .requestMatchers(HttpMethod.POST, "/commandes/**").hasAuthority("EMP")
                    .requestMatchers(HttpMethod.POST, "/commandes").hasAuthority("EMP")
                    .requestMatchers(HttpMethod.POST, "/reservations/**").hasAuthority("EMP")

                    .requestMatchers(HttpMethod.PUT, "/tables/**").hasAuthority("EMP")
                    .requestMatchers(HttpMethod.PUT, "/commandes/**").hasAuthority("EMP")
                    .requestMatchers(HttpMethod.PUT, "/reservations/**").hasAuthority("EMP")

                    .requestMatchers(HttpMethod.DELETE, "/commandes/**").hasAuthority("EMP")

                    // Toutes autres url et méthodes HTTP ne sont pas permises
                    .anyRequest().denyAll();
        });

        // Désactive Cross Site Request Forgery
        // Non préconisé pour les API REST en stateless.
        // Sauf pour POST, PUT, PATCH et DELETE
        http.csrf(csrf -> {
            csrf.disable();
        });


        //Connexion de l'utilisateur
        http.authenticationProvider(authenticationProvider());

        //Activer le filtre JWT et l'authentication de l'utilisateur
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Session Stateless
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //Encodeur de password avec BCrypt, car c'est le standard avec Spring Security
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
