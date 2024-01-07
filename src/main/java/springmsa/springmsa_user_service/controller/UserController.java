package springmsa.springmsa_user_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springmsa.springmsa_user_service.dto.*;
import springmsa.springmsa_user_service.entity.Users;
import springmsa.springmsa_user_service.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity<ApiResponseDto<ResponseUserDto>> createUser(@RequestBody RequestUserDto requestUserDto) {
        log.info("createUser payload: {}", requestUserDto);

        try {
            Users createdUser = userService.createUser(modelMapper.map(requestUserDto, UserDto.class));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(modelMapper.map(createdUser, ResponseUserDto.class), null));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDto<>(null, e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<ApiResponseDto<List<ResponseUsersDto>>> getUsers() {
        Iterable<Users> users = userService.findAll();

        List<ResponseUsersDto> result = new ArrayList<>();

        users.forEach(user -> {
            ResponseUsersDto userDto = modelMapper.map(user, ResponseUsersDto.class);
            result.add(userDto);
        });

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(result, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ResponseUserDto>> getUser(@PathVariable Long id) {
        UserDto findUser = userService.findUserById(id);

        ResponseUserDto userDto = modelMapper.map(findUser, ResponseUserDto.class);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(userDto, null));
    }
}
