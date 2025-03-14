package fr.patedor.PFR_Equipe.service;

import java.util.List;
import java.util.Map;

import fr.patedor.PFR_Equipe.entity.Plat;

public interface PlatService {
	 List<Plat> getAll();
	 Plat getById(Integer id);
	 Plat getByNom(String nom);
	 Map<String, List<Plat>> getPlatsByRestaurant(Integer restaurantId);
}
