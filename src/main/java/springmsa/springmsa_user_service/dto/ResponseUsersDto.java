package springmsa.springmsa_user_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseUsersDto {
    private String userId;
    private String email;
    private String name;
}
