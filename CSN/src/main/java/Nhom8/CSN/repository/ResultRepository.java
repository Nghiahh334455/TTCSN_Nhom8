package Nhom8.CSN.repository;

import Nhom8.CSN.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    // Tìm tất cả kết quả học tập của sinh viên dựa trên ID sinh viên
    List<Result> findByStudentId(Long studentId);

    // Tìm kết quả học tập của sinh viên cho một môn học cụ thể
    Result findByStudentIdAndCourseId(Long studentId, Long courseId);
}
