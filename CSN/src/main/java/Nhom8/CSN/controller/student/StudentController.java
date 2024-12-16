package Nhom8.CSN.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;
import Nhom8.CSN.entity.Result;
import Nhom8.CSN.entity.Student;
import Nhom8.CSN.repository.ResultRepository;
import Nhom8.CSN.repository.StudentRepository;

@RequestMapping("/student")
@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository, ResultRepository resultRepository) {
        this.studentRepository = studentRepository;
        this.resultRepository = resultRepository;
    }

    @GetMapping("/homepage")
    public String homepage(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId"); // Lấy userId từ session

        // Nếu chưa đăng nhập, chuyển hướng về trang login
        if (userId == null) {
            return "redirect:/Login";
        }

        return "student/homepage";
    }

    @GetMapping("/information")
    public String personalInformation(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId"); // Lấy userId từ session

        // Kiểm tra nếu không có session (người dùng chưa đăng nhập)
        if (userId == null) {
            return "redirect:/Login"; // Nếu chưa đăng nhập, chuyển hướng về trang login
        }

        Student student = studentRepository.findById(userId).orElse(null); 
        if (student != null) {
            model.addAttribute("student", student);
        } else {
            model.addAttribute("error", "Không tìm thấy thông tin sinh viên.");
        }
        return "student/information";
    } 

    @GetMapping("/results")
    public String showResults(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId"); // Lấy userId từ session

        // Kiểm tra nếu không có session (người dùng chưa đăng nhập)
        if (userId == null) {
            return "redirect:/Login"; // Nếu chưa đăng nhập, chuyển hướng về trang login
        }

        // Lấy thông tin sinh viên
        Student student = studentRepository.findById(userId).orElse(null);
        if (student == null) {
            model.addAttribute("error", "Không tìm thấy sinh viên.");
            return "student/results";
        }

        // Lấy danh sách kết quả học tập của sinh viên
        List<Result> results = resultRepository.findByStudentId(userId);
        if (results.isEmpty()) {
            model.addAttribute("error", "Không có kết quả học tập.");
        } else {
            model.addAttribute("results", results); 
            model.addAttribute("student", student);
        }

        return "student/results";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa toàn bộ session
        return "redirect:/Login"; // Chuyển hướng về trang đăng nhập
    }
}
