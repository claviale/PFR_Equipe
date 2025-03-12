package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UtilisateurService {

    Utilisateur enregistrerEmploye(String login);

    List<Utilisateur> getAll();
}
