package Nhom8.CSN.repository;

import Nhom8.CSN.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
    // Lấy cố vấn theo ID
    Advisor findById(long id);
}
