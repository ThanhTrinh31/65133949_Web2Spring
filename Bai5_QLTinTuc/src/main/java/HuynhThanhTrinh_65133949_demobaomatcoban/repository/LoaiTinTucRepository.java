package HuynhThanhTrinh_65133949_demobaomatcoban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import HuynhThanhTrinh_65133949_demobaomatcoban.model.LoaiTinTuc; // Nhớ import đúng model của bro

public interface LoaiTinTucRepository extends JpaRepository<LoaiTinTuc, Integer> {
}