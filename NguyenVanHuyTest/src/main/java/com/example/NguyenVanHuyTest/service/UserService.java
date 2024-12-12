package com.example.NguyenVanHuyTest.service;

import com.example.NguyenVanHuyTest.dto.UserSearchRequest;
import com.example.NguyenVanHuyTest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User create(User user);
    User update(Long id, User user);
    Optional<User> getById(Long id);
    void delete(Long id);
    Page<User> search(UserSearchRequest request, Pageable pageable);
}
