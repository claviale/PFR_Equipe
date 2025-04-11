package fr.patedor.PFR_Equipe.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.patedor.PFR_Equipe.entity.Reservation;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.TableRestaurant;
import fr.patedor.PFR_Equipe.repository.ReservationRepository;
import fr.patedor.PFR_Equipe.repository.TableRestaurantRepository;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {

	@Autowired
	TableRestaurantRepository repo;
	
	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public TableRestaurant getById(Integer id) {
		return repo.findById(id).orElseThrow();
	}

	@Override
	public List<TableRestaurant> getAllByRestaurantId(Integer idRestaurant) {
		// TODO Auto-generated method stub
		return repo.findAllByRestaurantId(idRestaurant);
	}

	@Override
	public TableRestaurant selectByNumeroTableAndIdRestaurant(Integer numero, Integer idRestaurant) {
		return repo.findByNumeroTableAndIdRestaurant(numero, idRestaurant);
	}

	@Override
	public List<TableRestaurant> getTablesLibres(Integer idRestaurant, LocalDateTime heureResa, Integer nbPersonne) {
        List<TableRestaurant> tables = repo.findByNbPlacesAndIdRestaurant(idRestaurant, nbPersonne);

		List<Reservation> reservations = reservationRepository.findAllByRestaurant(idRestaurant, ZonedDateTime.now(ZoneId.of("Europe/Paris")));

        return tables.stream()
                .filter(table -> estLibre(table, reservations))
                .collect(Collectors.toList());
	}

	@Override
	public List<TableRestaurant> getTablesLibres(Integer idRestaurant, ZonedDateTime heureResa) {
        List<TableRestaurant> tables = repo.findAllByRestaurantId(idRestaurant);
        
		List<Reservation> reservations = reservationRepository.findAllByRestaurant(idRestaurant, ZonedDateTime.now(ZoneId.of("Europe/Paris")));

        return tables.stream()
                .filter(table -> estLibre(table, reservations))
                .collect(Collectors.toList());
	}

	@Override
	public List<TableRestaurant> getTablesLibresMaintenant(Integer idRestaurant, Integer nbPersonne) {
        List<TableRestaurant> tables = repo.findByNbPlacesAndIdRestaurant(idRestaurant, nbPersonne);

		List<Reservation> reservations = reservationRepository.findAllByRestaurant(idRestaurant, ZonedDateTime.now(ZoneId.of("Europe/Paris")));

        return tables.stream()
                .filter(table -> estLibre(table, reservations))
                .collect(Collectors.toList());
	}

	@Override
	public List<TableRestaurant> getTablesOccupees(Integer idRestaurant) {
        List<TableRestaurant> tables = repo.findAllByRestaurantId(idRestaurant);

		List<Reservation> reservations = reservationRepository.findAllByRestaurant(idRestaurant, ZonedDateTime.now(ZoneId.of("Europe/Paris")));

        return tables.stream()
                .filter(table -> !estLibre(table, reservations))
                .collect(Collectors.toList());
	}
	
	
	public boolean estLibre(TableRestaurant table, List<Reservation> reservations) {
	    ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
	   
	    for (Reservation res : reservations) {
	        if (res.getTable() != null && res.getTable().getIdTableRestaurant().equals(table.getIdTableRestaurant())) {
	            
	        	ZonedDateTime debut = res.getHoraireReservation();  
	            ZonedDateTime fin = debut.plusHours(2).plusMinutes(30);

	            boolean isBetweenStartAndEnd = (now.isAfter(debut) || now.isEqual(debut)) && (now.isBefore(fin) || now.isEqual(fin));
	            if (isBetweenStartAndEnd && (res.getStatut().equals("Présent") || res.getStatut().equals("Confirmée"))) {
	                System.out.println("Table occupée: " + table.getIdTableRestaurant());
	                return false;
	            }
	        }
	    }
	    return true; 
	}
}
