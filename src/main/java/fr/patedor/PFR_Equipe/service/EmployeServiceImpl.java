package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Employe;
import fr.patedor.PFR_Equipe.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    EmployeRepository employeRepository;

    @Override
    public List<Employe> findFromRestaurant(Integer id) {
        return employeRepository.findFromRestaurant(id);
    }

    @Override
    public Employe findById(Integer id) {
        return employeRepository.findById(id).orElseThrow();
    }

    @Override
    public void addEmploye(Employe aAjouter) {
        employeRepository.save(aAjouter);
    }

    @Override
    public void delete(Integer idEmploye) {
        Optional<Employe> aSupprimer = employeRepository.findById(idEmploye);
        aSupprimer.ifPresent(utilisateur -> employeRepository.delete(utilisateur));
    }
    
}
