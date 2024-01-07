package springmsa.springmsa_user_service.service;

import springmsa.springmsa_user_service.dto.UserDto;
import springmsa.springmsa_user_service.entity.Users;

public interface UserService {
    Users createUser(UserDto userDto);
}
