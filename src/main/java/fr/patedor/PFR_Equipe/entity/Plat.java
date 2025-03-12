package fr.patedor.PFR_Equipe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "plats")
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private float prix;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_categorie" )
    private Categorie categorie;

}
