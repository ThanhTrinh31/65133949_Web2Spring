package thiGK.ntu65133949.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap; // Thêm import này
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList; // Thêm import này
import java.util.List;      // Thêm import này
import thiGK.ntu65133949.Models.Page; // Đảm bảo đúng package chứa file Page của bạn

@Controller
public class DashboardController {

    // --- CỔNG DỊCH CHUYỂN 1: GIAO DIỆN CV ---
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

        return "dashboard"; 
    } // <--- CHÚ Ý: ĐÓNG NGOẶC HÀM 1 TẠI ĐÂY LÀ ĐÚNG

    
    // --- CỔNG DỊCH CHUYỂN 2: HIỂN THỊ DANH SÁCH PAGE ---
    @GetMapping("/page/all")
    public String getTatCaTrang(ModelMap m) {
        
        // 1. Dời đoạn Hard-code danh sách Page xuống đây
        List<Page> dsTrang = new ArrayList<>();
        dsTrang.add(new Page(1, "Trang chủ", "home, index", "Nội dung trang chủ", 0));
        dsTrang.add(new Page(2, "Giới thiệu", "about", "Giới thiệu khoa CNTT", 0));
        dsTrang.add(new Page(3, "Đào tạo", "education", "Chương trình đào tạo", 0));

        // 2. Giao cho anh Shipper mang ra View
        m.addAttribute("lstPages", dsTrang);
        
        // 3. Trả về tên file HTML
        return "allpage"; 
    } // <--- ĐÓNG NGOẶC HÀM 2
}