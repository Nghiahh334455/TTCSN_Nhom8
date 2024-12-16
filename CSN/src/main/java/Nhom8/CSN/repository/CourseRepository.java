package Nhom8.CSN.repository;

import Nhom8.CSN.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional; // Import Optional

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Phương thức tìm kiếm học phần theo ID, trả về Optional<Course>
    Optional<Course> findById(Long id); 
    
    // Phương thức tìm kiếm tất cả các học phần của sinh viên 
    List<Course> findByResultStudentId(Long studentId);
}
