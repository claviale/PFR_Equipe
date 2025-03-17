package fr.patedor.PFR_Equipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.patedor.PFR_Equipe.entity.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {
	Plat findByNom(String nom);
}
