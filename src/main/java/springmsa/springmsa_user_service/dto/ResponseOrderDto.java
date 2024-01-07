package springmsa.springmsa_user_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseOrderDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDateTime createdAt;
    private String orderId;
}
