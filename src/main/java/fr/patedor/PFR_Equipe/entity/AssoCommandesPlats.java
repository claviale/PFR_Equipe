package fr.patedor.PFR_Equipe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Entity
@Table(name = "asso_commandes_plats")
public class AssoCommandesPlats {

	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "id_plat")
    private Plat plat;

    private Integer quantite;

	public void setLibelleCategorie(String libelle) {
		// TODO Auto-generated method stub
		
	}

	public void setNomPlat(String nom) {
		// TODO Auto-generated method stub
		
	}

	public void setPrix(Float prix) {
		// TODO Auto-generated method stub
		
	} 
}
