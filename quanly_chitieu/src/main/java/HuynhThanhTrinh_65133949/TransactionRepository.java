package HuynhThanhTrinh_65133949;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    
    // Tìm các giao dịch của user trong một tháng cụ thể để làm báo cáo/dashboard
    List<Transaction> findByUserIdAndTransactionDateBetween(Long userId, java.time.LocalDate start, java.time.LocalDate end);
}