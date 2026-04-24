package thigk2.HuynhThanhTrinh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import thigk2.HuynhThanhTrinh.entity.SanPham;
import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    // Viết sẵn hàm này để lát làm Việc B lấy sản phẩm theo ID Thể Loại
    List<SanPham> findByTheLoai_Id(Integer idTheLoai);
}