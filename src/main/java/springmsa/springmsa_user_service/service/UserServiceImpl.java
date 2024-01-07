package springmsa.springmsa_user_service.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springmsa.springmsa_user_service.dto.ResponseOrderDto;
import springmsa.springmsa_user_service.dto.UserDto;
import springmsa.springmsa_user_service.entity.Users;
import springmsa.springmsa_user_service.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Override
    public UserDto findUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        UserDto userDto = modelMapper.map(user.get(), UserDto.class);

        List<ResponseOrderDto> orders = new ArrayList<>();
        userDto.setOrders(orders);

        return userDto;
    }

    @Override
    public Iterable<Users> findAll() {
        return userRepository.findAll();
    }
}
