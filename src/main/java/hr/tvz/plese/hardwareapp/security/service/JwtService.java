package hr.tvz.plese.hardwareapp.security.service;

import hr.tvz.plese.hardwareapp.security.domain.Users;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(Users users);

}
