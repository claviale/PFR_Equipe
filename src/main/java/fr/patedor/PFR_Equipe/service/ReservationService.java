package fr.patedor.PFR_Equipe.service;

import java.util.List;

import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.Restaurant;


public interface ReservationService {
	public void create(Reservation reservation);
	public List<Reservation> getAllByRestaurant(Restaurant restaurant);
	public Reservation getById(Integer id);
	public Reservation getByTableId(Integer id);


}
