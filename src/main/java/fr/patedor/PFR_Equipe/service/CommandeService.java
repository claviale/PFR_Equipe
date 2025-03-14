package fr.patedor.PFR_Equipe.service;

import java.util.List;
import java.util.Optional;

import fr.patedor.PFR_Equipe.entity.Commande;

public interface CommandeService {
	public void create(Commande commande);
	public void update(Commande commande);
	public void delete(Commande commande);
	public List<Commande> getAll();
	public Commande getById(Integer id);
}
