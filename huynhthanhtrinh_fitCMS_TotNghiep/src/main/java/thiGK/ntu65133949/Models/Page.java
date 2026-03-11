package thiGK.ntu65133949.Models;

public class Page {
	private int id;
	private String pageName;
	private String keyword;
	private String content;
	private int parentPageId;
	// Skill 1: Constructor (Hàm khởi tạo - Dùng để nặn bánh từ khuôn)
    public Page(int id, String pageName, String keyword, String content, int parentPageId) {
        this.id = id;
        this.pageName = pageName;
        this.keyword = keyword;
        this.content = content;
        this.parentPageId = parentPageId;
    }

    // Skill 2: Getters & Setters (Bắt buộc phải có để Spring Boot lấy được dữ liệu)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPageName() { return pageName; }
    public void setPageName(String pageName) { this.pageName = pageName; }
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public int getParentPageId() { return parentPageId; }
    public void setParentPageId(int parentPageId) { this.parentPageId = parentPageId; }
}

