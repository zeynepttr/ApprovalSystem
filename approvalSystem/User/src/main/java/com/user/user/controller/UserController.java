package com.user.user.controller;

import com.user.user.dtb.UserDTO;
import com.user.user.model.User;
import com.user.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Tüm kullanıcıları getir
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ID'ye göre kullanıcı getir
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Yeni kullanıcı oluştur
    @PostMapping
    public User createUser(@RequestBody @Valid UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRoleId(userDTO.getRoleId());
        user.setGroupId(userDTO.getGroupId()); // Yeni eklenen alan
        return userService.createUser(user);
    }

    // Kullanıcı güncelleme işlemi
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        User userDetails = new User();
        userDetails.setUsername(userDTO.getUsername());
        userDetails.setPassword(userDTO.getPassword());
        userDetails.setRoleId(userDTO.getRoleId());
        userDetails.setGroupId(userDTO.getGroupId()); // Yeni eklenen alan
        return userService.updateUser(id, userDetails);
    }

    // Kullanıcı silme işlemi
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Kullanıcının rolünü güncelleme
    @PatchMapping("/{id}/role")
    public User updateUserRole(@PathVariable Long id, @RequestParam Long roleId) {
        return userService.updateUserRole(id, roleId);
    }

    // Kullanıcının grubunu güncelleme (Yeni metod)
    @PatchMapping("/{id}/group")
    public User updateUserGroup(@PathVariable Long id, @RequestParam Long groupId) {
        return userService.updateUserGroup(id, groupId);
    }
}
