package fr.patedor.PFR_Equipe.mapper;

import fr.patedor.PFR_Equipe.dto.RestaurantDTO;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantDTO toDTO(Restaurant restaurant) {
        RestaurantDTO result = new RestaurantDTO();
        result.setId(restaurant.getIdRestaurant());
        result.setNom(restaurant.getNom());
        result.setAdresse(restaurant.getAdresse());
        return result;
    }

    public Restaurant toEntity(RestaurantDTO restaurantDTO) {
        return Restaurant.builder()
                .idRestaurant(restaurantDTO.getId())
                .nom(restaurantDTO.getNom())
                .adresse(restaurantDTO.getAdresse())
                .build();
    }
}
