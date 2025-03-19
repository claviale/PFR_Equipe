package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Utilisateur;

public interface UtilisateurService {

    Utilisateur findById(Integer id);

    Utilisateur selectByNom(String nom);
}
