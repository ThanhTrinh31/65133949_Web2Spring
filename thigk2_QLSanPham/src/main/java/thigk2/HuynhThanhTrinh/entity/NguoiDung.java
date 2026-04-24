package thigk2.HuynhThanhTrinh.entity;

import jakarta.persistence.*;

@Entity
public class NguoiDung {
    @Id
    private String username; // Dùng username làm ID cho nhanh
    
    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    // NHỚ TẠO GETTER/SETTER
}