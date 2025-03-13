package fr.patedor.PFR_Equipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.patedor.PFR_Equipe.dto.CommandeDto;
import fr.patedor.PFR_Equipe.entity.Commande;
import fr.patedor.PFR_Equipe.repository.CommandeRepository;
import jakarta.transaction.Transactional;

@Service
public class CommandeServiceImpl implements CommandeService {
	
	@Autowired
	CommandeRepository repo;
	
	@Override
	public void create(Commande commande) {
		repo.save(commande);
		
	}

	@Override
	public void update(Commande commande) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Commande commande) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Commande> getAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Commande> getById(Integer id) {
		return repo.findById(id);
	}
	
	
}
