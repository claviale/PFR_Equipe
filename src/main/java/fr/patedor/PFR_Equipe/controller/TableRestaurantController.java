package fr.patedor.PFR_Equipe.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import fr.patedor.PFR_Equipe.dto.ReservationDTO;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.mapper.ReservationMapper;
import fr.patedor.PFR_Equipe.service.ReservationService;
import fr.patedor.PFR_Equipe.service.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.patedor.PFR_Equipe.dto.TableRestaurantDTO;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;
import fr.patedor.PFR_Equipe.mapper.TableRestaurantMapper;
import fr.patedor.PFR_Equipe.service.TableRestaurantServiceImpl;

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

    @GetMapping("/{idRestaurant}/libres")
    public List<TableRestaurantDTO> getTablesLibres(
            @PathVariable Integer idRestaurant,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime heureResa) {
    	
        List<TableRestaurant> tablesLibres = tableService.getTablesLibres(idRestaurant, heureResa);

        // Convertir les entités en DTOs
        return tablesLibres.stream()
                .map(table -> tableMapper.toDTO(table))  
                .collect(Collectors.toList());
    }

    //Permet d'accepter une nouvelle résa et l'associer à une table
    //Deux cas possibles :
    //Dans le cas où des gens arrivent sans avoir réservé, le front créé une réservation avec la dateTime.NOW,
    //avec un utilisateur nommé "sans_resa" et le statut "présent" (plus reste du DTO)
    //Si la résa est en base, on l'associe juste à la table et on la passe en "présents"
    //Le statut de la table (libre ou occupée) est en fait géré par la requête estLibre de Quentin
    @PutMapping("/{id_table}")
    public ResponseEntity<ReservationDTO> accepterResa(@RequestBody ReservationDTO resaAAccepter){
        if("sans_resa".equalsIgnoreCase(resaAAccepter.getNomClient())){
            //cas sans résa, on ajoute juste en base
            Reservation resa = reservationMapper.toEntity(resaAAccepter);
            reservationService.create(resa);
        }else{
            //cas avec résa, on passe juste le statut à "présent"
            resaAAccepter.setStatut("présent");
            Reservation resa = reservationMapper.toEntity(resaAAccepter);
            reservationService.create(resa);
        }
        return ResponseEntity.ok(resaAAccepter);
    }
}
