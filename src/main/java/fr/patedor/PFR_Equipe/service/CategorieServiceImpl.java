package fr.patedor.PFR_Equipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.patedor.PFR_Equipe.entity.Categorie;
import fr.patedor.PFR_Equipe.repository.CategorieRepository;

@Service
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	CategorieRepository categorieRepo;
	
	@Override
	public List<Categorie> getAll() {
		return categorieRepo.findAll();
	}

	@Override
	public Categorie getById(Integer id) {
		return categorieRepo.findById(id).orElse(null);
	}

}
