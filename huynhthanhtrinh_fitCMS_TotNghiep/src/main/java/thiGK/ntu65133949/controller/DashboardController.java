package thiGK.ntu65133949.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

// Import 2 khuôn mẫu của bạn
import thiGK.ntu65133949.Models.Page;
import thiGK.ntu65133949.Models.Post;

@Controller
public class DashboardController {

    // --- 1. BIẾN TOÀN CỤC CHỨA DỮ LIỆU ---
    private List<Page> dsTrang = new ArrayList<>();
    private List<Post> dsPost = new ArrayList<>();

    // --- 2. CONSTRUCTOR NẠP DỮ LIỆU KHI CHẠY SERVER ---
    public DashboardController() {
        // Hard-code cho Page
        dsTrang.add(new Page(1, "Trang chủ", "home", "Nội dung trang chủ", 0));
        dsTrang.add(new Page(2, "Giới thiệu", "about", "Giới thiệu khoa", 0));
        dsTrang.add(new Page(3, "Đào tạo", "education", "Chương trình đào tạo", 0));

        // Hard-code cho Post
        dsPost.add(new Post(1, "Lịch thi học kỳ", "Chi tiết lịch thi", 1, "lichthi.jpg"));
        dsPost.add(new Post(2, "Hội thảo AI", "Khoa tổ chức hội thảo", 2, "hoithao.jpg"));
    }

    // ====================================================
    // --- GIAO DIỆN CHÍNH (DASHBOARD CV)
    // ====================================================
    @GetMapping({"/", "/dashboard", "/dasdboard"}) 
    public String showDashboard(Model model) {
        model.addAttribute("userName", "Admin_NTU");
        model.addAttribute("studentName", "Nguyễn Văn A"); 
        model.addAttribute("cvName", "Nguyễn Văn A");
        model.addAttribute("cvMajor", "Kỹ thuật phần mềm");
        model.addAttribute("cvEmail", "nguyenvana@ntu.edu.vn");
        model.addAttribute("cvSkills", "Java, Spring Boot");
        return "dashboard"; 
    }

    // ====================================================
    // --- KHU VỰC CỦA PAGE (CÂU 3)
    // ====================================================
    
    // 3.a Hiện danh sách Page
    @GetMapping("/page/all")
    public String getTatCaTrang(ModelMap m) {
        m.addAttribute("lstPages", dsTrang); // Gửi danh sách qua View với tên lstPages
        return "allpage"; 
    }

    // 3.b Thêm mới Page (Hiện Form)
    @GetMapping("/page/new")
    public String hienThiFormThemMoi(Model model) {
        model.addAttribute("pageMoi", new Page());
        return "newpage"; 
    }

    // 3.b Thêm mới Page (Xử lý Lưu)
    @PostMapping("/page/new")
    public String luuTrangMoi(@ModelAttribute("pageMoi") Page p) {
        dsTrang.add(p);
        return "redirect:/page/all";
    }

    // 3.c Xem chi tiết Page
    @GetMapping("/page/view/{id}")
    public String xemTrang(@PathVariable("id") int id, ModelMap m) {
        for (Page p : dsTrang) {
            if (p.getId() == id) {
                m.addAttribute("pageDetail", p);
                return "viewpage"; 
            }
        }
        return "redirect:/page/all";
    }

    // 3.d Xóa Page
    @GetMapping("/page/delete/{id}")
    public String xoaTrang(@PathVariable("id") int id) {
        dsTrang.removeIf(p -> p.getId() == id);
        return "redirect:/page/all";
    }

    // ====================================================
    // --- KHU VỰC CỦA POST (CÂU 4)
    // ====================================================

    // 4.a Hiện danh sách Post
    @GetMapping("/post/all")
    public String getTatCaPost(ModelMap m) {
        m.addAttribute("lstPosts", dsPost);
        return "allpost"; 
    }

    // 4.b Thêm mới Post (Hiện form)
    @GetMapping("/post/new")
    public String hienThiFormThemMoiPost(Model model) {
        model.addAttribute("postMoi", new Post()); 
        return "newpost"; 
    }

    // 4.b Thêm mới Post (Xử lý lưu)
    @PostMapping("/post/new")
    public String luuPostMoi(@ModelAttribute("postMoi") Post p) {
        dsPost.add(p); 
        return "redirect:/post/all"; 
    }

    // 4.c Xem chi tiết Post
    @GetMapping("/post/view/{id}")
    public String xemPost(@PathVariable("id") int id, ModelMap m) {
        for (Post p : dsPost) {
            if (p.getId() == id) {
                m.addAttribute("postDetail", p);
                return "viewpost"; 
            }
        }
        return "redirect:/post/all";
    }

    // 4.d Xóa Post
    @GetMapping("/post/delete/{id}")
    public String xoaPost(@PathVariable("id") int id) {
        dsPost.removeIf(p -> p.getId() == id); 
        return "redirect:/post/all";
    }
}