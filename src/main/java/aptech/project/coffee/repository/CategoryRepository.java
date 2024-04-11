/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package aptech.project.coffee.repository;

import aptech.project.coffee.models.Category;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ASUS
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Tìm kiếm danh mục theo tên
//    @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
//    List<Category> findByNameContainingIgnoreCase(String name);
    
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
