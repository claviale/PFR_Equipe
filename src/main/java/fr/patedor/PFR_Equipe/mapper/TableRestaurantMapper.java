package fr.patedor.PFR_Equipe.mapper;

import org.springframework.stereotype.Component;

import fr.patedor.PFR_Equipe.dto.TableRestaurantDTO;

import fr.patedor.PFR_Equipe.entity.TableRestaurant;

@Component
public class TableRestaurantMapper {

	public TableRestaurantDTO toDTO(TableRestaurant table) {
		return TableRestaurantDTO.builder()
				.id(table.getIdTableRestaurant())
				.nbPlaces(table.getNbPlaces())
				.numeroTable(table.getNumeroTable())
				.build();
	}
	
	public TableRestaurant toEntity(TableRestaurantDTO tableDTO) {
		return TableRestaurant.builder()
				.idTableRestaurant(tableDTO.getId())
				.nbPlaces(tableDTO.getNbPlaces())
				.numeroTable(tableDTO.getNumeroTable())
				.build();
	}
}
