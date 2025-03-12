package fr.patedor.PFR_Equipe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cartes")
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
