package thiGK.ntu65133949.Models;

public class Post {
    private int id;
    private String title;
    private String content;
    private int categoryId;
    private String thumbnailImage; // Field thêm vào để lấy điểm cộng

    // Constructor
    public Post(int id, String title, String content, int categoryId, String thumbnailImage) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.thumbnailImage = thumbnailImage;
    }
    // Khuôn rỗng (Default Constructor) bắt buộc phải có cho Spring Boot
    public Post() {
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getThumbnailImage() { return thumbnailImage; }
    public void setThumbnailImage(String thumbnailImage) { this.thumbnailImage = thumbnailImage; }
}