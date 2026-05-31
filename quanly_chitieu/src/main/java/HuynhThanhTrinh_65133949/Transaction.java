package HuynhThanhTrinh_65133949;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount; // Số tiền giao dịch

    @Column(length = 255)
    private String note; // Ghi chú (Ví dụ: "Mua cơm tấm", "Lương tháng 5")

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate; // Ngày thực hiện giao dịch (Dùng LocalDate của Java 8+)

    // Nhiều giao dịch thuộc về một Danh mục
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Nhiều giao dịch thuộc về một Người dùng
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // --- Constructor ---
    public Transaction() {}

    public Transaction(Double amount, String note, LocalDate transactionDate, Category category, User user) {
        this.amount = amount;
        this.note = note;
        this.transactionDate = transactionDate;
        this.category = category;
        this.user = user;
    }

    // --- Getter và Setter ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
