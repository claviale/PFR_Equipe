package fr.patedor.PFR_Equipe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employes")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private String login;
    private String email;
    private String telephone;
    private String mdp;
    private String token;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;
}
