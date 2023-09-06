package me.kavinduchamiran.postharbor.deployers;

import com.kavinduchamiran.postharbor.entities.privilege.Privilege;
import com.kavinduchamiran.postharbor.entities.role.Role;
import com.kavinduchamiran.postharbor.entities.user.IUserRepository;
import com.kavinduchamiran.postharbor.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class StartupDeployers implements ApplicationRunner {
    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Privilege privilege1 = Privilege.builder()
                                        .name("privilege1")
                                        .build();
        Privilege privilege2 = Privilege.builder()
                                        .name("privilege2")
                                        .build();

        Role role1 = Role.builder()
                         .name("role1")
                         .privileges(Set.of(privilege1, privilege2))
                         .build();

        User user1 = User.builder()
                         .username("testuser")
                         .firstName("Test")
                         .lastName("User")
                         .email("test@user.com")
                         .profilePicture("https://www.google.com")
                         .password(passwordEncoder.encode("testpassword"))
                         .roles(Set.of(role1))
                         .build();

        userRepository.save(user1);
    }
}
