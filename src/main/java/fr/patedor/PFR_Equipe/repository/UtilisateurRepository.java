package fr.patedor.PFR_Equipe.repository;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("FROM Utilisateur u WHERE u.restaurant.id = :id")
    List<Utilisateur> findFromRestaurant(@Param("id") Integer id);

    Utilisateur findByPrenom(String prenom);

    Optional<Utilisateur> findByLogin(String login);

    Utilisateur findByNom(String nom);
}
