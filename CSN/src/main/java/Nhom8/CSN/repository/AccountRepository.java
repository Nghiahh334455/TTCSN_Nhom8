package Nhom8.CSN.repository;

import Nhom8.CSN.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    // Tìm tài khoản theo tên đăng nhập (id)
    Optional<Account> findById(long id);

    // Tìm tài khoản theo vai trò (role)
    Optional<Account> findByRole(String role);
}
