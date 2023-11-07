package geomex.notice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*import lombok.Getter;
import lombok.Setter;
import lombok.ToString; 사용해볼려다가 추가가 안되서 냅둠*/

public class UserVo {
	
	@NotEmpty(message="아이디를 입력해주세요")
	private String userId;
	@NotEmpty(message="비밀번호를 입력해주세요")
	private String userPassword;
	@NotNull(message="본인 이름을 입력해주세요")
	private String userName;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
