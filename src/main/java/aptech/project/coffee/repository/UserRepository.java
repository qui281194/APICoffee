/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package aptech.project.coffee.repository;

import aptech.project.coffee.models.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *
 * @author ASUS
 */
public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findByEmail(String email);
   @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))")
Optional<User> searchByUsername(@Param("username") String username);
   Optional<User> findByUsername(String username); // Thêm phương thức tìm kiếm theo username
   boolean existsByEmail(String email);
   //hàm tiềm kiếm theo tên dựa có tất cả trong trang
    Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
   
    
   
   @Query("SELECT a FROM User a WHERE a.email= :email AND a.password= :password")
    User checkLogin(@PathVariable("email") String email, 
                   @PathVariable("password") String password );

}
