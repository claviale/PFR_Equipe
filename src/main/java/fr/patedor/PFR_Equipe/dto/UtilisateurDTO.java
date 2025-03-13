package fr.patedor.PFR_Equipe.dto;

import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {

    //TODO see if there is a need to add token to attributes
    private Integer id;
    private String nom;
    private String prenom;
    /*
    private String login;
    private byte[] mdp;
    private byte[] salt;
    */

    public UtilisateurDTO(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        /*
        this.login = utilisateur.getLogin();
        this.mdp = utilisateur.getMdp();
        this.salt = utilisateur.getSalt();
        */
    }

    public Utilisateur toEntity(){
        return Utilisateur.builder()
                .id(id)
                .nom(nom)
                .prenom(prenom)
                /*
                .login(login)
                .mdp(mdp)
                .salt(salt)
                */
                .build();
    }
}
