package fr.patedor.PFR_Equipe.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.patedor.PFR_Equipe.entity.Carte;
import fr.patedor.PFR_Equipe.entity.Plat;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.repository.PlatRepository;

@Service
public class PlatServiceImpl implements PlatService {
	
	@Autowired
	PlatRepository platRepo;
	
	@Autowired
	RestaurantService restaurantService;
	
	
	@Override
	public List<Plat> getAll() {
		return platRepo.findAll();
	}

	@Override
	public Plat getById(Integer id) {
		return platRepo.findById(id).orElse(null);
	}
	
	@Override
	public Plat getByNom(String nom) {
		return platRepo.findByNom(nom); 
	}

	@Override
	public Map<String, List<Plat>> getPlatsByRestaurant(Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        Carte carte = restaurant.getCarte();
        
        if (carte != null) {
        	Map<String, List<Plat>> platsParCategorie = new HashMap<>();
        	
        	for (Plat plat : carte.getPlats()) {
        		String categorieLibelle = plat.getCategorie().getLibelle();
        		platsParCategorie.computeIfAbsent(categorieLibelle, k -> new ArrayList<>()).add(plat);
        		}
        	return platsParCategorie;
        	}
        return Collections.emptyMap();
	}
}
