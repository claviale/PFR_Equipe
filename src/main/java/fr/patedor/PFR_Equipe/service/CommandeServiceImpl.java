package fr.patedor.PFR_Equipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.patedor.PFR_Equipe.entity.Commande;
import fr.patedor.PFR_Equipe.repository.CommandeRepository;

@Service
public class CommandeServiceImpl implements CommandeService {
	
	@Autowired
	CommandeRepository commandeRepo;
	
	@Autowired
	ReservationService resaService;
	
	@Override
	public void create(Commande commande) {
		commandeRepo.save(commande);
	}

	@Override
	public void update(Commande commande) {
		commandeRepo.save(commande);
	}

	@Override
	public List<Commande> getAll() {
		return commandeRepo.findAll();
	}

	@Override
	public Commande getById(Integer id) {
		return commandeRepo.findById(id).orElse(null);
	}

	@Override
	public List<Commande> getCommandesByStatut(String statut) {
		return commandeRepo.findByStatut(statut);
	}

	@Override
	public void delete(Integer id) {
		commandeRepo.deleteById(id);
	}

	@Override
	public Commande findByReservationId(Integer idReservation) {
		return commandeRepo.findByReservation_IdReservation(idReservation);
	}
	
	
}
