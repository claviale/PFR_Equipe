package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    List<Utilisateur> findFromRestaurant(Integer id);

    void delete(Integer idEmploye);

    Utilisateur selectByPrenom(String prenom);

    void addUtilisateur(Utilisateur aAjouter);
}
