package fr.patedor.PFR_Equipe.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.patedor.PFR_Equipe.dto.TableRestaurantDTO;
import fr.patedor.PFR_Equipe.entity.*;
import fr.patedor.PFR_Equipe.mapper.TableRestaurantMapper;
import fr.patedor.PFR_Equipe.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.patedor.PFR_Equipe.dto.ReservationDTO;
import fr.patedor.PFR_Equipe.mapper.ReservationMapper;
import fr.patedor.PFR_Equipe.mapper.RestaurantMapper;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
    UtilisateurService utilisateurService;
	
	@Autowired
	TableRestaurantService tableRestaurantService;

    @Autowired
    TableRestaurantMapper tableRestaurantMapper;
	
    @Autowired
    RestaurantService restaurantService;
    
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
		Utilisateur client = utilisateurService.selectByNom(reservation.getNomClient());
		TableRestaurant tableRestaurant = tableRestaurantService.selectByNumeroTableAndIdRestaurant(reservation.getNumeroTable(), idRestaurant);
    	Optional<Restaurant> restaurant = restaurantService.findById(idRestaurant);
        Reservation nouvelleResa = Reservation.builder()
				.client(client)
				.table(tableRestaurant)
				.horaireReservation(reservation.getHoraireReservation())
				.nbPersonne(reservation.getNbPersonne())
				.statut(reservation.getStatut())
				.build();
        restaurant.ifPresent(nouvelleResa::setRestaurant);
        reservationService.create(nouvelleResa);
    }

    @GetMapping("/reservation/{idReservation}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Integer idReservation) {
        Reservation reservation = reservationService.getById(idReservation);
        return ResponseEntity.ok(reservationMapper.toDTO(reservation));
    }
    
    //renvoie la liste des tables libres et avec le bon nombre de places
    //pour pouvoir en associer une à une réservation "en attente"
    @GetMapping("/{id_restaurant}/{id_reservation}")
    public ResponseEntity<List<TableRestaurantDTO>> tablesPourResa(@PathVariable("id_restaurant") Integer idRestau,
                                                                   @PathVariable("id_reservation") Integer idResa){
        Reservation resaAValider = reservationService.getById(idResa);

        List<TableRestaurantDTO> tables = tableRestaurantService.getTablesLibres(idRestau, resaAValider.getHoraireReservation())
                .stream()
                .filter(table -> resaAValider.getNbPersonne() <= table.getNbPlaces() + 1)
                .map(table -> tableRestaurantMapper.toDTO(table))
                .collect(Collectors.toList());

        return ResponseEntity.ok(tables);
    }

    //une fois la table sélectionnée, on l'ajoute à la résa et on passe celle-ci en "Confirmée"
    @PutMapping("/{id_restaurant}/{id_reservation}")
    public ResponseEntity<ReservationDTO> accepterReservation(@PathVariable("id_restaurant") Integer idRestau,
                                                              @PathVariable("id_reservation") Integer idResa,
                                                              @RequestBody TableRestaurantDTO table) {
        Reservation reservation = reservationService.getById(idResa);
        TableRestaurant tableAAjouter = tableRestaurantService.selectByNumeroTableAndIdRestaurant(table.getNumeroTable(), idRestau);

        reservation.setStatut("Confirmée");
        reservation.setTable(tableAAjouter);

        reservationService.create(reservation);

        return ResponseEntity.ok(reservationMapper.toDTO(reservation));
    }
}