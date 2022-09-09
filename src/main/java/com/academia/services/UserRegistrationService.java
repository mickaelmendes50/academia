package com.academia.services;

import com.academia.dto.UserRegistrationDTO;
import com.academia.entities.User;
import com.academia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    public String registerUser(UserRegistrationDTO userDTO) throws Exception {
        boolean userExists = userRepository.existsById(userDTO.getEmail());

        if (!userExists) {
            String password = userDTO.getPassword();

            User user = User.builder()
                    .email(userDTO.getEmail())
                    .password(encoder.encode(password))
                    .username(userDTO.getName())
                    .build();

            userRepository.save(user);
            return "User " + user.getEmail() +  " created";
        } else {
            throw new Exception("User " + userDTO.getEmail() + " already exists");
        }
    }
}
