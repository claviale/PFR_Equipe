package fr.patedor.PFR_Equipe.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity @Table(name = "reservations")
public class Reservation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idReservation;
	
	@ManyToOne
	@JoinColumn(name = "id_restaurant")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;
	
    @ManyToOne
    @JoinColumn(name = "id_table", nullable = true)
	private TableRestaurant table;

	@Column(name = "horaire_reservation")
	private LocalDateTime horaireReservation;
	
	@Column(name = "nombre_personnes")
	private int nbPersonne;
	private String statut;
}