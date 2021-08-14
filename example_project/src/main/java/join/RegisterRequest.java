package join;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegisterRequest {
	private String name;
	private String id;
	private String pwd;
	private String email;
	private String address;
	private String tel;
	private String entre;
	private String kakao;
}
