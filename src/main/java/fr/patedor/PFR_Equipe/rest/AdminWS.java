package fr.patedor.PFR_Equipe.rest;

import fr.patedor.PFR_Equipe.dto.RestaurantDTO;
import fr.patedor.PFR_Equipe.dto.UtilisateurDTO;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.Role;
import fr.patedor.PFR_Equipe.entity.Utilisateur;
import fr.patedor.PFR_Equipe.service.RestaurantService;
import fr.patedor.PFR_Equipe.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminWS {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAll() {
        return  ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{id_restaurant}")
    public ResponseEntity<List<UtilisateurDTO>> getEmployes(@PathVariable("id_restaurant") Integer idRestaurant) {
        return ResponseEntity.ok(utilisateurService.findFromRestaurant(idRestaurant));
    }

    /*TODO
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
