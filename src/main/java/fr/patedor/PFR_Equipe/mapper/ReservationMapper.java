package fr.patedor.PFR_Equipe.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.patedor.PFR_Equipe.dto.ReservationDTO;
import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.service.RestaurantService;
import fr.patedor.PFR_Equipe.service.TableRestaurantService;
import fr.patedor.PFR_Equipe.service.UtilisateurService;

@Component
public class ReservationMapper {

	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	TableRestaurantService tableRestaurantService;
	
	public ReservationDTO toDTO(Reservation reservation) {
		return ReservationDTO.builder()
				.id(reservation.getIdReservation())
				.nomClient(reservation.getUtilisateur().getNom())
	            // Vérification si la table est null avant d'accéder à numeroTable
	            .numeroTable(reservation.getTable() != null ? reservation.getTable().getNumeroTable() : null)
				.horaireReservation(reservation.getHoraireReservation())
				.nbPersonne(reservation.getNbPersonne())
				.statut(reservation.getStatut())
				.idRestaurant(reservation.getRestaurant().getIdRestaurant())
				.build();
	}
	
	public Reservation toEntity(ReservationDTO reservationDTO) {
		Utilisateur utilisateur = utilisateurService.selectByNom(reservationDTO.getNomClient());
		TableRestaurant tableRestaurant = tableRestaurantService.selectByNumeroTableAndIdRestaurant(reservationDTO.getNumeroTable(), reservationDTO.getIdRestaurant());
		
		return Reservation.builder()
				.idReservation(reservationDTO.getId())
				.utilisateur(utilisateur)
				.table(tableRestaurant)
				.horaireReservation(reservationDTO.getHoraireReservation())
				.nbPersonne(reservationDTO.getNbPersonne())
				.statut(reservationDTO.getStatut())
				.build();
	}
}
