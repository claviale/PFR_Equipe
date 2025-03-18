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
@Entity @Table(name = "tables_restaurant")
public class TableRestaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idTableRestaurant;

	
	@Column(name = "nb_places")
	private Integer nbPlaces;
	@Column(name = "numero_table")
	private Integer numeroTable;
	
    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;
}

