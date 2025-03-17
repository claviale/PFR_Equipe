package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    List<Restaurant> findAll();
    Optional<Restaurant> findById(Integer idRestaurant);
    Restaurant getRestaurantById(Integer idRestaurant);
}
