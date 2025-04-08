package fr.patedor.PFR_Equipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.patedor.PFR_Equipe.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
	List<Commande> findByStatut(String statut);
	Commande findByReservation_IdReservation(Integer idReservation);
}
