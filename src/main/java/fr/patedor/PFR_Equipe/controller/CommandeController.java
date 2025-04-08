package fr.patedor.PFR_Equipe.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	// SALLE
	// Afficher toutes les commandes (pour tests postman)
	@GetMapping
	public ResponseEntity<List<CommandeDto>> getAll() {
		List<Commande> commandes = commandeService.getAll();
		List<CommandeDto> commandesDto = commandes.stream()
				.map(commande -> commandeMapper.toDto(commande))
				.collect(Collectors.toList());

		return ResponseEntity.ok(commandesDto);
	}
	
	
	// Créer une commande vide lors du clic sur une table
	@PostMapping("/creation")
	public ResponseEntity<CommandeDto> create(@RequestParam("table") Integer idTable ){
		Reservation resa = reservationService.getByTableIdAndStatutPresent(idTable);
		Commande commande = new Commande();
		commande.setStatut("En cours");
		commande.setReservation(resa);
		commandeService.create(commande);
		
		CommandeDto commandeDto = commandeMapper.toDto(commande);
		
		return ResponseEntity.ok(commandeDto);
	}
	
	// Modifier une commande lors de sa création (ajout de plats)
	@PutMapping("/{id}")
	public ResponseEntity<CommandeDto> update(@PathVariable("id") Integer id, @RequestBody CommandeDto commandeDto) {
	   
	    Commande commande = commandeService.getById(id);
	    commande.setStatut("En cuisine");
	    
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
	
	//Récupérer la réservation d'une table et sa commande associée
	@GetMapping("/par-table/{idTable}")
	public ResponseEntity<CommandeDto> getCommandeParTable(@PathVariable Integer idTable) {
	    Reservation reservation = reservationService
	        .getByTableIdAndStatutPresent(idTable);
	    if (reservation == null) {
	        return ResponseEntity.noContent().build();
	    }
	    Commande commande = commandeService.findByReservationId(reservation.getIdReservation());
	    if (commande == null) {
	        return ResponseEntity.noContent().build();
	    }
	    CommandeDto commandeDto = commandeMapper.toDto(commande);
	    return ResponseEntity.ok(commandeDto);
	}
	
	// Afficher les détails d'une commande (clic sur la table ou lors de la facturation)
	@GetMapping("/{id}")
	public ResponseEntity<CommandeDto> getCommande(@PathVariable("id") Integer id) {
		Commande commande = commandeService.getById(id);
		CommandeDto commandeDto = commandeMapper.toDto(commande);
		return ResponseEntity.ok(commandeDto);
	}
		
	// Mettre à jour le statut de la commande "Prête" à "Servie"
	@PutMapping("/{id}/servie")
	public ResponseEntity<CommandeDto> updateStatutServie(@PathVariable("id") Integer id) {
		Commande commande = commandeService.getById(id);
		commande.setStatut("Servie");
		commandeService.update(commande);

		CommandeDto commandeDto = commandeMapper.toDto(commande);
		return ResponseEntity.ok(commandeDto);
	}
	
	// CUISINE
	// Afficher les commandes avec le statut "En cuisine"
	@GetMapping("/en-cuisine")
	public ResponseEntity<List<CommandeDto>> getCommandesEnCuisine() {
	    List<Commande> commandesEnCuisine = commandeService.getCommandesByStatut("En cuisine");
	    
	    List<CommandeDto> commandesDto = commandesEnCuisine.stream()
	        .map(commande -> commandeMapper.toDto(commande))
	        .collect(Collectors.toList());
	    
	    return ResponseEntity.ok(commandesDto);
	}
	
	// Mettre à jour le statut de la commande "En cuisine" à "Prête"
	@PutMapping("/{id}/prete")
	public ResponseEntity<CommandeDto> updateStatutPrete(@PathVariable("id") Integer id) {
		Commande commande = commandeService.getById(id);
		commande.setStatut("Prête");
		commandeService.update(commande);
	        
		CommandeDto commandeDto = commandeMapper.toDto(commande);
		return ResponseEntity.ok(commandeDto);
	}
	
	// CAISSE
	// Afficher les commandes avec le statut "Servie"
	@GetMapping("/servie")
	public ResponseEntity<List<CommandeDto>> getCommandesServies() {
		List<Commande> commandesEnCuisine = commandeService.getCommandesByStatut("Servie");
		    
		List<CommandeDto> commandesDto = commandesEnCuisine.stream()
			.map(commande -> commandeMapper.toDto(commande))
			.collect(Collectors.toList());
		    
		return ResponseEntity.ok(commandesDto);
	}
		
	// Mettre à jour le statut de la commande "Servie" à "Payée"
	@PutMapping("/{id}/payee")
	public ResponseEntity<CommandeDto> updateStatutPayee(@PathVariable("id") Integer id) {
		Commande commande = commandeService.getById(id);
		commande.setStatut("Payée");
		commandeService.update(commande);
		        
		CommandeDto commandeDto = commandeMapper.toDto(commande);
		return ResponseEntity.ok(commandeDto);
	}
		
	// Supprimer la commande une fois réglée (check du statut a faire en front)
	@DeleteMapping("/{id}/suppression")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		commandeService.delete(id);
		return ResponseEntity.ok("La commande et la réservation associée ont bien été supprimées !");
	}
	
}
