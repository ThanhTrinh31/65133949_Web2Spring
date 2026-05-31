package HuynhThanhTrinh_65133949;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String type; // "INCOME" hoặc "EXPENSE"

    // Cách xử lý lỗi: Gán trực tiếp = 0.0 và định nghĩa cột trong database mặc định là 0
    @Column(name = "budget_limit", columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double budgetLimit = 0.0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    // --- Constructor ---
    public Category() {}

    public Category(String name, String type, Double budgetLimit, User user) {
        this.name = name;
        this.type = type;
        this.budgetLimit = budgetLimit;
        this.user = user;
    }

    // --- Getter và Setter ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getBudgetLimit() { return budgetLimit; }
    public void setBudgetLimit(Double budgetLimit) { this.budgetLimit = budgetLimit; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
}