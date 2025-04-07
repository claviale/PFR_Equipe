package fr.patedor.PFR_Equipe.security.jwt;

import fr.patedor.PFR_Equipe.entity.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        //Si l'authenticationManager authentifie un employé en base, on récupère ce dernier dans l'objet Authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getMdp()));
        Employe employe = (Employe) authentication.getPrincipal();

        //Génère le token
        String jwtToken = jwtService.generateToken(employe);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setToken(jwtToken);
        if("EMP".equals(employe.getRole().getId())){
            authResponse.setIdRestaurant(employe.getRestaurant().getIdRestaurant());
        }

        //Ajoute l'utilisateur connecté au contexte de sécurité
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authResponse;
    }
}
