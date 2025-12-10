package com.user.user.service;

import com.user.user.exception.UserNotFoundException;
import com.user.user.model.User;
import com.user.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Tüm kullanıcıları getir
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID'ye göre kullanıcı getir
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    // Yeni kullanıcı oluştur
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Kullanıcı güncelleme işlemi
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRoleId(userDetails.getRoleId());
        user.setGroupId(userDetails.getGroupId()); // Yeni eklenen alanın güncellenmesi

        return userRepository.save(user);
    }

    // Kullanıcı silme işlemi
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Kullanıcının rolünü güncelle
    public User updateUserRole(Long id, Long roleId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setRoleId(roleId);
        return userRepository.save(user);
    }

    // Kullanıcının grubunu güncelle (Yeni eklenen metod)
    public User updateUserGroup(Long id, Long groupId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setGroupId(groupId); // Yeni grup ID'si ayarlanıyor
        return userRepository.save(user);
    }
}



