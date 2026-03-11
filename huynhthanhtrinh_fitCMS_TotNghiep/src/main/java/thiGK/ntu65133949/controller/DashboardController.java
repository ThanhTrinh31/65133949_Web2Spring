package thiGK.ntu65133949.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // Bắt cả 3 trường hợp: gõ "/", gõ "/dashboard" hoặc gõ sai lỗi chính tả "/dasdboard" như trong đề
    @GetMapping({"/", "/dashboard", "/dasdboard"}) 
    public String showDashboard(Model model) {
        
        // 1. Giao dữ liệu cho Header và Footer
        model.addAttribute("userName", "Admin_NTU");
        model.addAttribute("studentName", "Nguyễn Văn A"); // HÃY SỬA THÀNH TÊN CỦA BẠN
        
        // 2. Giao dữ liệu cho phần Content (CV của sinh viên)
        model.addAttribute("cvName", "Nguyễn Văn A");
        model.addAttribute("cvMajor", "Kỹ thuật phần mềm");
        model.addAttribute("cvEmail", "nguyenvana@ntu.edu.vn");
        model.addAttribute("cvSkills", "Java, Spring Boot, Sinh Tồn Deadline");

        // Trả về giao diện có tên là dashboard.html
        return "dashboard"; 
    }
}