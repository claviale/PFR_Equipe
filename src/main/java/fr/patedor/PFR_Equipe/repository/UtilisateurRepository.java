package fr.patedor.PFR_Equipe.repository;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    //Rechercher un utilisateur par son pseudo
    Utilisateur findByPseudo(@Param("pseudo") String pseudo);

    //Rechercher un utilisateur par son pseudo et password
    Utilisateur findByPseudoAndPassword(@Param("pseudo") String pseudo, @Param("password") String password);

    Optional<Utilisateur> findByLogin(String login);
}
