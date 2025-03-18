package fr.patedor.PFR_Equipe.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.patedor.PFR_Equipe.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	@Query("SELECT r FROM Reservation r WHERE r.restaurant.id = :idRestaurant AND r.horaireReservation >= CURRENT_TIMESTAMP ORDER BY r.horaireReservation ASC")
	List<Reservation> findAllByRestaurant(@Param("idRestaurant") Integer idRestaurant);
	
	@Query("SELECT r FROM Reservation r WHERE r.table.idTableRestaurant = :idTableRestaurant")
	Reservation findByTableId(@Param("idTableRestaurant") Integer idTableRestaurant);
	
}
