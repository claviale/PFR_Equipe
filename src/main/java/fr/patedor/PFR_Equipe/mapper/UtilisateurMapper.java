package fr.patedor.PFR_Equipe.mapper;

import fr.patedor.PFR_Equipe.dto.UtilisateurDTO;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    @Autowired
    UtilisateurService utilisateurService;

    public UtilisateurDTO toDTO(Utilisateur utilisateur) {
        return UtilisateurDTO.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .telephone(utilisateur.getTelephone())
                .build();
    }

    public Utilisateur toEntity(UtilisateurDTO utilisateurDTO) {
        return Utilisateur.builder()
                .id(utilisateurDTO.getId())
                .nom(utilisateurDTO.getNom())
                .prenom(utilisateurDTO.getPrenom())
                .email(utilisateurDTO.getEmail())
                .telephone(utilisateurDTO.getTelephone())
                .mdp("Default".getBytes())
                .salt("Default".getBytes())
                .build();
    }
}
