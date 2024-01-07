package springmsa.springmsa_user_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String encryptedPwd;
    private String userId;
    private LocalDateTime createdAt;
}
