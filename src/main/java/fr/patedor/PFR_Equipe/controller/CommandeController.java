package fr.patedor.PFR_Equipe.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.patedor.PFR_Equipe.dto.CommandeDto;
import fr.patedor.PFR_Equipe.entity.Commande;
import fr.patedor.PFR_Equipe.mapper.CommandeMapper;
import fr.patedor.PFR_Equipe.service.CommandeService;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
	
	@Autowired
	CommandeService service;
	
	@GetMapping
	public ResponseEntity<List<CommandeDto>> getAll() {
		List<Commande> commandes = service.getAll();
		
		List<CommandeDto> commandesDto = commandes.stream()
				.map(commande -> CommandeMapper.toDto(commande))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(commandesDto);
	}
	
	//Retourne tous les plats par libellés qui correspondent au restaurant
	
	//@GetMapping("/choix")
	//public ResponseEntity<List<CommandeDto>> getAllPlats() {
		
	//	return ResponseEntity.ok();
	//}
	
	@PostMapping
	public ResponseEntity<CommandeDto> create(@RequestBody CommandeDto commandeDto){
		// TODO gérer les exceptions
		service.create(CommandeMapper.toEntity(commandeDto));
		return ResponseEntity.ok(commandeDto);
	}
	
}
