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
	ReservationRepository repo;
	
	@Override
	public void create(Reservation reservation) {
		// TODO Auto-generated method stub
		repo.save(reservation);
	}

	@Override
	public List<Reservation> getAllByRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return repo.findAllByRestaurant(restaurant);
	}

	@Override
	public Reservation getById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public Reservation getByTableId(Integer id) {
		// TODO Auto-generated method stub
		return repo.findByTableId(id);
	}
	


}
