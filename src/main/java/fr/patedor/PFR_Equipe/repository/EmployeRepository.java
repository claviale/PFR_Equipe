package fr.patedor.PFR_Equipe.repository;

import fr.patedor.PFR_Equipe.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {

    @Query("FROM Employe e WHERE e.restaurant.id = :id")
    List<Employe> findFromRestaurant(@Param("id") Integer id);

    Employe findByPrenom(String prenom);

    Employe findByNom(String nom);
}
