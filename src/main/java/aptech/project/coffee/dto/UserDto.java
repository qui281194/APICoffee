/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aptech.project.coffee.dto;

import aptech.project.coffee.models.Profile;

/**
 *
 * @author ASUS
 */
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private String rpassword;
    private String address;
    private String phone;
    private Boolean status;
    private String gender;
    private String birthday;
    private String image;

    private Integer role_id;
    private RoleDto roleInfo;
    private Integer profile_id;
    private Profile profile;

    public UserDto() {
    }

    public UserDto(Integer id, String username, String email, String password, String rpassword, String address, String phone, Boolean status, String gender, String birthday, String image, Integer role_id, RoleDto roleInfo, Integer profile_id, Profile profile) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.rpassword = rpassword;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.gender = gender;
        this.birthday = birthday;
        this.image = image;
        this.role_id = role_id;
        this.roleInfo = roleInfo;
        this.profile_id = profile_id;
        this.profile = profile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public RoleDto getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleDto roleInfo) {
        this.roleInfo = roleInfo;
    }

    public Integer getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Integer profile_id) {
        this.profile_id = profile_id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
