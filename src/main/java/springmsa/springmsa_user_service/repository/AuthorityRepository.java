package springmsa.springmsa_user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springmsa.springmsa_user_service.entity.Authority;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByEmail(String email);
}
