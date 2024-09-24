package com.example.AuthService.service;


import com.example.AuthService.dto.LoginRequest;
import com.example.AuthService.dto.RegisterRequest;
import com.example.AuthService.exception.InvalidNameException;
import com.example.AuthService.exception.InvalidPasswordException;
import com.example.AuthService.exception.UserAlreadyExistsException;
import com.example.AuthService.exception.UserNotFoundException;
import com.example.AuthService.model.Role;
import com.example.AuthService.model.User;
import com.example.AuthService.repository.UserRepository;
import com.example.AuthService.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public void register(RegisterRequest registerRequest) {
        // Валидация имени пользователя
        validateName(registerRequest.getUsername());
        // Валидация пароля
        validatePassword(registerRequest.getPassword());
        // Проверка, существует ли пользователь с таким именем
        checkUserExists(registerRequest.getUsername());
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.valueOf(registerRequest.getRole().toUpperCase()));

        userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {
        validateName(loginRequest.getUsername());
        // Валидация пароля
        validatePassword(loginRequest.getPassword());
        log.info("Attempting to log in user: {}", loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        log.info("User {} logged in successfully.", loginRequest.getUsername());
        return jwtTokenProvider.createToken(authentication);
    }
    private void validatePassword(String password) {
        // Проверка, что пароль не состоит только из цифр
        if (password.matches("\\d+")) {
            throw new InvalidPasswordException("Пароль не может содержать только цифры.");
        }
    }

    private void validateName(String name) {
        // Проверка, что имя состоит только из букв
        if (!name.matches("[a-zA-Zа-яА-ЯёЁ]+")) {
            throw new InvalidNameException("Имя может содержать только буквы.");
        }
    }
    private void checkUserExists(String username) {
        // Проверка, существует ли пользователь с таким именем
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует.");
        }
    }
}