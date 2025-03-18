package fr.patedor.PFR_Equipe.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.patedor.PFR_Equipe.dto.TableRestaurantDTO;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;
import fr.patedor.PFR_Equipe.mapper.TableRestaurantMapper;
import fr.patedor.PFR_Equipe.service.TableRestaurantServiceImpl;

@RestController
@RequestMapping("/tables")
public class TableRestaurantController {

    @Autowired
    private TableRestaurantServiceImpl tableService;
    
    @Autowired
    TableRestaurantMapper tableMapper;

    @GetMapping("/{idRestaurant}/libres")
    public List<TableRestaurantDTO> getTablesLibres(
            @PathVariable Integer idRestaurant,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime heureResa) {
    	
        List<TableRestaurant> tablesLibres = tableService.getTablesLibres(idRestaurant, heureResa);

        // Convertir les entités en DTOs
        return tablesLibres.stream()
                .map(table -> tableMapper.toDTO(table))  
                .collect(Collectors.toList());
    }
}
