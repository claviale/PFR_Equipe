package fr.patedor.PFR_Equipe.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.patedor.PFR_Equipe.dto.AssoCommandesPlatsDto;
import fr.patedor.PFR_Equipe.dto.CommandeDto;
import fr.patedor.PFR_Equipe.entity.AssoCommandesPlats;
import fr.patedor.PFR_Equipe.entity.Commande;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.service.ReservationService;

@Component
public class CommandeMapper {
	
	@Autowired
	ReservationService resaService;
	
	public CommandeDto toDto(Commande commande) {
		CommandeDto commandeDto = new CommandeDto();
		commandeDto.setIdCommande(commande.getId());
		commandeDto.setStatut(commande.getStatut());
		commandeDto.setIdReservation(commande.getReservation().getIdReservation());
		
		List<AssoCommandesPlatsDto> listeCmdPlatDto = commande.getAssoCommandesPlats().stream()
		            .map(assocommandePlat -> {
		            	AssoCommandesPlatsDto assocommandePlatDTO = new AssoCommandesPlatsDto();
		                assocommandePlatDTO.setLibelleCategorie(assocommandePlat.getPlat().getCategorie().getLibelle());
		                assocommandePlatDTO.setNomPlat(assocommandePlat.getPlat().getNom());
		                assocommandePlatDTO.setQuantite(assocommandePlat.getQuantite());
		                assocommandePlatDTO.setPrix(assocommandePlat.getPlat().getPrix());
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
		            	assoCommandePlat.setLibelleCategorie(assocommandePlatDto.getLibelleCategorie());
		            	assoCommandePlat.setNomPlat(assocommandePlatDto.getNomPlat());
		            	assoCommandePlat.setQuantite(assocommandePlatDto.getQuantite());
		            	assoCommandePlat.setPrix(assocommandePlatDto.getPrix());
		                return assoCommandePlat;
		            })
		            .collect(Collectors.toList());
		
		commande.setAssoCommandesPlats(listeCmdPlat);
				
		return commande;
	}
}
