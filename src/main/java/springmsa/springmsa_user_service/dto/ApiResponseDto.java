package springmsa.springmsa_user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {
    private T data;
    private String errorMessage;
}
