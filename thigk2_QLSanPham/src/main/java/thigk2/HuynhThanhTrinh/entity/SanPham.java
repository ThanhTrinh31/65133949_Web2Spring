package thigk2.HuynhThanhTrinh.entity;

import jakarta.persistence.*;

@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String tenSP;
    private Double gia;

    // Cấu hình khóa ngoại (Nhiều Sản phẩm thuộc 1 Thể loại)
    @ManyToOne
    @JoinColumn(name = "id_the_loai")
    private TheLoai theLoai;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public Double getGia() {
		return gia;
	}

	public void setGia(Double gia) {
		this.gia = gia;
	}

	public TheLoai getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(TheLoai theLoai) {
		this.theLoai = theLoai;
	}

    // NHỚ TẠO GETTER/SETTER CHO 4 BIẾN NÀY (Chuột phải -> Source -> Generate...)
    
}