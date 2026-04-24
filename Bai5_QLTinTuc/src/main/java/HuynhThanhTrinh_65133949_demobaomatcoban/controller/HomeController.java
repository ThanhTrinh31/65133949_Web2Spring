package HuynhThanhTrinh_65133949_demobaomatcoban.controller;

import HuynhThanhTrinh_65133949_demobaomatcoban.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private TinTucService tinTucService;

    // Khi người dùng gõ http://localhost:8080/
    @GetMapping("/")
    public String trangChu(Model model) {
        // Gắn danh sách tin tức vào biến "danhSachTin" để gửi sang HTML
        model.addAttribute("danhSachTin", tinTucService.layTatCaTinTuc());
        return "index"; // Trả về file index.html
    }
}