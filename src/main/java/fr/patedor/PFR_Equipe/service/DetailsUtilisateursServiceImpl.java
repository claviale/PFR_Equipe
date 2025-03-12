package fr.patedor.PFR_Equipe.service;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DetailsUtilisateursServiceImpl implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Cet utilisateur n'est pas en base !"));
        return new User(utilisateur.getLogin(),
                //TODO pour le moment on convertit juste le tableau de byte en string
                //voir si besoin de changer
                Arrays.toString(utilisateur.getMdp()),
                List.of(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole()))
                );
    }

}
