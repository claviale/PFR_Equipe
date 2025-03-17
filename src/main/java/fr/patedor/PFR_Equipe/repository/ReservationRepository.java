package fr.patedor.PFR_Equipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	List<Reservation> findAllByRestaurant(Restaurant restaurant);
	Reservation findByTableId(Integer tableId);
}
