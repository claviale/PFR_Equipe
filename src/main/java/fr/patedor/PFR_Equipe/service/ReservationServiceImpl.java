package fr.patedor.PFR_Equipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;
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
        return reservationRepository.findAllByRestaurant(idRestaurant);
    }

	@Override
	public List<Reservation> getAllByRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation getByTableId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
