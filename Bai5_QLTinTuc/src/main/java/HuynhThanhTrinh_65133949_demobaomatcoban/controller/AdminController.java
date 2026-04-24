package HuynhThanhTrinh_65133949_demobaomatcoban.controller;

import HuynhThanhTrinh_65133949_demobaomatcoban.model.TinTuc;
import HuynhThanhTrinh_65133949_demobaomatcoban.service.TinTucService;
import HuynhThanhTrinh_65133949_demobaomatcoban.repository.LoaiTinTucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TinTucService tinTucService;
    
    @Autowired
    private LoaiTinTucRepository loaiTinTucRepository;

    // Giao diện quản lý chung
    @GetMapping
    public String trangQuanTri(Model model) {
        model.addAttribute("danhSachTin", tinTucService.layTatCaTinTuc());
        model.addAttribute("danhSachLoai", loaiTinTucRepository.findAll()); // Để đổ vào Thẻ Select
        model.addAttribute("tinTucMoi", new TinTuc()); // Form rỗng để nhập tin mới
        return "admin"; // Trả về file admin.html
    }

    // Xử lý khi Admin bấm nút "Lưu"
    @PostMapping("/luu")
    public String luuTinTuc(@ModelAttribute TinTuc tinTuc) {
        tinTucService.luuTinTuc(tinTuc);
        return "redirect:/admin"; // Lưu xong tải lại trang quản trị
    }

    // Xử lý khi Admin bấm nút "Xóa"
    @GetMapping("/xoa/{id}")
    public String xoaTinTuc(@PathVariable Integer id) {
        tinTucService.xoaTinTuc(id);
        return "redirect:/admin"; // Xóa xong tải lại trang
    }
}