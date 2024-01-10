package springmsa.springmsa_user_service.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springmsa.springmsa_user_service.dto.ResponseOrderDto;
import springmsa.springmsa_user_service.dto.UserDto;
import springmsa.springmsa_user_service.entity.Authority;
import springmsa.springmsa_user_service.entity.Users;
import springmsa.springmsa_user_service.repository.AuthorityRepository;
import springmsa.springmsa_user_service.repository.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with this email: " + email);
        }

        return new User(
                user.getEmail(),
                user.getEncryptedPwd(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String email) {
        List<Authority> auths = authorityRepository.findByEmail(email);
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Authority auth : auths) {
            authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
        }
        return authorities;
    }
}
