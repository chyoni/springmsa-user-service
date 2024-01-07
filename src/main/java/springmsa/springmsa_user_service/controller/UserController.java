package springmsa.springmsa_user_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLDataException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springmsa.springmsa_user_service.dto.CreatedUserDto;
import springmsa.springmsa_user_service.dto.ResponseDto;
import springmsa.springmsa_user_service.dto.UserDto;
import springmsa.springmsa_user_service.entity.Users;
import springmsa.springmsa_user_service.service.UserService;
import springmsa.springmsa_user_service.vo.CreateUser;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseDto<CreatedUserDto>> createUser(@RequestBody CreateUser createUser) {
        log.info("createUser payload: {}", createUser);

        try {
            Users createdUser = userService.createUser(modelMapper.map(createUser, UserDto.class));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto<>(modelMapper.map(createdUser, CreatedUserDto.class), null));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(null, e.getMessage()));
        }
    }
}
