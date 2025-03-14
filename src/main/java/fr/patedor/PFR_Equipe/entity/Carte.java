package fr.patedor.PFR_Equipe.entity;

import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity @Table(name = "cartes")
public class Carte {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String description;
	
	@ManyToMany
	@JoinTable(
			name = "asso_cartes_plats",
			joinColumns = {@JoinColumn(name = "id_carte")},
			inverseJoinColumns = {@JoinColumn(name = "id_plat")}
	)
	private List<Plat> plats;
	
	@Transient
    private Map<Categorie, List<Plat>> platsGroupedByCategory;


}
