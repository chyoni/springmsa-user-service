package springmsa.springmsa_user_service.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springmsa.springmsa_user_service.dto.UserDto;
import springmsa.springmsa_user_service.entity.Users;
import springmsa.springmsa_user_service.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString().substring(0, 8));

        Users users = modelMapper.map(userDto, Users.class);
        users.setEncryptedPwd(bCryptPasswordEncoder.encode(userDto.getPwd()));

        userRepository.save(users);

        return users;
    }
}
