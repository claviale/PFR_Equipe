package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur findById(Integer id) {
        return utilisateurRepository.findById(id).orElseThrow();
    }

    @Override
    public Utilisateur selectByNom(String nom) {
        return utilisateurRepository.findByNom(nom);
    }

}
