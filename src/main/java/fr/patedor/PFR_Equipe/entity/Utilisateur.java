package fr.patedor.PFR_Equipe.entity;


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
@Entity
@Table(name ="utilisateurs")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idUtilisateur;
	
	private String nom;
	private String prenom;
	private String login;
	private String telephone;
	private String email;
	private byte[] mdp;
	private byte[] salt;
	private String token;
	
	@ManyToOne
	@JoinColumn(name = "id_role")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "id_restaurant")
	private Restaurant restaurant;
	
}