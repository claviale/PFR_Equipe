package fr.patedor.PFR_Equipe.mapper;

import fr.patedor.PFR_Equipe.dto.EmployeDTO;
import fr.patedor.PFR_Equipe.entity.Employe;
import org.springframework.stereotype.Component;

@Component
public class EmployeMapper {

    public EmployeDTO toDTO(Employe employe) {
        return EmployeDTO.builder()
                .id(employe.getId())
                .nom(employe.getNom())
                .prenom(employe.getPrenom())
                .login(employe.getLogin())
                .email(employe.getEmail())
                .telephone(employe.getTelephone())
                .mdp(employe.getMdp())
                .build();
    }

    public Employe toEntity(EmployeDTO employeDTO){
        return Employe.builder()
                .id(employeDTO.getId())
                .nom(employeDTO.getNom())
                .prenom(employeDTO.getPrenom())
                .login(employeDTO.getLogin())
                .email(employeDTO.getEmail())
                .telephone(employeDTO.getTelephone())
                .mdp(employeDTO.getMdp())
                .build();
    }

}
