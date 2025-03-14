package fr.patedor.PFR_Equipe.repository;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("FROM Utilisateur u WHERE u.restaurant.id = :id")
    List<Utilisateur> findFromRestaurant(@Param("id") Integer id);

    Utilisateur findByPrenom(String prenom);

}
