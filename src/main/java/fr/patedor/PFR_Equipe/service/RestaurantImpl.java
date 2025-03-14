package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantImpl implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Integer idRestaurant) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(idRestaurant);
        return restaurant;
    }
}
