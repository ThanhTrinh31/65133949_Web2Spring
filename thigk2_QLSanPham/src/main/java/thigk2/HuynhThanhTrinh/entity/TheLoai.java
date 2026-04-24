package thigk2.HuynhThanhTrinh.entity;

import jakarta.persistence.*;

@Entity
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenTheLoai() {
		return tenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}

	private String tenTheLoai;

    // BRO BẮT BUỘC PHẢI LÀM THAO TÁC NÀY ĐỂ TẠO GETTER/SETTER:
    // Bôi đen 2 dòng biến ở trên -> Chuột phải -> Source -> Generate Getters and Setters -> Select All -> Generate.
}