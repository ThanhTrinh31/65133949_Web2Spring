package HuynhThanhTrinh_65133949_demobaomatcoban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import HuynhThanhTrinh_65133949_demobaomatcoban.model.TinTuc;; // Nhớ import đúng model của bro

public interface TinTucRepository extends JpaRepository<TinTuc, Integer> {
}
