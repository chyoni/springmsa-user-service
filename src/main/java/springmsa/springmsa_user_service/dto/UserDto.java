package springmsa.springmsa_user_service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String encryptedPwd;
    private String userId;
    private LocalDateTime createdAt;

    private List<ResponseOrderDto> orders;
}
