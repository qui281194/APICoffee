/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package aptech.project.coffee.service;

import aptech.project.coffee.dto.LoginDto;
import aptech.project.coffee.dto.RoleDto;
import aptech.project.coffee.dto.UserDto;
import aptech.project.coffee.models.Role;
import aptech.project.coffee.models.User;
import aptech.project.coffee.repository.RoleRepository;
import aptech.project.coffee.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class UsersService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    
    

    private User mapToModel(UserDto userDto) {
        User users = new User();
        users.setUsername(userDto.getUsername());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        users.setAddress(userDto.getAddress());
        users.setPhone(userDto.getPhone());
        users.setStatus(true);
        users.setBirthday(userDto.getBirthday());
        users.setGender(userDto.getGender());
        users.setImage(userDto.getImage());
        

        Role roleInfo = roleRepository.findById(userDto.getRole_id()).orElse(null);
        users.setRole_id(roleInfo);

        return users;
    }

    private UserDto mapToDto(User users) {
        UserDto userDto = new UserDto();
        userDto.setId(users.getId());
        userDto.setUsername(users.getUsername());
        userDto.setEmail(users.getEmail());
        userDto.setPassword(users.getPassword());
        userDto.setAddress(users.getAddress());
        userDto.setPhone(users.getPhone());
        userDto.setStatus(users.getStatus());
        userDto.setBirthday(users.getBirthday());
        userDto.setGender(users.getGender());
        userDto.setImage(users.getImage());
        
        

        userDto.setRole_id(users.getRole_id().getId());

        RoleDto roleDto = new RoleDto(
                users.getRole_id().getId(),
                users.getRole_id().getRoleName()
        );
        userDto.setRoleInfo(roleDto);

        return userDto;
    }
 
    //Login Service
    public UserDto login(LoginDto loginDto) {
        Optional<User> optionalUser = userRepository.findByEmail(loginDto.getEmail());
        if (optionalUser.isPresent()) {
            User checkLogin = optionalUser.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(loginDto.getPassword(), checkLogin.getPassword())) {
                return mapToDto(checkLogin);
            }
        }
        return null;
    }
    

    //For Admin ONLY
    public UserDto createNewUser(UserDto userDto) {
        //Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return null; // Trả về null nếu email đã tồn tại
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        // Thiết lập giá trị mặc định cho trường "status" là "false" nếu không được chọn

        User newUser = mapToModel(userDto);
        newUser.setStatus(Boolean.FALSE);

        User responseUser = userRepository.save(newUser);

        return mapToDto(responseUser);
    }

    public UserDto updateUser(UserDto updatedUserDto) {
        Optional<User> optionalUser = userRepository.findById(updatedUserDto.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update the user information
            existingUser.setUsername(updatedUserDto.getUsername());
            existingUser.setEmail(updatedUserDto.getEmail());
            existingUser.setAddress(updatedUserDto.getAddress());
            existingUser.setPhone(updatedUserDto.getPhone());
            existingUser.setStatus(updatedUserDto.getStatus());
            existingUser.setBirthday(updatedUserDto.getBirthday());
            existingUser.setGender(updatedUserDto.getGender());
            
            // Truy xuất đối tượng Role từ cơ sở dữ liệu dựa trên role_id trong updatedUserDto
        Role roleInfo = roleRepository.findById(updatedUserDto.getRole_id()).orElse(null);
        existingUser.setRole_id(roleInfo); // Gán đối tượng Role cho trường role_id của đối tượng User
            

            User updatedUser = userRepository.save(existingUser);
            return mapToDto(updatedUser);
        }
        return null;
    }

    public UserDto findOne(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return mapToDto(user);
        }
        return null;
    }

    public UserDto registerNewCustomer(UserDto userDto) {
        // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return null; // Trả về null nếu email đã tồn tại
        }
        userDto.setRole_id(3);

        //Mã hóa mật khẩu trước khi lưu trữ vào cơ sở dữ liệu
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        User newCustomer = mapToModel(userDto);

        User responseUser = userRepository.save(newCustomer);

        return mapToDto(responseUser);
    }

    //Kiểm tra trùng email
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public List<UserDto> allUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Page<UserDto> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(this::mapToDto);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDto details(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return mapToDto(user);
        }
        return null;
    }
    public UserDto findById(int id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        return mapToDto(user);
    }
    return null;
}

}
