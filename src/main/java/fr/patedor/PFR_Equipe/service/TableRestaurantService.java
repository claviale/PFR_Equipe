package fr.patedor.PFR_Equipe.service;

import java.time.LocalDateTime;
import java.util.List;

import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;

public interface TableRestaurantService {
	
	List<TableRestaurant> getAllByRestaurantId(Integer idRestaurant);
	
	TableRestaurant selectByNumeroTableAndIdRestaurant(Integer numero, Integer idRestaurant);
	
	List<TableRestaurant> getTablesLibres(Integer idRestaurant, LocalDateTime heureResa, Integer nbPersonne);
	
	List<TableRestaurant> getTablesLibres(Integer idRestaurant, LocalDateTime heureResa);
	
	List<TableRestaurant> getTablesLibresMaintenant(Integer idRestaurant, Integer nbPersonne);
	
	List<TableRestaurant> getTablesOccupees(Integer idRestaurant);
	
	boolean estLibre(TableRestaurant table, List<Reservation> reservations, LocalDateTime heureResa);

	TableRestaurant getById(Integer id);
}
