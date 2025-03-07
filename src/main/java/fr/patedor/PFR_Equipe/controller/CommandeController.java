package fr.patedor.PFR_Equipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.patedor.PFR_Equipe.entity.Commande;
import fr.patedor.PFR_Equipe.service.CommandeService;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
	
	@Autowired
	CommandeService service;
	
	@GetMapping
	public ResponseEntity<List<Commande>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
}
