package Nhom8.CSN.repository;

import Nhom8.CSN.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Long> {
    // Lấy lớp theo ID
    Classes findById(long id);

    // Lấy tất cả các lớp của một cố vấn
    List<Classes> findByAdvisorId(Long advisorId);
}
