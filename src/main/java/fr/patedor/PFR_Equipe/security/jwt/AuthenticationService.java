package fr.patedor.PFR_Equipe.security.jwt;

import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.repository.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private UtilisateurRepository uRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getMdp()));
        Utilisateur user = uRepository.findByLogin(request.getLogin()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}
