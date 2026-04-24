package thigk2.HuynhThanhTrinh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import thigk2.HuynhThanhTrinh.entity.SanPham;
import thigk2.HuynhThanhTrinh.repository.SanPhamRepository;
import thigk2.HuynhThanhTrinh.repository.TheLoaiRepository;

@Controller
public class ProductController {

	@Autowired
	private SanPhamRepository sanPhamRepo;

	@Autowired
	private TheLoaiRepository theLoaiRepo;

	// Hiển thị danh sách tất cả sản phẩm hoặc lọc theo thể loại
	@GetMapping("/sanpham")
	public String hienThiDanhSach(@RequestParam(name = "idTL", required = false) Integer idTL, Model model) {
		// Lấy danh sách thể loại để hiển thị menu lọc
		model.addAttribute("listTL", theLoaiRepo.findAll());

		if (idTL != null) {
			// Nếu có idTL thì lọc sản phẩm theo loại
			model.addAttribute("listSP", sanPhamRepo.findByTheLoai_Id(idTL));
			model.addAttribute("currentTL", idTL);
		} else {
			// Nếu không thì lấy tất cả sản phẩm
			model.addAttribute("listSP", sanPhamRepo.findAll());
		}
		return "sanpham_list";
	}

	// Hiển thị chi tiết một sản phẩm theo mã
	@GetMapping("/sanpham/chitiet/{id}")
	public String xemChiTiet(@PathVariable("id") Integer id, Model model) {
		SanPham sp = sanPhamRepo.findById(id).orElse(null);
		if (sp == null) {
			// Nếu không tìm thấy, đá văng về trang danh sách luôn
			return "redirect:/sanpham";
		}
		model.addAttribute("sp", sp);
		return "sanpham_detail";
	}
}