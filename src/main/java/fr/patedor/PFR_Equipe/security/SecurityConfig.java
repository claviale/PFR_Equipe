package fr.patedor.PFR_Equipe.security;

import fr.patedor.PFR_Equipe.repository.EmployeRepository;
import fr.patedor.PFR_Equipe.security.jwt.JwtAuthenticationFilter;
import fr.patedor.PFR_Equipe.service.UtilisateurService;
import jakarta.servlet.Filter;

import java.util.List;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private Filter jwtAuthenticationFilter;


    /**
     * Restriction des URLs selon la connexion utilisateur et leurs rôles
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
            		.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
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
        http.authenticationManager(authenticationManager());

        //Activer le filtre JWT et l'authentication de l'utilisateur
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Session Stateless
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }


	//Instancie un AuthenticationManager dont on précise comment implémenter la méthode authenticate
    @Bean
    public AuthenticationManager authenticationManager() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return  authenticationProvider().authenticate(authentication);
            }
        };
    }

    //Instancie un AuthenticationProvider pour notre AuthenticationManager
    //L'implémentation DaoAuthenticationProvider a besoin d'un UserDetailsService et d'un PasswordEncoder
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //Instancie un userDetailsService, qui est juste un DAO qui a accès uniquement à findByLogin
    //Il sert à récupérer en base l'utilisateur que l'on compare avec les Credentials qui sont dans les objets d'Authentication
    //que l'on passe en paramètre de la méthode authenticate que l'on donne à nos classes d'authentification
    @Bean
    UserDetailsService userDetailsService() {
        return login -> employeRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    //Encodeur de password avec BCrypt, car c'est le standard avec Spring Security
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
	}

}
