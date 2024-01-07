package springmsa.springmsa_user_service.dto;

import lombok.Data;

@Data
public class CreatedUserDto {
    private String userId;
    private String email;
    private String name;
}
