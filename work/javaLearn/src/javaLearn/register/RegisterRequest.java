package javaLearn.register;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

public class RegisterRequest {

	//이 클래스는 사용자 입력 요청이라고 보면 됨.
	//예를 들어서 html에서 input으로 입력한 것들을 form데이터로 전송했다. 라고 할 수 있다.
	private String email;
	private String password;
	private String confirmPassword;
	private String name;

	public RegisterRequest(String email, String password, String confirmPassword, String name) {
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPasswordEqualToConfirmPassword(){
		return password.equals(confirmPassword);
	}

}
