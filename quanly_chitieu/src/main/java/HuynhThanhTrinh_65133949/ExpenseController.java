package HuynhThanhTrinh_65133949;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    // ==========================================
    // 1. TRANG DASHBOARD CHÍNH (XEM VÀ THÊM)
    // ==========================================
    @GetMapping("/")
    public String showDashboard(Model model, HttpSession session) {
        // Kiểm tra xem user đã đăng nhập chưa
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login"; // Chưa đăng nhập thì đá về trang login
        }

        Long userId = loggedInUser.getId();

        // Lấy số dư và lịch sử giao dịch của riêng User này
        Double currentBalance = expenseService.getCurrentBalance(userId);
        List<Transaction> transactions = transactionRepo.findByUserId(userId);
        
        // Lấy danh mục (Bao gồm danh mục hệ thống NULL và danh mục riêng cá nhân)
        List<Category> categories = expenseService.getAllCategoriesByUser(userId);

        // LOGIC CẢNH BÁO: Kiểm tra xem các danh mục nào đang bị vượt hạn mức chi tiêu
        Map<Long, Boolean> budgetAlertMap = new HashMap<>();
        for (Category cat : categories) {
            boolean isOver = expenseService.isCategoryOverBudget(cat.getId(), userId);
            budgetAlertMap.put(cat.getId(), isOver);
        }
        model.addAttribute("budgetAlertMap", budgetAlertMap);

        // LOGIC BIỂU ĐỒ: Lấy dữ liệu thống kê gom nhóm đổ vào Chart.js
        Map<String, Double> statsMap = expenseService.getExpenseStats(userId);
        List<String> chartLabels = new ArrayList<>(statsMap.keySet());
        List<Double> chartData = new ArrayList<>(statsMap.values());
        model.addAttribute("chartLabels", chartLabels);
        model.addAttribute("chartData", chartData);

        // Đẩy toàn bộ dữ liệu nền sang Thymeleaf
        model.addAttribute("user", loggedInUser);
        model.addAttribute("balance", currentBalance);
        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", categories);
        
        // Nếu không ở trạng thái Sửa (không có sẵn object), tạo mới một đối tượng rỗng để Thêm mới
        if (!model.containsAttribute("newTransaction")) {
            model.addAttribute("newTransaction", new Transaction());
        }

        return "dashboard"; // Trả về file templates/dashboard.html
    }

    // ==========================================
    // 2. XỬ LÝ LƯU GIAO DỊCH (THÊM HOẶC CẬP NHẬT)
    // ==========================================
    @PostMapping("/transaction/add")
    public String addTransaction(@ModelAttribute("newTransaction") Transaction transaction, 
                                 @RequestParam("categoryId") Long categoryId,
                                 HttpSession session) {
        
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        // Gắn thông tin User thực tế đang đăng nhập vào giao dịch
        transaction.setUser(loggedInUser);

        // Gắn danh mục đã chọn từ dropdown vào giao dịch
        Category category = new Category();
        category.setId(categoryId);
        transaction.setCategory(category);

        // Đặt ngày mặc định là hôm nay nếu không có sẵn ngày
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(LocalDate.now());
        }

        // Hibernate tự nhận diện: Nếu object có chứa ID cũ -> Tự động chạy lệnh UPDATE
        // Nếu object không chứa ID -> Tự động chạy lệnh INSERT mới
        expenseService.saveTransaction(transaction);
        
        return "redirect:/"; // Lưu xong quay về trang chủ cập nhật số liệu
    }

    // ==========================================
    // 3. ĐIỀU HƯỚNG SỬA GIAO DỊCH
    // ==========================================
    @GetMapping("/transaction/edit/{id}")
    public String editTransaction(@PathVariable("id") Long id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";

        Transaction transaction = expenseService.getTransactionById(id);
        
        // Bảo mật: Chỉ cho phép sửa nếu giao dịch đó thuộc về đúng người đang đăng nhập
        if (transaction != null && transaction.getUser().getId().equals(loggedInUser.getId())) {
            model.addAttribute("newTransaction", transaction);
            model.addAttribute("isEditing", true); // Đánh dấu giao diện chuyển sang chế độ sửa
            
            // Tải lại toàn bộ dữ liệu trang chủ kèm form chứa dữ liệu cũ
            return showDashboard(model, session);
        }
        return "redirect:/";
    }

    // ==========================================
    // 4. XỬ LÝ XÓA GIAO DỊCH
    // ==========================================
    @GetMapping("/transaction/delete/{id}")
    public String deleteTransaction(@PathVariable("id") Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";

        Transaction transaction = expenseService.getTransactionById(id);
        
        // Bảo mật: Chỉ cho phép xóa nếu giao dịch thuộc về đúng người đang đăng nhập
        if (transaction != null && transaction.getUser().getId().equals(loggedInUser.getId())) {
            transactionRepo.delete(transaction);
        }
        return "redirect:/";
    }

    // ==========================================
    // 5. HIỂN THỊ TRANG QUẢN LÝ DANH MỤC
    // ==========================================
    @GetMapping("/categories")
    public String showCategoriesPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";

        List<Category> categories = expenseService.getAllCategoriesByUser(loggedInUser.getId());
        
        model.addAttribute("user", loggedInUser);
        model.addAttribute("categories", categories);
        model.addAttribute("newCategory", new Category());
        return "categories"; // Trả về file templates/categories.html
    }

    // ==========================================
    // 6. XỬ LÝ THÊM DANH MỤC CÁ NHÂN MỚI
    // ==========================================
    @PostMapping("/category/add")
    public String addPersonalCategory(@ModelAttribute("newCategory") Category category, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";

        // Gán user vào danh mục để phân biệt với danh mục Global (NULL) của hệ thống
        category.setUser(loggedInUser);
        
        if (category.getBudgetLimit() == null) {
            category.setBudgetLimit(0.0);
        }

        categoryRepo.save(category);
        return "redirect:/categories"; // Tạo xong tải lại trang danh mục
    }
 // --- THÊM MỚI: Trang Lịch Sử & Bộ Lọc Khoảng Ngày ---
    @GetMapping("/history")
    public String showHistoryPage(@RequestParam(value = "startDate", required = false) String startStr,
                                  @RequestParam(value = "endDate", required = false) String endStr,
                                  Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/login";

        LocalDate startDate;
        LocalDate endDate;

        // Nếu người dùng chủ động chọn ngày từ giao diện Form Lọc
        if (startStr != null && !startStr.isEmpty() && endStr != null && !endStr.isEmpty()) {
            startDate = LocalDate.parse(startStr);
            endDate = LocalDate.parse(endStr);
        } else {
            // Mặc định: Lấy từ ngày 1 của tháng hiện tại đến ngày hôm nay
            startDate = LocalDate.now().withDayOfMonth(1);
            endDate = LocalDate.now();
        }

        // Gọi Repository để lọc dữ liệu chính xác trong khoảng ngày của riêng User này
        List<Transaction> filteredTransactions = transactionRepo.findByUserIdAndTransactionDateBetween(
                loggedInUser.getId(), startDate, endDate);

        // Tính tổng thu và tổng chi trong khoảng thời gian được lọc để làm báo cáo nhanh
        Double totalIncome = 0.0;
        Double totalExpense = 0.0;
        for (Transaction t : filteredTransactions) {
            if ("INCOME".equals(t.getCategory().getType())) {
                totalIncome += t.getAmount();
            } else if ("EXPENSE".equals(t.getCategory().getType())) {
                totalExpense += t.getAmount();
            }
        }

        // Đẩy dữ liệu sang Thymeleaf
        model.addAttribute("user", loggedInUser);
        model.addAttribute("transactions", filteredTransactions);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);

        return "history"; // Sẽ tạo file history.html ở Bước 3
    }
}