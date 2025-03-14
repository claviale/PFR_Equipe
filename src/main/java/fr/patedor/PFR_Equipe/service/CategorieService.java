package fr.patedor.PFR_Equipe.service;

import java.util.List;

import fr.patedor.PFR_Equipe.entity.Categorie;

public interface CategorieService {
	 List<Categorie> getAll();
	 Categorie getById(Integer id);
}
