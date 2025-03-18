package fr.patedor.PFR_Equipe.mapper;


import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.patedor.PFR_Equipe.dto.ReservationDTO;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;
import fr.patedor.PFR_Equipe.entity.Employe;
import fr.patedor.PFR_Equipe.service.TableRestaurantService;
import fr.patedor.PFR_Equipe.service.EmployeService;

@Component
public class ReservationMapper {

	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	TableRestaurantService tableRestaurantService;
	
	public ReservationDTO toDTO(Reservation reservation) {
		return ReservationDTO.builder()
				.id(reservation.getIdReservation())
				.nomClient(reservation.getClient().getNom())
	            // Vérification si la table est null avant d'accéder à numeroTable
	            .numeroTable(reservation.getTable() != null ? reservation.getTable().getNumeroTable() : null)
				.horaireReservation(reservation.getHoraireReservation())
				.nbPersonne(reservation.getNbPersonne())
				.statut(reservation.getStatut())
				.idRestaurant(reservation.getRestaurant().getIdRestaurant())
				.build();
	}
	
	public Reservation toEntity(ReservationDTO reservationDTO) {
		Utilisateur client = utilisateurService.selectByNom(reservationDTO.getNomClient());
		TableRestaurant tableRestaurant = tableRestaurantService.selectByNumeroTableAndIdRestaurant(reservationDTO.getNumeroTable(), reservationDTO.getIdRestaurant());
		
		return Reservation.builder()
				.idReservation(reservationDTO.getId())
				.client(client)
				.table(tableRestaurant)
				.horaireReservation(reservationDTO.getHoraireReservation())
				.nbPersonne(reservationDTO.getNbPersonne())
				.statut(reservationDTO.getStatut())
				.build();
	}
}
