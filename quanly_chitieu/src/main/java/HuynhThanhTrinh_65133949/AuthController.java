package HuynhThanhTrinh_65133949;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ExpenseService expenseService; // Inject thêm service vào để gọi hàm

    // 1. Hiển thị trang Đăng ký
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

 // 2. Hàm POST Đăng ký
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "register";
        }
        
        // 1. Tiến hành băm mật khẩu người dùng gõ từ form bằng BCrypt
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        
        // 2. Thay thế mật khẩu thuần bằng mật khẩu đã được mã hóa
        user.setPassword(hashedPassword);
        
        // 3. Lưu vào Database XAMPP
        userRepo.save(user); 
        return "redirect:/login";
    }

    // 3. Hiển thị trang Đăng nhập
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // 4. Xử lý logic Đăng nhập
    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {
        User user = userRepo.findByUsername(username);
        
        // Sử dụng BCrypt.checkpw để đối chiếu mật khẩu bảo mật
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/";
        }
        
        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
        return "login";
    }

    // 5. Đăng xuất
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa sạch session
        return "redirect:/login";
    }
}