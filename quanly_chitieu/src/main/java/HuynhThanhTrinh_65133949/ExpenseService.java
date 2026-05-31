package HuynhThanhTrinh_65133949;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    // 1. Tính tổng số dư của người dùng
    public Double getCurrentBalance(Long userId) {
        List<Transaction> transactions = transactionRepo.findByUserId(userId);
        Double balance = 0.0;
        for (Transaction t : transactions) {
            if ("INCOME".equals(t.getCategory().getType())) {
                balance += t.getAmount();
            } else if ("EXPENSE".equals(t.getCategory().getType())) {
                balance -= t.getAmount();
            }
        }
        return balance;
    }

    // 2. Kiểm tra xem hũ tiền (Category) đã tiêu quá hạn mức chưa
    public boolean isCategoryOverBudget(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElse(null);
        if (category == null || category.getBudgetLimit() == 0) return false;

        // Tính tổng tiền đã tiêu trong tháng này của danh mục đó
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        
        List<Transaction> transactions = transactionRepo.findByUserIdAndTransactionDateBetween(
                category.getUser().getId(), startOfMonth, endOfMonth);

        Double totalSpent = 0.0;
        for (Transaction t : transactions) {
            if (t.getCategory().getId().equals(categoryId)) {
                totalSpent += t.getAmount();
            }
        }
        return totalSpent > category.getBudgetLimit();
    }

    // 3. Thêm nhanh một giao dịch mới
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    // 4. Lấy tất cả danh mục để hiển thị lên form chọn (Dropdown)
    public List<Category> getAllCategoriesByUser(Long userId) {
        return categoryRepo.findByUserId(userId);
    }
}