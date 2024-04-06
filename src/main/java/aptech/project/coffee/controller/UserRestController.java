/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package aptech.project.coffee.controller;

import aptech.project.coffee.dto.LoginDto;
import aptech.project.coffee.dto.PageDto;
import aptech.project.coffee.dto.UserDto;
import aptech.project.coffee.models.User;

import aptech.project.coffee.service.UsersService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    UsersService usersService;

    @GetMapping("/all")
    public PageDto<UserDto> function_allUser(
            @RequestParam(defaultValue = "1") Integer pageNo
    ) {
        Page<UserDto> userPage = usersService.getAll(pageNo);
        PageDto<UserDto> pageDto = new PageDto<>();
        pageDto.setContent(userPage.getContent());
        pageDto.setTotalPages(userPage.getTotalPages());
        pageDto.setTotalElements(userPage.getTotalElements());
        return pageDto;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> function_login(
            @RequestBody LoginDto loginDto
    ) {

        UserDto loggedInUser = usersService.login(loginDto);
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> function_createNewUser(
            @RequestBody UserDto newUserDto
    ) {
        //        return _userService.createNewUser(newUserDto);
        UserDto createUser = usersService.createNewUser(newUserDto);

        if (createUser != null) {
            return ResponseEntity.ok(createUser); //đăng ký thành công trả về OK
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // ngược lại email có r trả về CONFLICT
        }
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<UserDto> function_editUser(@PathVariable("id") int id) {
        UserDto userDetails = usersService.findById(id);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> updateBlog(@PathVariable Integer id, @RequestBody UserDto updatedBlog) {
        UserDto updated = usersService.updateUser(updatedBlog);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> function_registerNewCustomer(
            @RequestBody UserDto newUserDto
    ) {
        //        return _userService.registerNewCustomer(newUserDto);
        UserDto registeredUser = usersService.registerNewCustomer(newUserDto);

        if (registeredUser != null) {
            return ResponseEntity.ok(registeredUser); //đăng ký thành công trả về OK
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // ngược lại email có r trả về CONFLICT
        }
    }

    @GetMapping("/details/{email}")
    public ResponseEntity<UserDto> function_details(
            @PathVariable("email") String email
    ) {
        UserDto userDetails = usersService.details(email);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detailsById/{id}")
    public ResponseEntity<UserDto> function_detailsById(
            @PathVariable("id") int id
    ) {
        UserDto userDetails = usersService.findOne(id);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/check-email")
    public ResponseEntity<Void> checkEmailExists(@RequestBody String email) {
        boolean emailExists = usersService.existsByEmail(email);
        if (emailExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Email đã tồn tại, trả về CONFLICT
        } else {
            return ResponseEntity.ok().build(); // Email chưa tồn tại, trả về OK
        }
    }

  @GetMapping("/search")
public PageDto<UserDto> searchByUsername(
        @RequestParam("username") String username,
        @RequestParam(defaultValue = "1") Integer pageNo
) {
    // Tìm kiếm người dùng theo username từ service
    Page<UserDto> userPage = usersService.searchByUsername(username, pageNo);

    // Tạo PageDto để đóng gói kết quả trả về
    PageDto<UserDto> pageDto = new PageDto<>();
    pageDto.setContent(userPage.getContent()); // Set nội dung của trang
    pageDto.setTotalPages(userPage.getTotalPages()); // Set tổng số trang
    pageDto.setTotalElements(userPage.getTotalElements()); // Set tổng số phần tử

    return pageDto;
}






}
