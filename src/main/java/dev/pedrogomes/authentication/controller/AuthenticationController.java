package dev.pedrogomes.authentication.controller;

import dev.pedrogomes.authentication.dto.user.AuthenticationDTO;
import dev.pedrogomes.authentication.dto.user.LoginResponseDTO;
import dev.pedrogomes.authentication.services.AuthenticationService;
import dev.pedrogomes.authentication.utils.enums.UserRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        LoginResponseDTO response = authenticationService.login(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @Valid AuthenticationDTO data) {
        System.out.println("Iniciando o registro de um novo usuario " + data.email());
        LoginResponseDTO response = authenticationService.register(data, UserRole.USER);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<LoginResponseDTO> registerAdmin(@RequestBody @Valid AuthenticationDTO data) {
        LoginResponseDTO response = authenticationService.register(data, UserRole.ADMIN);
        return ResponseEntity.ok(response);
    }
}
