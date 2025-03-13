package fr.patedor.PFR_Equipe.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.patedor.PFR_Equipe.dto.CommandeDto;
import fr.patedor.PFR_Equipe.entity.Commande;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.mapper.CommandeMapper;
import fr.patedor.PFR_Equipe.service.CommandeService;
import fr.patedor.PFR_Equipe.service.ReservationService;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
	
	@Autowired
	CommandeService commandeService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	CommandeMapper commandeMapper;
	
	@GetMapping
	public ResponseEntity<List<CommandeDto>> getAll() {
		List<Commande> commandes = commandeService.getAll();
		
		List<CommandeDto> commandesDto = commandes.stream()
				.map(commande -> commandeMapper.toDto(commande))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(commandesDto);
	}
	
	//Retourne tous les plats par libellés qui correspondent au restaurant
	
	//@GetMapping("/choix")
	//public ResponseEntity<List<CommandeDto>> getAllPlats() {
		
	//	return ResponseEntity.ok();
	//}
	
	@PostMapping
	public ResponseEntity<Commande> create(@RequestParam("table") Integer idTable ){
		// id table on récup la résa associée : selectbyidtable (custom select)
		Reservation resa = reservationService.getByTableId(idTable);
		Commande commande = new Commande();
		commande.setReservation(resa);
		commandeService.create(commande);
		
		return ResponseEntity.ok(commande);
	}
	
	//@putMapping commandes id pour bouton commande
	
}
