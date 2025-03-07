package fr.patedor.PFR_Equipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.patedor.PFR_Equipe.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {

}
