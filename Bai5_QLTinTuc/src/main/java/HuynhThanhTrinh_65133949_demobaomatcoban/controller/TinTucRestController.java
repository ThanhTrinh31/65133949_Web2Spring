package HuynhThanhTrinh_65133949_demobaomatcoban.controller;

import HuynhThanhTrinh_65133949_demobaomatcoban.model.TinTuc;
import HuynhThanhTrinh_65133949_demobaomatcoban.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tintuc") // Đường dẫn để gọi API này
public class TinTucRestController {

    @Autowired
    private TinTucService tinTucService;

    @GetMapping
    public List<TinTuc> danhSachTinTuc() {
        // Gọi Service để lấy dữ liệu và trả thẳng về trình duyệt
        return tinTucService.layTatCaTinTuc();
    }
}
