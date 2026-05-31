package HuynhThanhTrinh_65133949;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private TransactionRepository transactionRepo;

    // Giả lập ID người dùng đang đăng nhập là 1
    private final Long MOCK_USER_ID = 1L;

    @GetMapping("/")
    public String showDashboard(Model model) {
        // 1. Lấy số dư hiện tại
        Double currentBalance = expenseService.getCurrentBalance(MOCK_USER_ID);
        
        // 2. Lấy danh sách giao dịch
        List<Transaction> transactions = transactionRepo.findByUserId(MOCK_USER_ID);
        
        // 3. Lấy danh sách danh mục để đổ vào thẻ <select> trong Form
        List<Category> categories = expenseService.getAllCategoriesByUser(MOCK_USER_ID);

        // Đẩy dữ liệu sang file HTML Thymeleaf
        model.addAttribute("balance", currentBalance);
        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", categories);
        
        // Tạo một đối tượng rỗng để map với Form thêm mới
        model.addAttribute("newTransaction", new Transaction());

        return "dashboard"; // Sẽ tìm file dashboard.html trong thư mục templates
    }

    @PostMapping("/transaction/add")
    public String addTransaction(@ModelAttribute("newTransaction") Transaction transaction, 
                                 @RequestParam("categoryId") Long categoryId) {
        
        // Giả lập gán User và Category vào đối tượng transaction trước khi lưu
        User mockUser = new User();
        mockUser.setId(MOCK_USER_ID);
        transaction.setUser(mockUser);

        Category category = new Category();
        category.setId(categoryId);
        transaction.setCategory(category);

        // Đặt ngày giao dịch mặc định là hôm nay nếu người dùng không chọn
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(LocalDate.now());
        }

        expenseService.saveTransaction(transaction);
        
        return "redirect:/"; // Lưu xong quay về trang chủ cập nhật số liệu
    }
}