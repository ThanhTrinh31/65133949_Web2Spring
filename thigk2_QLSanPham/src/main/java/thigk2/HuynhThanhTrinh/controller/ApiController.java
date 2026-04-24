package thigk2.HuynhThanhTrinh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thigk2.HuynhThanhTrinh.entity.SanPham;
import thigk2.HuynhThanhTrinh.entity.TheLoai;
import thigk2.HuynhThanhTrinh.repository.SanPhamRepository;
import thigk2.HuynhThanhTrinh.repository.TheLoaiRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TheLoaiRepository theLoaiRepo;

    @Autowired
    private SanPhamRepository sanPhamRepo;

    // 1. Lấy danh sách các thể loại sản phẩm
    @GetMapping("/theloai")
    public List<TheLoai> getDanhSachTheLoai() {
        return theLoaiRepo.findAll();
    }

    // 2. Lấy danh sách các sản phẩm theo một thể loại
    @GetMapping("/sanpham/theloai/{idTheLoai}")
    public List<SanPham> getSanPhamTheoTheLoai(@PathVariable Integer idTheLoai) {
        // Gọi hàm findByTheLoai_Id đã định nghĩa ở Việc A
        return sanPhamRepo.findByTheLoai_Id(idTheLoai);
    }
}