package HuynhThanhTrinh_65133949_demobaomatcoban.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tin_tuc")
public class TinTuc {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "tieu_de", nullable = false)
    private String tieuDe;
    
    @Column(name = "noi_dung", columnDefinition = "TEXT")
    private String noiDung;
    
    // Mối quan hệ N-1: Nhiều Tin Tức thuộc về 1 Loại Tin Tức
    @ManyToOne
    @JoinColumn(name = "id_loai")
    private LoaiTinTuc loaiTinTuc;

    // --- GETTER VÀ SETTER ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }

    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }

    public LoaiTinTuc getLoaiTinTuc() { return loaiTinTuc; }
    public void setLoaiTinTuc(LoaiTinTuc loaiTinTuc) { this.loaiTinTuc = loaiTinTuc; }
}
