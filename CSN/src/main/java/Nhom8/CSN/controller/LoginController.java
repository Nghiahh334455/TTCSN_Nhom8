package Nhom8.CSN.controller;

import Nhom8.CSN.entity.Account;
import Nhom8.CSN.repository.AccountRepository;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    private final AccountRepository accountRepository;

    public LoginController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "login"; 
    }

    @GetMapping("/Login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/Login")
    public String login(@RequestParam long id, @RequestParam String password, HttpSession session, Model model) {
        Account account = accountRepository.findById(id).orElse(null);

        if (account != null && account.getPassword().equals(password)) {
            session.setAttribute("userId", id); // Lưu userId vào session
            session.setAttribute("role", account.getRole()); // Lưu role vào session

            if (account.getRole().equals("admin")) {
                return "redirect:/admin/homepage"; 
            } else if (account.getRole().equals("student")) {
                return "redirect:/student/homepage"; 
            } else if (account.getRole().equals("advisor")) {
                return "redirect:/advisor/homepage"; 
            }
        }

        model.addAttribute("loginError", "Sai tên đăng nhập hoặc mật khẩu!");
        return "login";
    }

    // @GetMapping("student/logout")
    // public String logout(HttpSession session) {
    //     session.invalidate(); // Xóa toàn bộ session
    //     return "redirect:/Login"; // Chuyển hướng về trang đăng nhập
    // }
}
