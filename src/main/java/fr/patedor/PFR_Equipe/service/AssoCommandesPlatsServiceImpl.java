package fr.patedor.PFR_Equipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.patedor.PFR_Equipe.entity.AssoCommandesPlats;
import fr.patedor.PFR_Equipe.repository.AssoCommandesPlatsRepository;

@Service
public class AssoCommandesPlatsServiceImpl implements AssoCommandesPlatsService {
	
	@Autowired
	AssoCommandesPlatsRepository assoRepo;
	
	@Override
	public void create(AssoCommandesPlats assoCommandesPlats) {
		assoRepo.save(assoCommandesPlats);
	}

}
