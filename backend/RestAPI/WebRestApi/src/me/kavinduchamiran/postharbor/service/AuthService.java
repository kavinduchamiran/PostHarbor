package me.kavinduchamiran.postharbor.service;

import com.kavinduchamiran.postharbor.entities.role.Role;
import com.kavinduchamiran.postharbor.entities.user.IUserRepository;
import com.kavinduchamiran.postharbor.entities.user.User;
import lombok.RequiredArgsConstructor;
import me.kavinduchamiran.postharbor.models.AuthenticationResponse;
import me.kavinduchamiran.postharbor.models.LoginRequest;
import me.kavinduchamiran.postharbor.models.RegistrationRequest;
import me.kavinduchamiran.postharbor.utilities.DateUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request) {
        User user = User.builder()
                        .username(request.getUsername())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .roles(Set.of(Role.builder()
                                          .name("role1")
                                          .build()))
                        .createdAt(DateUtils.getNow())
                        .updatedAt(DateUtils.getNow())
                        .build();

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                                     .token(jwtToken)
                                     .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            String jwtToken = jwtService.generateToken(user.get());

            return AuthenticationResponse.builder()
                                         .token(jwtToken)
                                         .build();
        }

        return null;
    }
}
