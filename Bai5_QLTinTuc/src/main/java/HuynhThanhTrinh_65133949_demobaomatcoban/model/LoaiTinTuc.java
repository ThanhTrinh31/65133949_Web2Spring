package HuynhThanhTrinh_65133949_demobaomatcoban.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loai_tin_tuc")
public class LoaiTinTuc {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "ten_loai", nullable = false)
    private String tenLoai;

    // --- GETTER VÀ SETTER ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getTenLoai() { return tenLoai; }
    public void setTenLoai(String tenLoai) { this.tenLoai = tenLoai; }
}
