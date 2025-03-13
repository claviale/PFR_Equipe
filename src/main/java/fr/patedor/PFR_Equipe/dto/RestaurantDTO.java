package fr.patedor.PFR_Equipe.dto;

import fr.patedor.PFR_Equipe.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private Integer id;
    private String nom;
    private String adresse;

    public RestaurantDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.nom = restaurant.getNom();
        this.adresse = restaurant.getAdresse();
    }

    public Restaurant toEntity() {
        return Restaurant.builder()
                .id(id)
                .nom(nom)
                .adresse(adresse)
                .build();
    }
}
