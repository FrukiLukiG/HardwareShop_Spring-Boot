package hr.tvz.plese.hardwareapp.security.service;

import hr.tvz.plese.hardwareapp.security.command.LoginCommand;
import hr.tvz.plese.hardwareapp.security.domain.Users;
import hr.tvz.plese.hardwareapp.security.dto.LoginDTO;
import hr.tvz.plese.hardwareapp.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final hr.tvz.plese.hardwareapp.security.service.JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(hr.tvz.plese.hardwareapp.security.service.JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<Users> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();

        return b.matches(rawPassword, encryptedPassword);
    }
}
