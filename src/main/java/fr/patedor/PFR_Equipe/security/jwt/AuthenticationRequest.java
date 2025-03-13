package fr.patedor.PFR_Equipe.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(of = "pseudo")
public class AuthenticationRequest {
    private String login;
    private String mdp;
}
