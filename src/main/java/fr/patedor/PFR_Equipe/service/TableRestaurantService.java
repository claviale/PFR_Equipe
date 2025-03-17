package fr.patedor.PFR_Equipe.service;

import java.time.LocalDateTime;
import java.util.List;

import fr.patedor.PFR_Equipe.dto.TableRestaurantDTO;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;

public interface TableRestaurantService {
	
	public List<TableRestaurant> getAllByRestaurantId(Integer idRestaurant);
	
	TableRestaurant selectByNumeroTableAndIdRestaurant(Integer numero, Integer idRestaurant);
	
	List<TableRestaurant> getTablesLibres(Integer idRestaurant, LocalDateTime heureResa);
	
	boolean estLibre(TableRestaurant table, List<Reservation> reservations, LocalDateTime heureResa);
}
