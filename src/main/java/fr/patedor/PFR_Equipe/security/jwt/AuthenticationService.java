package fr.patedor.PFR_Equipe.security.jwt;

import fr.patedor.PFR_Equipe.entity.Employe;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getMdp()));

        //Génère le token
        Employe employe = employeRepository.findByLogin(request.getLogin()).orElseThrow();
        String jwtToken = jwtService.generateToken(employe);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setToken(jwtToken);

        //Ajoute l'utilisateur connecté au contexte de sécurité
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authResponse;
    }
}
