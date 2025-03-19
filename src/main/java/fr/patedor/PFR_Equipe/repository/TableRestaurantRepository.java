package fr.patedor.PFR_Equipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.patedor.PFR_Equipe.entity.TableRestaurant;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Integer> {

	@Query("SELECT t FROM TableRestaurant t WHERE t.restaurant.id = :restaurantId")
	List<TableRestaurant> findAllByRestaurantId(@Param("restaurantId") Integer restaurantId);
	
	@Query("SELECT t FROM TableRestaurant t WHERE t.numeroTable = :numeroTable AND t.restaurant.id = :idRestaurant")
	TableRestaurant findByNumeroTableAndIdRestaurant(@Param("numeroTable") Integer numeroTable, @Param("idRestaurant") Integer idRestaurant);
	
	@Query("SELECT t FROM TableRestaurant t WHERE t.restaurant.id = :idRestaurant AND t.nbPlaces + 1 >= :nbPersonne")
	List<TableRestaurant> findByNbPlacesAndIdRestaurant(@Param("idRestaurant") Integer idRestaurant, @Param("nbPersonne") Integer nbPersonne);

}
