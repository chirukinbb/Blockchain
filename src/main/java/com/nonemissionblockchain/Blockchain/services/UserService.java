package com.nonemissionblockchain.Blockchain.services;

import com.nonemissionblockchain.Blockchain.contracts.UserRepository;
import com.nonemissionblockchain.Blockchain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
