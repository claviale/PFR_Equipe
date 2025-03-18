package fr.patedor.PFR_Equipe.repository;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findByNom(String nom);

}
