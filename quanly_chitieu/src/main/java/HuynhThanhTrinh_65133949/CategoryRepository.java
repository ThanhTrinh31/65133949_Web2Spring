package HuynhThanhTrinh_65133949;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Lấy danh mục hệ thống (user IS NULL) HOẶC danh mục cá nhân (user.id = :userId)
    @Query("SELECT c FROM Category c WHERE c.user IS NULL OR c.user.id = :userId")
    List<Category> findGlobalAndPersonalCategories(@Param("userId") Long userId);
}