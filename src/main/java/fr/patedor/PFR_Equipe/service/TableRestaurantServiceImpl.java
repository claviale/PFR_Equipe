package fr.patedor.PFR_Equipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;
import fr.patedor.PFR_Equipe.repository.ReservationRepository;
import fr.patedor.PFR_Equipe.repository.TableRestaurantRepository;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {

	@Autowired
	TableRestaurantRepository repo;
	
	@Override
	public List<TableRestaurant> getAllByRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return repo.findAllByRestaurant(restaurant);
	}

	@Override
	public TableRestaurant selectByNumeroTableAndIdRestaurant(Integer numero, Integer idRestaurant) {
		return repo.findByNumeroTableAndIdRestaurant(numero, idRestaurant);
	}
	
	

}
