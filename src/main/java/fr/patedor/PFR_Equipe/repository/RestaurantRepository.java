package fr.patedor.PFR_Equipe.repository;

import fr.patedor.PFR_Equipe.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
