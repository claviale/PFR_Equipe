package fr.patedor.PFR_Equipe.service;

import java.util.List;

import fr.patedor.PFR_Equipe.entity.Commande;

public interface CommandeService {
	public void create(Commande commande);
	public void update(Commande commande);
	public List<Commande> getAll();
	public Commande getById(Integer id);
	public List<Commande> getCommandesByStatut(String statut);
	public void delete(Integer id);
	Commande findByReservationId(Integer idReservation);
}
