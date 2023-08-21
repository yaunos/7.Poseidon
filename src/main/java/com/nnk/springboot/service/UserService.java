package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     *  READ (ALL) :Get all users from data repository
     *  Return a list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * READ (ONE) : Find a user by id in all users from data source
     */
    public Optional<User> getAUserByItsId(final Integer id) {
        return userRepository.findById(id);
    }

    /**
     * UPDATE/SAVE a user by id in all users from data source
     */
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    /**
     * DELETE a user
     */
    public void deleteTrade (final Integer id) {
        userRepository.deleteById(id);
    }

}
