package fr.patedor.PFR_Equipe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssoCommandesPlatsDto {
	private String libelleCategorie;
	private String nomPlat;
	private Integer quantite;
	private Float prix;
}
