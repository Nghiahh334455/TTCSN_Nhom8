package Nhom8.CSN.controller.advisor;

import Nhom8.CSN.entity.Advisor;
import Nhom8.CSN.entity.Student;
import Nhom8.CSN.entity.Classes;
import Nhom8.CSN.entity.Result;
import Nhom8.CSN.repository.AdvisorRepository;
import Nhom8.CSN.repository.ClassesRepository;
import Nhom8.CSN.repository.ResultRepository;
import Nhom8.CSN.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/advisor")
public class AdvisorController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassesRepository classRepository;

    @Autowired
    private AdvisorRepository advisorRepository;

    @Autowired
    private ResultRepository resultRepository;
    @GetMapping("/homepage")
    public String homepage(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId"); // Lấy userId từ session

        // Nếu chưa đăng nhập, chuyển hướng về trang login
        if (userId == null) {
            return "redirect:/Login";
        }

        return "advisor/homepage";
    }

    @GetMapping("/Student")
    public String listStudentsByClass(
            HttpSession session,
            @RequestParam(value = "classId", required = false) Long classId,
            Model model) {
    
        // Lấy userId từ session
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            model.addAttribute("error", "Bạn cần đăng nhập trước.");
            return "redirect:/Login"; // Chuyển hướng đến trang đăng nhập
        }
    
        // Tìm cố vấn dựa vào userId
        Advisor advisor = advisorRepository.findById(userId).orElse(null);
        if (advisor == null) {
            model.addAttribute("error", "Không tìm thấy thông tin cố vấn.");
            return "error"; // Trả về trang lỗi nếu không tìm thấy cố vấn
        }
    
        // Lấy danh sách các lớp do cố vấn phụ trách
        List<Classes> classes = classRepository.findByAdvisorId(advisor.getId());
        model.addAttribute("classes", classes);
    
        // Nếu classId được truyền, lấy danh sách sinh viên của lớp đó
        if (classId != null) {
            Classes selectedClass = classRepository.findById(classId).orElse(null);
            if (selectedClass != null) {
                List<Student> students = studentRepository.findByLop(selectedClass);
    
                // Tính GPA cho mỗi sinh viên từ bảng điểm nhưng không thay đổi dữ liệu trong CSDL
                for (Student student : students) {
                    // Lấy tất cả các kết quả học tập của sinh viên
                    List<Result> results = resultRepository.findByStudentId(student.getId());
    
                    // Tính GPA từ điểm hệ 4
                    double totalPoints = 0.0;
                    int subjectCount = 0;
    
                    for (Result result : results) {
                        if (result.getDiemHe4() != null) {
                            totalPoints += result.getDiemHe4();
                            subjectCount++;
                        }
                    }
    
                    // Tính GPA trung bình nếu có kết quả môn học
                    double gpa = (subjectCount > 0) ? totalPoints / subjectCount : 0.0;
    
                    // Đặt GPA tính toán vào đối tượng sinh viên để hiển thị trên giao diện mà không thay đổi CSDL
                    student.setGPA(gpa); // Cập nhật GPA tạm thời cho sinh viên
                }
    
                model.addAttribute("students", students);
                model.addAttribute("classes", classes);
            } else {
                model.addAttribute("error", "Lớp được chọn không hợp lệ.");
            }
        }
    
        return "advisor/Student"; // Trả về trang hiển thị danh sách sinh viên
    }
    
    

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa toàn bộ session
        return "redirect:/Login"; // Chuyển hướng về trang đăng nhập
    }
}
