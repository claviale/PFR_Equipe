package fr.patedor.PFR_Equipe.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity @Table(name = "restaurants")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idRestaurant;
	
	private String nom;
	private String adresse;
	private String url_image;
	
	@ManyToOne
	@JoinColumn(name = "id_carte")
	private Carte carte;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_restaurant")
	private List<TableRestaurant> tables;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_restaurant")
	private List<Horaire> horaires;
	
}
