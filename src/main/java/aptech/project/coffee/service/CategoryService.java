/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package aptech.project.coffee.service;

import aptech.project.coffee.models.Category;
import aptech.project.coffee.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Hiển thị tất cả các danh mục
    public List<Category> showAllCategories() {
        return categoryRepository.findAll();
    }

    // Sửa lại phương thức getAll để trả về một trang danh mục
    public Page<Category> getAll(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return categoryRepository.findAll(pageable);
    }

    // Tạo một danh mục mới
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Cập nhật thông tin của một danh mục
    public Category updateCategory(Integer id, Category newCategory) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(newCategory.getName());
            return categoryRepository.save(existingCategory);
        } else {
            // Xử lý trường hợp không tìm thấy danh mục với id tương ứng
            return null; // hoặc bạn có thể throw một Exception tương ứng
        }
    }

    // Xóa một danh mục
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
    

//    // Tìm kiếm danh mục theo tên
//    public List<Category> searchCategoryByName(String name) {
//        return categoryRepository.findByNameContainingIgnoreCase(name);
//    }
    
    // Hiển thị thông tin chi tiết của một danh mục dựa trên ID
    public Optional<Category> getDetails(Integer id) {
        return categoryRepository.findById(id);
    }
    
   // Tìm kiếm danh mục theo tên và trả về một trang kết quả
public Page<Category> searchCategoryByNamePage(String name, Integer pageNo) {
    int pageSize = 30; // Kích thước của mỗi trang

    // Thực hiện tìm kiếm theo tên và phân trang
    return categoryRepository.findByNameContainingIgnoreCase(name, PageRequest.of(pageNo - 1, pageSize));
}




}
