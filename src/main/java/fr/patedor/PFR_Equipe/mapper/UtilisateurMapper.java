package fr.patedor.PFR_Equipe.mapper;

import fr.patedor.PFR_Equipe.dto.UtilisateurDTO;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public UtilisateurDTO toDTO(Utilisateur utilisateur) {
        return UtilisateurDTO.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .login(utilisateur.getLogin())
                .email(utilisateur.getEmail())
                .telephone(utilisateur.getTelephone())
                .mdp(utilisateur.getMdp())
                .build();
    }

    public Utilisateur toEntity(UtilisateurDTO utilisateurDTO){
        return Utilisateur.builder()
                .id(utilisateurDTO.getId())
                .nom(utilisateurDTO.getNom())
                .prenom(utilisateurDTO.getPrenom())
                .login(utilisateurDTO.getLogin())
                .email(utilisateurDTO.getEmail())
                .telephone(utilisateurDTO.getTelephone())
                .mdp(utilisateurDTO.getMdp())
                .build();
    }

}
