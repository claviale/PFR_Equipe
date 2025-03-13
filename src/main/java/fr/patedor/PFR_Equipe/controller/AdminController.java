package fr.patedor.PFR_Equipe.controller;

import fr.patedor.PFR_Equipe.dto.RestaurantDTO;
import fr.patedor.PFR_Equipe.dto.UtilisateurDTO;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.mapper.RestaurantMapper;
import fr.patedor.PFR_Equipe.mapper.UtilisateurMapper;
import fr.patedor.PFR_Equipe.service.RestaurantService;
import fr.patedor.PFR_Equipe.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
     /*
    @PostMapping("/{id_restaurant}")
    public void addEmploye(UtilisateurDTO utilisateur, @PathVariable("id_restaurant") Integer idRestaurant) {
        Optional<Restaurant> restaurant = restaurantService.findById(idRestaurant);
        Utilisateur aAjouter = Utilisateur.builder()
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .build();
        System.out.println(aAjouter);
        //TODO ajouter autogeneration de MDP
        //pour le moment,on récupere celui d'Etienne
        Utilisateur etienne = utilisateurService.selectByPrenom("Etienne");
        aAjouter.setLogin(utilisateur.getPrenom());
        aAjouter.setMdp(etienne.getMdp());
        aAjouter.setSalt(etienne.getSalt());
        aAjouter.setRole(new Role("EMP", "Employé"));
        restaurant.ifPresent(aAjouter::setRestaurant);
        System.out.println(aAjouter);
        utilisateurService.addUtilisateur(aAjouter);
    }
    */

    @DeleteMapping("/{id_restaurant}/{id_employe}")
    public void deleteEmploye(@PathVariable("id_employe") Integer idEmploye) {
        utilisateurService.delete(idEmploye);
    }


}
