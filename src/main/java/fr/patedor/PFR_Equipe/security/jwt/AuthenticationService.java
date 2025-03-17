package fr.patedor.PFR_Equipe.security.jwt;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getMdp()));

        //Génère le token
        Utilisateur utilisateur = utilisateurRepository.findByLogin(request.getLogin()).orElseThrow();
        String jwtToken = jwtService.generateToken(utilisateur);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setToken(jwtToken);

        //Ajoute l'utilisateur connecté au contexte de sécurité
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authResponse;
    }
}
