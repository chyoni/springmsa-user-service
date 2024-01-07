package springmsa.springmsa_user_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String userId;

    @Column(nullable = false)
    private String encryptedPwd;
}
