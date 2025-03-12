package fr.patedor.PFR_Equipe.controller;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class AuthentificationController {

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAll(){
        return ResponseEntity.ok(utilisateurService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> inscription(@RequestBody String login) {
        utilisateurService.enregistrerEmploye(login);
        return ResponseEntity.ok("Utilisateur enregistré avec succès");
    }

    /*
    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody AuthRequest request) {
        //connexion de l'utilisateur
        //mettre le token de connexion dans le return
        return ResponseEntity.ok("Connexion réussie");
    }
    */
}
