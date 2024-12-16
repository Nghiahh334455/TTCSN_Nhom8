package Nhom8.CSN.service;

import Nhom8.CSN.entity.Result;
import Nhom8.CSN.entity.Student;
import Nhom8.CSN.repository.CourseRepository;
import Nhom8.CSN.repository.ResultRepository;
import Nhom8.CSN.repository.StudentRepository;
import Nhom8.CSN.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository; // Repository để truy vấn kết quả
    @Autowired
    private StudentRepository studentRepository; // Repository để truy vấn sinh viên
    @Autowired
    private CourseRepository courseRepository; // Repository để truy vấn học phần

    @Transactional
    public List<Result> getAllResultsForStudent(Long studentId) {
        // Truy vấn kết quả của sinh viên từ bảng Result
        return resultRepository.findByStudentId(studentId);
    }

    @Transactional
    public Result getResultForStudentAndCourse(Long studentId, Long courseId) {
        // Truy vấn kết quả của sinh viên cho một học phần cụ thể
        return resultRepository.findByStudentIdAndCourseId(studentId, courseId);
    }
}
