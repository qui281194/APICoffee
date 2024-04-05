/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package aptech.project.coffee.repository;

import aptech.project.coffee.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *
 * @author ASUS
 */
public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findByEmail(String email);
   boolean existsByEmail(String email);
   
   @Query("SELECT a FROM User a WHERE a.email= :email AND a.password= :password")
    User checkLogin(@PathVariable("email") String email, 
                   @PathVariable("password") String password );

}
