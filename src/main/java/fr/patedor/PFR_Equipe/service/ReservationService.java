package fr.patedor.PFR_Equipe.service;

import java.util.List;

import fr.patedor.PFR_Equipe.entity.Reservation;


public interface ReservationService {
	public void create(Reservation reservation);
    public List<Reservation> getAllReservationsForRestaurant(Integer idRestaurant);
	public Reservation getById(Integer idReservation);
	public Reservation getByTableIdAndStatutPresent(Integer id);
	public void delete(Reservation reservation);
}
