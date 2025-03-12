package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Role;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur enregistrerEmploye(String login){
        Utilisateur employe = new Utilisateur();
        employe.setLogin(login);
        //TODO Autogeneration de mdp
        employe.setMdp(passwordEncoder.encode("password").getBytes());
        employe.setRole(new Role("EMP", "Employé"));
        return utilisateurRepository.save(employe);
    }
}
