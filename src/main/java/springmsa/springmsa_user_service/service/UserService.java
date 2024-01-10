package springmsa.springmsa_user_service.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springmsa.springmsa_user_service.dto.UserDto;
import springmsa.springmsa_user_service.entity.Users;

public interface UserService extends UserDetailsService {
    Users createUser(UserDto userDto);

    UserDto findUserById(Long id);

    Iterable<Users> findAll();
}
