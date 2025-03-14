package fr.patedor.PFR_Equipe.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.patedor.PFR_Equipe.dto.AssoCommandesPlatsDto;
import fr.patedor.PFR_Equipe.dto.CommandeDto;
import fr.patedor.PFR_Equipe.entity.AssoCommandesPlats;
import fr.patedor.PFR_Equipe.entity.Commande;
import fr.patedor.PFR_Equipe.entity.Plat;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.service.PlatService;
import fr.patedor.PFR_Equipe.service.ReservationService;

@Component
public class CommandeMapper {
	
	@Autowired
	ReservationService resaService;
	
	@Autowired
	PlatService platService;
	
	public CommandeDto toDto(Commande commande) {
		CommandeDto commandeDto = new CommandeDto();
		commandeDto.setIdCommande(commande.getId());
		commandeDto.setStatut(commande.getStatut());
		commandeDto.setIdReservation(commande.getReservation().getIdReservation());
		
		List<AssoCommandesPlatsDto> listeCmdPlatDto = commande.getAssoCommandesPlats().stream()
		            .map(assocommandePlat -> {
		            	AssoCommandesPlatsDto assocommandePlatDTO = new AssoCommandesPlatsDto();
		            	 Plat plat = assocommandePlat.getPlat(); 
		                 assocommandePlatDTO.setPlat(plat);
		                 assocommandePlatDTO.setQuantite(assocommandePlat.getQuantite());
		                 assocommandePlatDTO.setPrix(plat.getPrix());

		                 return assocommandePlatDTO;
		            })
		            .collect(Collectors.toList());
		
		commandeDto.setAssoCommandesPlatsDto(listeCmdPlatDto);
				
		return commandeDto;
	}
	
	public Commande toEntity(CommandeDto commandeDto) {
		
		Commande commande = new Commande();
		commande.setId(commandeDto.getIdCommande());
		commande.setStatut(commandeDto.getStatut());
		
		Reservation reservation = resaService.getById(commandeDto.getIdReservation());
		commande.setReservation(reservation);
		
		 List<AssoCommandesPlats> listeCmdPlat = commandeDto.getAssoCommandesPlatsDto().stream()
		            .map(assocommandePlatDto -> {
		                AssoCommandesPlats assoCommandePlat = new AssoCommandesPlats();
		                Plat plat = assocommandePlatDto.getPlat();
		                assoCommandePlat.setPlat(plat);
		                assoCommandePlat.setQuantite(assocommandePlatDto.getQuantite());
		                assoCommandePlat.setPrix(plat.getPrix());
		                return assoCommandePlat;
		            })
		            .collect(Collectors.toList());

		        commande.setAssoCommandesPlats(listeCmdPlat);
				
		return commande;
	}
}
