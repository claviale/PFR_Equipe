package fr.patedor.PFR_Equipe.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Entity @Table(name = "commandes")
public class Commande {
	
	@Id @GeneratedValue
	private Integer id;
	
	//@ManyToOne
	//@JoinColumn(name = "id_reservation")
	//private Reservation reservation;
	
	private String statut;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "commande")
    private List<AssoCommandesPlats> assoCommandesPlats;
	
	
}
