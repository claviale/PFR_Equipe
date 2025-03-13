package fr.patedor.PFR_Equipe.mapper;

import fr.patedor.PFR_Equipe.dto.UtilisateurDTO;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public UtilisateurDTO toDTO(Utilisateur utilisateur) {
        UtilisateurDTO result = new UtilisateurDTO();
        result.setId(utilisateur.getId());
        result.setNom(utilisateur.getNom());
        result.setPrenom(utilisateur.getPrenom());
        result.setLogin(utilisateur.getLogin());
        return result;
    }

    public Utilisateur toEntity(UtilisateurDTO utilisateurDTO){
        return Utilisateur.builder()
                .id(utilisateurDTO.getId())
                .nom(utilisateurDTO.getNom())
                .prenom(utilisateurDTO.getPrenom())
                .login(utilisateurDTO.getLogin())
                .build();
    }

}
