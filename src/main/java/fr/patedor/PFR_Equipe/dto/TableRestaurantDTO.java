package fr.patedor.PFR_Equipe.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableRestaurantDTO {

	private Integer id;
	private Integer nbPlaces;
	private Integer numeroTable;
}
