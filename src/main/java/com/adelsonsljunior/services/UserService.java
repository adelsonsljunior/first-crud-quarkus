package com.adelsonsljunior.services;

import com.adelsonsljunior.dtos.UserRequestDTO;
import com.adelsonsljunior.dtos.UserResponseDTO;
import com.adelsonsljunior.entities.User;
import com.adelsonsljunior.exceptions.ExistingEmailException;
import com.adelsonsljunior.exceptions.ResourceNotFoundException;
import com.adelsonsljunior.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public UserResponseDTO create(UserRequestDTO userRequestDTO) {

        // Verifica se o email já existe em outro usuário
        userRepository.findByEmail(userRequestDTO.email())
                .ifPresent(user -> {
                    throw new ExistingEmailException("Email already exists");
                });

        User user = new User(userRequestDTO);
        userRepository.persist(user);

        return user.toResponseDTO();

    }

    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.listAll();

        return users.stream()
                .map(User::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findById(Long id) {

        User foundUser = userRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));

        return foundUser.toResponseDTO();
    }

    @Transactional
    public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {

        User foundUser = userRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));

        // Verifica se o email já existe em outro usuário
        userRepository.findByEmail(userRequestDTO.email())
                .ifPresent(existingUser -> {
                    // Lança a exceção apenas se o email pertencer a outro usuário
                    if (!existingUser.getId().equals(foundUser.getId())) {
                        throw new ExistingEmailException("Email already exists");
                    }
                });

        foundUser.setUsername(userRequestDTO.username());
        foundUser.setEmail(userRequestDTO.email());
        foundUser.setPassword(userRequestDTO.password());
        userRepository.persist(foundUser);

        return foundUser.toResponseDTO();
    }

    @Transactional
    public void delete(Long id) {

        userRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));

        userRepository.deleteById(id);
    }


}
