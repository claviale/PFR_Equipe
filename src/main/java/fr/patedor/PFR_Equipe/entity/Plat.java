package fr.patedor.PFR_Equipe.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Entity @Table(name = "plats")
public class Plat {
	
	@Id @GeneratedValue
	private Integer id;
	private String nom;
	private Float prix;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;
	
	@OneToMany(mappedBy = "plat")
	private List<AssoCommandesPlats> assoCommandesPlats;

}
