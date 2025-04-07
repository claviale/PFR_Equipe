package fr.patedor.PFR_Equipe.controller;

import fr.patedor.PFR_Equipe.dto.RestaurantDTO;
import fr.patedor.PFR_Equipe.dto.EmployeDTO;
import fr.patedor.PFR_Equipe.entity.Restaurant;
import fr.patedor.PFR_Equipe.entity.Role;
import fr.patedor.PFR_Equipe.entity.Employe;
import fr.patedor.PFR_Equipe.mapper.RestaurantMapper;
import fr.patedor.PFR_Equipe.mapper.EmployeMapper;
import fr.patedor.PFR_Equipe.service.RestaurantService;
import fr.patedor.PFR_Equipe.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestaurantMapper restaurantMapper;

    @Autowired
    EmployeService employeService;

    @Autowired
    EmployeMapper employeMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAll() {
        List<RestaurantDTO> restaurants = restaurantService.findAll().stream()
                .map(restaurant -> restaurantMapper.toDTO(restaurant))
                .collect(Collectors.toList());
        return  ResponseEntity.ok(restaurants);
    }


    @GetMapping("/{id_restaurant}")
    public ResponseEntity<List<EmployeDTO>> getEmployes(@PathVariable("id_restaurant") Integer idRestaurant) {
        List<EmployeDTO> employes = employeService.findFromRestaurant(idRestaurant).stream()
                .map(utilisateur -> employeMapper.toDTO(utilisateur))
                .collect(Collectors.toList());
        return ResponseEntity.ok(employes);
    }

    @CrossOrigin
    @PostMapping("/{id_restaurant}")
    public ResponseEntity<EmployeDTO> addEmploye(@RequestBody EmployeDTO utilisateur, @PathVariable("id_restaurant") Integer idRestaurant) {
        Optional<Restaurant> restaurant = restaurantService.findById(idRestaurant);
        Employe aAjouter = Employe.builder()
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .login(utilisateur.getLogin())
                .email(utilisateur.getEmail())
                .telephone(utilisateur.getTelephone())
                .mdp(passwordEncoder.encode(utilisateur.getLogin().toLowerCase()))
                .role(new Role("EMP", "Employé"))
                .build();
        restaurant.ifPresent(aAjouter::setRestaurant);
        employeService.addEmploye(aAjouter);
        return ResponseEntity.ok(employeMapper.toDTO(aAjouter));
    }

    @CrossOrigin
    @PutMapping("/{id_restaurant}/{id_employe}")
    public ResponseEntity<EmployeDTO> updateEmploye(@PathVariable("id_restaurant") Integer idRestaurant, @PathVariable("id_employe") Integer idEmploye, @RequestBody EmployeDTO employe){
        Optional<Restaurant> restaurant = restaurantService.findById(idRestaurant);
        Employe aModifier = employeService.findById(idEmploye);
        aModifier.setNom(employe.getNom());
        aModifier.setPrenom(employe.getPrenom());
        aModifier.setEmail(employe.getEmail());
        aModifier.setTelephone(employe.getTelephone());
        aModifier.setLogin(employe.getLogin());
        aModifier.setId(idEmploye);
        aModifier.setRole(new Role("EMP", "Employé"));
        restaurant.ifPresent(aModifier::setRestaurant);
        employeService.addEmploye(aModifier);
        return ResponseEntity.ok(employeMapper.toDTO(aModifier));
    }

    @CrossOrigin
    @DeleteMapping("/{id_restaurant}/{id_employe}")
    public void deleteEmploye(@PathVariable("id_employe") Integer idEmploye) {
        employeService.delete(idEmploye);
    }


}
