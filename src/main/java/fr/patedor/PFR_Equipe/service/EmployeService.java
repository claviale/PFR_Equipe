package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Employe;

import java.util.List;

public interface EmployeService {

    List<Employe> findFromRestaurant(Integer id);

    void delete(Integer idEmploye);

    Employe selectByPrenom(String prenom);
    
    Employe selectByNom(String nom);

    void addUtilisateur(Employe aAjouter);
}
