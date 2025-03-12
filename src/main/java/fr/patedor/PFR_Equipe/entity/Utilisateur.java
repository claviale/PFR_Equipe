package fr.patedor.PFR_Equipe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue
    private Integer id;

    private String nom;
    private String prenom;
    private String login;
    private String email;
    private String telephone;
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
