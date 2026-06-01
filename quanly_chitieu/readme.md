# 📊 Ứng Dụng Quản Lý Chi Tiêu Cá Nhân (Personal Expense Tracker)

Một ứng dụng web quản lý tài chính cá nhân toàn diện, lấy cảm hứng từ triết lý của *Actual Budget*. Hệ thống được xây dựng theo mô hình **Server-Side Rendering (SSR)** truyền thống nhưng tối ưu hóa trải nghiệm người dùng trên một màn hình dashboard thông minh, tích hợp báo cáo trực quan và khả năng trích xuất dữ liệu nâng cao.

---

## 🚀 Tính Năng Cốt Lõi (Key Features)

* **Hệ thống Xác thực Người dùng bảo mật (Authentication):** Đăng ký/Đăng nhập an toàn sử dụng `HttpSession` phân quyền theo từng phiên làm việc của User.
* **Mã hóa Mật khẩu một chiều:** Tích hợp thư viện bảo mật **BCrypt Hashing** để băm mật khẩu thành chuỗi 60 ký tự bảo mật trước khi lưu xuống database, chống rò rỉ dữ liệu.
* **Quản lý Danh mục thông minh (Global & Personal Categories):** * *Danh mục Hệ thống (Global):* Các danh mục thu/chi mặc định dùng chung cho mọi tài khoản để tối ưu tài nguyên lưu trữ.
    * *Danh mục Cá nhân (Personal):* Cho phép từng người dùng tự tạo thêm hũ tiền riêng và cấu hình **Hạn mức chi tiêu (`budget_limit`)** cho từng danh mục.
* **Dashboard Thống kê Trực quan:** Tích hợp **Chart.js** vẽ biểu đồ hình chiếc nhẫn (Doughnut Chart) tự động tính toán co giãn tỷ lệ phần trăm các khoản chi tiêu trong tháng.
* **Hệ thống Cảnh báo Vượt hạn mức (Smart Alert):** Quét dữ liệu thời gian thực và tự động nhuộm đỏ giao diện, hiển thị cảnh báo `⚠️ Vượt hạn mức` khi một danh mục chi tiêu vượt quá số tiền hạn mức đã đặt trong tháng.
* **Bộ lọc Lịch sử nâng cao:** Cho phép tìm kiếm, truy vấn lịch sử giao dịch chính xác theo khoảng thời gian tùy chọn (Từ ngày... Đến ngày...) kèm báo cáo tổng Thu/Chi trong kỳ lọc.
* **Xuất Báo cáo Excel chuyên nghiệp (Export to Excel):** Sử dụng thư viện công nghiệp **Apache POI** để tổng hợp dữ liệu đang lọc và xuất ra file Excel định dạng `.xlsx` chuẩn kế toán (tự động phân tách dấu phẩy hàng nghìn, chống lỗi hiển thị số khoa học `E+`).

---

## 🛠️ Công Nghệ Sử Dụng (Tech Stack)

* **Backend:** Java 17/23, Spring Boot, Spring Data JPA.
* **Frontend Template Engine:** Thymeleaf.
* **CSS Framework:** Tailwind CSS v4 (qua CDN nhanh nhẹn).
* **Database:** MySQL (quản lý qua XAMPP phpMyAdmin).
* **Libraries:** Apache POI (xử lý Excel), jBCrypt (Mã hóa mật khẩu), Chart.js (Vẽ biểu đồ).
* **Build Tool:** Maven.

---

## 📁 Cấu Trúc Thư Mục Dự Án (Project Structure)

```text
HuynhThanhTrinh_65133949
 ├── src
 │    ├── main
 │    │    ├── java/HuynhThanhTrinh_65133949
 │    │    │    ├── AuthController.java        # Quản lý đăng ký, đăng nhập, session
 │    │    │    ├── ExpenseController.java     # Quản lý giao dịch, danh mục, xuất Excel
 │    │    │    ├── User.java & UserRepository # Thực thể và tầng truy vấn người dùng
 │    │    │    ├── Category.java & CategoryRepository
 │    │    │    ├── Transaction.java & TransactionRepository
 │    │    │    └── ExpenseService.java        # Tầng xử lý logic nghiệp vụ và tính toán
 │    │    └── resources
 │    │         ├── templates                  # Giao diện Thymeleaf HTML
 │    │         │    ├── login.html
 │    │         │    ├── register.html
 │    │         │    ├── dashboard.html
 │    │         │    └── history.html
 │    │         └── application.properties     # Cấu hình kết nối MySQL và Hibernate
 └── pom.xml                                   # Quản lý thư viện Maven phụ thuộc
💻 Hướng Dẫn Cài Đặt & Khởi Chạy (Installation & Setup)
1. Chuẩn bị môi trường (Prerequisites)
Máy tính đã cài đặt Java JDK 17 hoặc mới hơn.

Cài đặt phần mềm XAMPP để chạy MySQL Server.

2. Cấu hình Cơ sở dữ liệu
Bật XAMPP và Start hai dịch vụ Apache và MySQL.

Truy cập vào localhost/phpmyadmin, tạo một cơ sở dữ liệu mới đặt tên là: quanly_chitieu.

Cấu hình chuỗi kết nối trong file src/main/resources/application.properties trùng khớp với tài khoản MySQL của bạn:

Properties
spring.datasource.url=jdbc:mysql://localhost:3000/quanly_chitieu?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
3. Khởi chạy ứng dụng
Cách 1: Chạy trực tiếp từ file thực thi .jar độc lập
Vào thư mục target/ của dự án, mở cửa sổ PowerShell/CMD và gõ lệnh:

Bash
java -jar .\quanly_chitieu-0.0.1-SNAPSHOT.jar
Cách 2: Chạy thông qua công cụ Maven
Bash
mvn spring-boot:run
Sau khi hệ thống báo khởi động thành công, hãy mở trình duyệt và truy cập: http://localhost:8080 để trải nghiệm ứng dụng.

👤 Thông Tin Tác Giả (Author)
Sinh viên thực hiện: Huỳnh Thanh Trịnh

Mã số sinh viên: 65133949

Học phần: Phát triển ứng dụng Web 2

Giảng viên hướng dẫn: Th.S Mai Cường Thọ - Trường Đại học Nha Trang