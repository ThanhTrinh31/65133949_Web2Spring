package HuynhThanhTrinh_65133949_demobaomatcoban.service;

import HuynhThanhTrinh_65133949_demobaomatcoban.model.TinTuc;
import HuynhThanhTrinh_65133949_demobaomatcoban.repository.TinTucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TinTucService {

    @Autowired
    private TinTucRepository tinTucRepository;

    // Hàm lấy toàn bộ danh sách tin tức
    public List<TinTuc> layTatCaTinTuc() {
        return tinTucRepository.findAll();
    }
 // Hàm lưu tin tức mới (hoặc cập nhật)
    public void luuTinTuc(TinTuc tinTuc) {
        tinTucRepository.save(tinTuc);
    }

    // Hàm xóa tin tức theo ID
    public void xoaTinTuc(Integer id) {
        tinTucRepository.deleteById(id);
    }
    // Sau này bro có thể viết thêm các hàm Lưu, Xóa, Sửa ở đây...
}
