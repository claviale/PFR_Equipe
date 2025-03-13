package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.dto.UtilisateurDTO;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurImpl implements UtilisateurService{

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public List<UtilisateurDTO> findFromRestaurant(Integer id) {
        List<UtilisateurDTO> utilisateurs = new ArrayList<>();
        for(Utilisateur cur : utilisateurRepository.findFromRestaurant(id)){
            utilisateurs.add(new UtilisateurDTO(cur));
        }
        return utilisateurs;
    }

    @Override
    public void addUtilisateur(Utilisateur aAjouter) {
        utilisateurRepository.save(aAjouter);
    }

    @Override
    public void delete(Integer idEmploye) {
        Optional<Utilisateur> aSupprimer = utilisateurRepository.findById(idEmploye);
        aSupprimer.ifPresent(utilisateur -> utilisateurRepository.delete(utilisateur));
    }

    @Override
    public Utilisateur selectByPrenom(String prenom) {
        return utilisateurRepository.findByPrenom("Etienne");
    }
}
