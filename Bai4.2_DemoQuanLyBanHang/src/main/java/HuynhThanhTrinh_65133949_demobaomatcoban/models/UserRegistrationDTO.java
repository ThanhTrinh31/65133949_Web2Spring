package HuynhThanhTrinh_65133949_demobaomatcoban.models;

public class UserRegistrationDTO {
    private String username;
    private String password;
    private String confirmPassword;
//getter, setter, contructor
	public UserRegistrationDTO(String username, String password, String confirmPassword) {
		super();
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	public UserRegistrationDTO() {
		super();
	}
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	    
}
