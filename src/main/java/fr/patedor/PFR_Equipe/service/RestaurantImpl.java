package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.dto.RestaurantDTO;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantImpl implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantDTO> findAll() {
        List<RestaurantDTO> restaurants = new ArrayList<>();
        for (Restaurant cur : restaurantRepository.findAll()){
            restaurants.add(new RestaurantDTO(cur));
        }
        return restaurants;
    }

    @Override
    public Optional<Restaurant> findById(Integer idRestaurant) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(idRestaurant);
        return restaurant;
    }
}
