/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package aptech.project.coffee.controller;

import aptech.project.coffee.dto.PageDto;
import aptech.project.coffee.models.Category;
import aptech.project.coffee.service.CategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryRestController {
     @Autowired
    private CategoryService categoryService;
    
      @GetMapping("/all")
    public PageDto<Category> getAllCategories(
            @RequestParam(defaultValue = "1") int pageNo
            ) {
        Page<Category> categoryPage = categoryService.getAll(pageNo);

        PageDto<Category> pageDto = new PageDto<>();
        pageDto.setContent(categoryPage.getContent());
        pageDto.setTotalPages(categoryPage.getTotalPages());

        return pageDto;
    }
    @GetMapping("/details/{id}")
    public Optional<Category> getCategoryDetails(@PathVariable Integer id) {
        return categoryService.getDetails(id);
    }

    
     // Tạo mới một loại dịch vụ
    @PostMapping("/create")
    public ResponseEntity<Category> function_createServiceCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable Integer id, @RequestBody Category newCategory) {
        return categoryService.updateCategory(id, newCategory);
    }

   

//    @GetMapping("/search")
//    public List<Category> searchCategoryByName(@RequestParam String name) {
//        return categoryService.searchCategoryByName(name);
//    }
//    
    @GetMapping("/search")
public PageDto<Category> searchByUsername(
        @RequestParam("name") String name,
        @RequestParam(defaultValue = "1") Integer pageNo
) {
    // Tìm kiếm người dùng theo username từ service
    Page<Category> categoryPage = categoryService.searchCategoryByNamePage(name, pageNo);

    // Tạo PageDto để đóng gói kết quả trả về
    PageDto<Category> pageDto = new PageDto<>();
    pageDto.setContent(categoryPage.getContent()); // Set nội dung của trang
    pageDto.setTotalPages(categoryPage.getTotalPages()); // Set tổng số trang
   

    return pageDto;
}
   
    
}
