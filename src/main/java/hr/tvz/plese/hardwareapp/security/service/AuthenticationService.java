package hr.tvz.plese.hardwareapp.security.service;

import hr.tvz.plese.hardwareapp.security.command.LoginCommand;
import hr.tvz.plese.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
