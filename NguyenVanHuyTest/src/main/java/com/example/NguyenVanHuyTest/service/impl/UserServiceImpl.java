package com.example.NguyenVanHuyTest.service.impl;

import com.example.NguyenVanHuyTest.dto.UserSearchRequest;
import com.example.NguyenVanHuyTest.entity.User;
import com.example.NguyenVanHuyTest.repository.UserRepository;
import com.example.NguyenVanHuyTest.service.UserService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setAge(user.getAge());
            updatedUser.setSalary(user.getSalary());
            return userRepository.save(updatedUser);
        }
        throw new RuntimeException("User not found with id " + id);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> search(UserSearchRequest request, Pageable pageable) {
        return userRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));
            }
            if (request.getAge() != null) {
                predicates.add(criteriaBuilder.equal(root.get("age"), request.getAge()));
            }
            if (request.getSalary() != null) {
                predicates.add(criteriaBuilder.equal(root.get("salary"), request.getSalary()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
}
