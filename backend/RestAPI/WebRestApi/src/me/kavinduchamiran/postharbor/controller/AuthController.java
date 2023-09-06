package me.kavinduchamiran.postharbor.controller;

import lombok.RequiredArgsConstructor;
import me.kavinduchamiran.postharbor.models.AuthenticationResponse;
import me.kavinduchamiran.postharbor.models.LoginRequest;
import me.kavinduchamiran.postharbor.models.RegistrationRequest;
import me.kavinduchamiran.postharbor.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
