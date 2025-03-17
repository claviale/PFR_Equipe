package fr.patedor.PFR_Equipe.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.patedor.PFR_Equipe.dto.CommandeDto;
import fr.patedor.PFR_Equipe.entity.AssoCommandesPlats;
import fr.patedor.PFR_Equipe.entity.Commande;
import fr.patedor.PFR_Equipe.entity.Plat;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.mapper.CommandeMapper;
import fr.patedor.PFR_Equipe.service.AssoCommandesPlatsService;
import fr.patedor.PFR_Equipe.service.CommandeService;
import fr.patedor.PFR_Equipe.service.PlatService;
import fr.patedor.PFR_Equipe.service.ReservationService;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
	
	@Autowired
	CommandeService commandeService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	PlatService platService;
	
	@Autowired
	AssoCommandesPlatsService assoService;
	
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
	
	@PostMapping
	public ResponseEntity<CommandeDto> create(@RequestParam("table") Integer idTable ){
		Reservation resa = reservationService.getByTableId(idTable);
		Commande commande = new Commande();
		commande.setStatut("En cours");
		commande.setReservation(resa);
		commandeService.create(commande);
		
		CommandeDto commandeDto = commandeMapper.toDto(commande);
		
		return ResponseEntity.ok(commandeDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CommandeDto> update(@PathVariable("id") Integer id, @RequestBody CommandeDto commandeDto) {
	   
	    Commande commande = commandeService.getById(id);
	    commande.setStatut(commandeDto.getStatut());
	    
	    List<AssoCommandesPlats> platsAjoutes = commandeDto.getAssoCommandesPlatsDto().stream()
	        .map(platDto -> {
	            AssoCommandesPlats assoCommandePlat = new AssoCommandesPlats();
	            
	            Plat plat = platService.getByNom(platDto.getPlat().getNom()); 
	            assoCommandePlat.setPlat(plat);
	            
	            assoCommandePlat.setQuantite(platDto.getQuantite());
	            assoCommandePlat.setPrix(plat.getPrix());
	            
	            assoCommandePlat.setCommande(commande); 
	            assoService.create(assoCommandePlat);
	            
	            return assoCommandePlat;
	        })
	        .collect(Collectors.toList());
	    
	    commande.setAssoCommandesPlats(platsAjoutes);
	    commandeService.update(commande);
	   
	    CommandeDto commandeDTO = commandeMapper.toDto(commande);
	    return ResponseEntity.ok(commandeDTO);
	}
	
}
