package fr.patedor.PFR_Equipe.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping("/{idRestaurant}")
    public ResponseEntity<Map<String, List<Reservation>>> getAllReservationsForRestaurant(
            @PathVariable Integer idRestaurant) {
        
        List<Reservation> reservations = reservationService.getAllReservationsForRestaurant(idRestaurant);
        
        // Créer une Map pour organiser les résultats
        Map<String, List<Reservation>> result = new LinkedHashMap<>();
        result.put("reservations", reservations);
        
        return ResponseEntity.ok(result);
    }
}