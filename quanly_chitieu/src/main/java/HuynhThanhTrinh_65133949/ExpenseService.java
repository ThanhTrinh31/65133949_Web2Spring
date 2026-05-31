package HuynhThanhTrinh_65133949;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


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

 // Sửa lại hàm này để lấy cả danh mục chung và riêng
    public List<Category> getAllCategoriesByUser(Long userId) {
        return categoryRepo.findGlobalAndPersonalCategories(userId);
    }
    public Map<String, Double> getExpenseStats(Long userId) {
        List<Transaction> transactions = transactionRepo.findByUserId(userId);
        Map<String, Double> statsMap = new HashMap<>();

        for (Transaction t : transactions) {
            // Chỉ thống kê các giao dịch thuộc loại CHI TIÊU (EXPENSE)
            if ("EXPENSE".equals(t.getCategory().getType())) {
                String categoryName = t.getCategory().getName();
                Double amount = t.getAmount();

                // Nếu danh mục đã có trong Map thì cộng dồn số tiền, chưa có thì tạo mới
                statsMap.put(categoryName, statsMap.getOrDefault(categoryName, 0.0) + amount);
            }
        }
        return statsMap;
    }
 // 1. Lấy chi tiết 1 giao dịch theo ID (Dùng khi bấm Sửa)
    public Transaction getTransactionById(Long id) {
        return transactionRepo.findById(id).orElse(null);
    }

    // 2. Logic kiểm tra vượt hạn mức (Budget Limit) trong tháng hiện tại
    public boolean isCategoryOverBudget(Long categoryId, Long userId) {
        Category category = categoryRepo.findById(categoryId).orElse(null);
        // Nếu danh mục không tồn tại hoặc không đặt hạn mức (bằng 0) thì bỏ qua cảnh báo
        if (category == null || category.getBudgetLimit() == null || category.getBudgetLimit() == 0) {
            return false;
        }

        // Lấy ngày đầu tháng và ngày cuối tháng hiện tại
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        // Lấy tất cả giao dịch của user trong tháng này
        List<Transaction> transactions = transactionRepo.findByUserIdAndTransactionDateBetween(userId, startOfMonth, endOfMonth);

        // Tính tổng tiền đã tiêu riêng của danh mục này
        Double totalSpent = 0.0;
        for (Transaction t : transactions) {
            if (t.getCategory().getId().equals(categoryId)) {
                totalSpent += t.getAmount();
            }
        }

        // Nếu tổng tiêu vượt quá hạn mức -> Trả về true (Bị vượt)
        return totalSpent > category.getBudgetLimit();
    }
}