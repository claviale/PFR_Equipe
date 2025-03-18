package fr.patedor.PFR_Equipe.controller;

import fr.patedor.PFR_Equipe.dto.RestaurantDTO;
import fr.patedor.PFR_Equipe.dto.UtilisateurDTO;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.Role;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.mapper.RestaurantMapper;
import fr.patedor.PFR_Equipe.mapper.UtilisateurMapper;
import fr.patedor.PFR_Equipe.service.RestaurantService;
import fr.patedor.PFR_Equipe.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestaurantMapper restaurantMapper;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    UtilisateurMapper utilisateurMapper;

    //A décommenter lorsque l'on aura merge Security
    /*
    @Autowired
    PasswordEncoder passwordEncoder;
    */

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAll() {
        List<RestaurantDTO> restaurants = restaurantService.findAll().stream()
                .map(restaurant -> restaurantMapper.toDTO(restaurant))
                .collect(Collectors.toList());
        return  ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id_restaurant}")
    public ResponseEntity<List<UtilisateurDTO>> getEmployes(@PathVariable("id_restaurant") Integer idRestaurant) {
        List<UtilisateurDTO> utilisateurs = utilisateurService.findFromRestaurant(idRestaurant).stream()
                .map(utilisateur -> utilisateurMapper.toDTO(utilisateur))
                .collect(Collectors.toList());
        return ResponseEntity.ok(utilisateurs);
    }

    @PostMapping("/{id_restaurant}")
    public ResponseEntity<UtilisateurDTO> addEmploye(@RequestBody UtilisateurDTO utilisateur, @PathVariable("id_restaurant") Integer idRestaurant) {
        Optional<Restaurant> restaurant = restaurantService.findById(idRestaurant);
        Utilisateur aAjouter = Utilisateur.builder()
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .login(utilisateur.getLogin())
                .email(utilisateur.getEmail())
                .telephone(utilisateur.getTelephone())
                .mdp(utilisateur.getLogin().toLowerCase()) //TODO A remplacer par passwordEncoder.encode(utilisateur.getLogin().toLowerCase()) une fois la sécurité merged
                .role(new Role("EMP", "Employé"))
                .build();
        restaurant.ifPresent(aAjouter::setRestaurant);
        utilisateurService.addUtilisateur(aAjouter);
        return ResponseEntity.ok(utilisateurMapper.toDTO(aAjouter));
    }


    @PutMapping("/{id_restaurant}/{id_employe}")
    public ResponseEntity<UtilisateurDTO> updateEmploye(@PathVariable("id_restaurant") Integer idRestaurant, @PathVariable("id_employe") Integer idEmploye, @RequestBody UtilisateurDTO employe){
        Optional<Restaurant> restaurant = restaurantService.findById(idRestaurant);
        Utilisateur aModifier = utilisateurMapper.toEntity(employe);
        aModifier.setId(idEmploye);
        aModifier.setRole(new Role("EMP", "Employé"));
        restaurant.ifPresent(aModifier::setRestaurant);
        utilisateurService.addUtilisateur(aModifier);
        return ResponseEntity.ok(utilisateurMapper.toDTO(aModifier));
    }


    @DeleteMapping("/{id_restaurant}/{id_employe}")
    public void deleteEmploye(@PathVariable("id_employe") Integer idEmploye) {
        utilisateurService.delete(idEmploye);
    }


}
