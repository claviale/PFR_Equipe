package fr.patedor.PFR_Equipe.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.patedor.PFR_Equipe.dto.ReservationDTO;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.mapper.ReservationMapper;
import fr.patedor.PFR_Equipe.mapper.RestaurantMapper;
import fr.patedor.PFR_Equipe.service.ReservationService;
import fr.patedor.PFR_Equipe.service.RestaurantService;
import fr.patedor.PFR_Equipe.service.TableRestaurantService;
import fr.patedor.PFR_Equipe.service.UtilisateurService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	TableRestaurantService tableRestaurantService;
	
    @Autowired
    RestaurantService restaurantService;
	
    @Autowired
    RestaurantMapper restaurantMapper;
    
    @Autowired
    ReservationService reservationService;
    
    @Autowired
    ReservationMapper reservationMapper;
    
    @GetMapping("/{idRestaurant}")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsForRestaurant(
            @PathVariable Integer idRestaurant) {
        
        List<ReservationDTO> reservations = reservationService.getAllReservationsForRestaurant(idRestaurant).stream()
        		.map(reservation -> reservationMapper.toDTO(reservation))
        		.collect(Collectors.toList());  
        return ResponseEntity.ok(reservations);
    }
    
    @PostMapping("/{idRestaurant}")
    public void addReservation(@RequestBody ReservationDTO reservation, @PathVariable("idRestaurant") Integer idRestaurant) {
		Utilisateur utilisateur = utilisateurService.selectByNom(reservation.getNomClient());
		TableRestaurant tableRestaurant = tableRestaurantService.selectByNumeroTableAndIdRestaurant(reservation.getNumeroTable(), idRestaurant);
    	Optional<Restaurant> restaurant = restaurantService.findById(idRestaurant);
        Reservation nouvelleResa = Reservation.builder()
				.utilisateur(utilisateur)
				.table(tableRestaurant)
				.horaireReservation(reservation.getHoraireReservation())
				.nbPersonne(reservation.getNbPersonne())
				.statut(reservation.getStatut())
				.build();
        restaurant.ifPresent(nouvelleResa::setRestaurant);
        reservationService.create(nouvelleResa);
    }
}