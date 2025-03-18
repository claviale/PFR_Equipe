package fr.patedor.PFR_Equipe.service;

import java.time.LocalDateTime;
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

		List<Reservation> reservations = reservationRepository.findAllByRestaurant(idRestaurant);

        return tables.stream()
                .filter(table -> estLibre(table, reservations, heureResa))
                .collect(Collectors.toList());
	}

	@Override
	public List<TableRestaurant> getTablesLibres(Integer idRestaurant, LocalDateTime heureResa) {
        List<TableRestaurant> tables = repo.findAllByRestaurantId(idRestaurant);
        
		List<Reservation> reservations = reservationRepository.findAllByRestaurant(idRestaurant);

        return tables.stream()
                .filter(table -> estLibre(table, reservations, heureResa))
                .collect(Collectors.toList());
	}

	@Override
	public List<TableRestaurant> getTablesLibresMaintenant(Integer idRestaurant, Integer nbPersonne) {
        List<TableRestaurant> tables = repo.findByNbPlacesAndIdRestaurant(idRestaurant, nbPersonne);

		List<Reservation> reservations = reservationRepository.findAllByRestaurant(idRestaurant);

        return tables.stream()
                .filter(table -> estLibre(table, reservations, LocalDateTime.now()))
                .collect(Collectors.toList());
	}

	@Override
	public List<TableRestaurant> getTablesOccupees(Integer idRestaurant) {
        List<TableRestaurant> tables = repo.findAllByRestaurantId(idRestaurant);

		List<Reservation> reservations = reservationRepository.findAllByRestaurant(idRestaurant);

        return tables.stream()
                .filter(table -> !estLibre(table, reservations, LocalDateTime.now()))
                .collect(Collectors.toList());
	}

	public boolean estLibre(TableRestaurant table, List<Reservation> reservations, LocalDateTime heureResa) {
        LocalDateTime finResa = heureResa.plusHours(2).plusMinutes(30);

        for (Reservation res : reservations) {
            if (res.getTable() != null && res.getTable().getIdTableRestaurant().equals(table.getIdTableRestaurant())) {
                LocalDateTime debut = res.getHoraireReservation();
                LocalDateTime fin = debut.plusHours(2).plusMinutes(30);

                // Si la réservation existe dans la même plage horaire, la table n'est pas libre
                if (debut.isBefore(finResa) && fin.isAfter(heureResa)) {
                    return false;
                }
            }
        }

        return true;
	}

}
