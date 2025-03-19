package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Employe;

import java.util.List;

public interface EmployeService {

    List<Employe> findFromRestaurant(Integer id);

    Employe findById(Integer id);

    void delete(Integer idEmploye);

    void addEmploye(Employe aAjouter);
}
