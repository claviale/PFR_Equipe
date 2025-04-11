package fr.patedor.PFR_Equipe.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.patedor.PFR_Equipe.dto.ReservationDTO;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.mapper.ReservationMapper;
import fr.patedor.PFR_Equipe.service.ReservationService;
import fr.patedor.PFR_Equipe.service.RestaurantService;
import fr.patedor.PFR_Equipe.service.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.patedor.PFR_Equipe.dto.TableRestaurantDTO;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.mapper.TableRestaurantMapper;
import fr.patedor.PFR_Equipe.service.TableRestaurantServiceImpl;
import fr.patedor.PFR_Equipe.service.UtilisateurService;

@RestController
@RequestMapping("/tables")
public class TableRestaurantController {

    @Autowired
    private TableRestaurantServiceImpl tableService;
    
    @Autowired
    TableRestaurantMapper tableMapper;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationMapper reservationMapper;

    @Autowired
    RestaurantService restaurantService;
    
    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("/libres/{idRestaurant}")
    public List<TableRestaurantDTO> getTablesLibres(
            @PathVariable Integer idRestaurant,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime heureResa,
            @RequestParam Integer nbPersonne) {
    	
        List<TableRestaurant> tablesLibres = tableService.getTablesLibres(idRestaurant, heureResa, nbPersonne);

        // Convertir les entités en DTOs
        return tablesLibres.stream()
                .map(table -> tableMapper.toDTO(table))
                .collect(Collectors.toList());
    }

    @GetMapping("/{idRestaurant}")
    public List<TableRestaurantDTO> getTablesLibresMaintenant(@PathVariable Integer idRestaurant, @RequestParam Integer nbPersonne) {

        List<TableRestaurant> tablesLibres = tableService.getTablesLibresMaintenant(idRestaurant, nbPersonne);

        // Convertir les entités en DTOs
        return tablesLibres.stream()
                .map(table -> tableMapper.toDTO(table))  
                .collect(Collectors.toList());
    }

    //Permet d'accepter une nouvelle résa et l'associer à une table
    //Deux cas possibles :
    //Dans le cas où des gens arrivent sans avoir réservé, le front créé une réservation avec la dateTime.NOW,
    //avec un utilisateur nommé "sans_resa" et le statut "Présent" (plus reste du DTO)
    //Si la résa est en base, on l'associe juste à la table et on la passe en "Présent"
    //Le statut de la table (libre ou occupée) est en fait géré par la requête estLibre de Quentin
    @PutMapping("/{id_table}")
    public ResponseEntity<ReservationDTO> accepterResa(@PathVariable("id_table") Integer idTable,
                                                       @RequestBody ReservationDTO resaAAccepter){

    	Reservation resa = reservationMapper.toEntity(resaAAccepter);

        Optional<Restaurant> restaurant = restaurantService.findById(resaAAccepter.getIdRestaurant());
        restaurant.ifPresent(resa::setRestaurant);

        // Associer la table
        resa.setTable(tableService.getById(idTable));

        // Cas sans réservation = nomClient "Sans"
        if ("sans_resa".equalsIgnoreCase(resaAAccepter.getNomClient())) {
            Utilisateur utilisateurParDefaut = utilisateurService.selectByNom("Sans");
            resa.setClient(utilisateurParDefaut);

            resaAAccepter.setId(resa.getIdReservation());
        } else {
            // Cas avec réservation = nom du vrai client
            Utilisateur utilisateur = utilisateurService.selectByNom(resaAAccepter.getNomClient());
            resa.setClient(utilisateur);
        }

        resa.setStatut("Présent");
        reservationService.create(resa);

        return ResponseEntity.ok(resaAAccepter);
    }
    
    @GetMapping("/all/{idRestaurant}")
    public List<TableRestaurantDTO> getToutesLesTables(@PathVariable Integer idRestaurant) {
        List<TableRestaurant> tables = tableService.getAllByRestaurantId(idRestaurant);
        return tables.stream()
                .map(table -> tableMapper.toDTO(table))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/occupees/{idRestaurant}")
    public List<TableRestaurantDTO> getTablesOccupees(@PathVariable Integer idRestaurant) {
    	List<TableRestaurant> tablesOccupees = tableService.getTablesOccupees(idRestaurant);

        return tablesOccupees.stream()
                .map(table -> tableMapper.toDTO(table))
                .collect(Collectors.toList());
    }

    
}
