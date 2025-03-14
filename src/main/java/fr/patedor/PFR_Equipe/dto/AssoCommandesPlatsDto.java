package fr.patedor.PFR_Equipe.dto;

import fr.patedor.PFR_Equipe.entity.Plat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssoCommandesPlatsDto {
    private Plat plat;
	private Integer quantite;
	private Float prix;
}
