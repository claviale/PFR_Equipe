package fr.patedor.PFR_Equipe.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
	public void create(Reservation reservation) {
		// TODO Auto-generated method stub
    	reservationRepository.save(reservation);
	}
    
    public List<Reservation> getAllReservationsForRestaurant(Integer idRestaurant) {
        return reservationRepository.findAllByRestaurant(idRestaurant, ZonedDateTime.now(ZoneId.of("Europe/Paris")));
    }


	@Override
	public Reservation getById(Integer idReservation) {
		return reservationRepository.findById(idReservation).orElse(null);
	}

	@Override
	public Reservation getByTableIdAndStatutPresent(Integer id) {
		return reservationRepository.findByTableIdAndStatutPresent(id);
	}

	@Override
	public void delete(Reservation reservation) {
		reservationRepository.delete(reservation);
	}
}
