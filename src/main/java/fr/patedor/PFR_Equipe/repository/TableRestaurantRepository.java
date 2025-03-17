package fr.patedor.PFR_Equipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Integer> {
	//List<TableRestaurant> findAllByRestaurant(Restaurant restaurant);
}
