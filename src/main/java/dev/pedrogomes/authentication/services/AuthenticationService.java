package dev.pedrogomes.authentication.services;


import dev.pedrogomes.authentication.dto.user.AuthenticationDTO;
import dev.pedrogomes.authentication.dto.user.LoginResponseDTO;
import dev.pedrogomes.authentication.entity.User;
import dev.pedrogomes.authentication.entity.UserSalty;
import dev.pedrogomes.authentication.exception.UserAlreadyExistsException;
import dev.pedrogomes.authentication.infra.security.TokenService;
import dev.pedrogomes.authentication.repository.UserRepository;
import dev.pedrogomes.authentication.utils.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SaltyService saltyService;

    public LoginResponseDTO login(AuthenticationDTO data) {
        var saltyValue = saltyService.getUserSalty(userRepository.findUserByEmail(data.email()));
        String saltedPassword = data.password() + saltyValue;
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), saltedPassword);
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public LoginResponseDTO register(AuthenticationDTO data, UserRole role) {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new UserAlreadyExistsException("User with email " + data.email() + " already exists");
        }

        String saltyValue = saltyService.generateSalty();
        String saltedPassword = data.password() + saltyValue;

        String encryptedPassword = new BCryptPasswordEncoder().encode(saltedPassword);
        User newUser = new User(data.email(), encryptedPassword, role);
        userRepository.save(newUser);

        UserSalty userSalty = saltyService.saltyUser(newUser, saltyValue);

        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), saltedPassword));
        var token = tokenService.generateToken(newUser);

        return new LoginResponseDTO(token);
    }
}
