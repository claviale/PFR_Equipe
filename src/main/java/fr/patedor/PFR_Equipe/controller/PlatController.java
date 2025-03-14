package fr.patedor.PFR_Equipe.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.patedor.PFR_Equipe.entity.Plat;
import fr.patedor.PFR_Equipe.service.PlatService;

@RestController
@RequestMapping("/plats")
public class PlatController {
	
	@Autowired
	PlatService platService;
	
	
	@GetMapping("/{idRestaurant}")
    public ResponseEntity<Map<String, List<Plat>>> getPlatsByCategorie(@PathVariable("idRestaurant") Integer idRestaurant) {
        Map<String, List<Plat>> platsParCategorie = platService.getPlatsByRestaurant(idRestaurant);
        return ResponseEntity.ok(platsParCategorie);
    }
}
