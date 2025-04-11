package fr.patedor.PFR_Equipe.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {

    private Integer id;
    private String nomClient;
    private Integer numeroTable;
    private ZonedDateTime horaireReservation;
    private Integer nbPersonne;
    private String statut;
    private Integer idRestaurant;
}
