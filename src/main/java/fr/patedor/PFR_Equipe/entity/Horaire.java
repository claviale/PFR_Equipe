package fr.patedor.PFR_Equipe.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity @Table(name = "horaires")
public class Horaire {
	
	@Id @GeneratedValue
	private int id;

	private String jour;
	private LocalTime ouverture;
	private LocalTime fermeture;
}
