package com.example.NguyenVanHuyTest.controller;

import com.example.NguyenVanHuyTest.dto.UserSearchRequest;
import com.example.NguyenVanHuyTest.entity.User;
import com.example.NguyenVanHuyTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id).orElse(null);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
       userService.delete(id);
       return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<User>> searchUsers(UserSearchRequest request, Pageable pageable) {
        return ResponseEntity.ok(userService.search(request, pageable));
    }
}