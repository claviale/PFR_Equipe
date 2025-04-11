package fr.patedor.PFR_Equipe.repository;


import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.patedor.PFR_Equipe.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	@Query("SELECT r FROM Reservation r WHERE r.restaurant.id = :idRestaurant AND r.horaireReservation >= :currentDateTime ORDER BY r.horaireReservation ASC")
	List<Reservation> findAllByRestaurant(@Param("idRestaurant") Integer idRestaurant, @Param("currentDateTime") ZonedDateTime currentDateTime);
	
	@Query("SELECT r FROM Reservation r WHERE r.table.idTableRestaurant = :idTableRestaurant AND r.statut = 'Présent'")
	Reservation findByTableIdAndStatutPresent(@Param("idTableRestaurant") Integer idTableRestaurant);
}
