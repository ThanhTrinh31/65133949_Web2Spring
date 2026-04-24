package thigk2.HuynhThanhTrinh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thigk2.HuynhThanhTrinh.entity.NguoiDung;
import thigk2.HuynhThanhTrinh.repository.NguoiDungRepository;

@Controller
public class LoginController {

    @Autowired
    private NguoiDungRepository nguoiDungRepo;

    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String hienThiTrangDangNhap() {
        return "login";
    }

    // Xử lý khi người dùng bấm nút Đăng nhập
 // Thêm HttpSession vào tham số của hàm
    @PostMapping("/login")
    public String xuLyDangNhap(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               jakarta.servlet.http.HttpSession session, // Thêm dòng này
                               Model model) {
        
        NguoiDung user = nguoiDungRepo.findById(username).orElse(null);
        
        if (user != null && user.getPassword().equals(password)) {
            // Lưu user vào session để đánh dấu đã đăng nhập
            session.setAttribute("userLogedIn", user); 
            return "redirect:/sanpham";
        } else {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            return "login";
        }
    }

    // Thêm hàm xử lý đăng xuất
    @GetMapping("/logout")
    public String dangXuat(jakarta.servlet.http.HttpSession session) {
        session.invalidate(); // Xóa toàn bộ session
        return "redirect:/login";
    }
}