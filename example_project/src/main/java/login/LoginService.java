package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import db.MemberDAO;

public class LoginService {
	
	private MemberDAO memberDao;
	
	public LoginService (MemberDAO memberDao) {
		this.memberDao = memberDao;
	}
	
	public Boolean processLogin(LoginRequest loginRequest,HttpServletResponse response) throws IOException {
		Boolean whetherLogin = memberDao.LoginByBatis(loginRequest);
		if (!whetherLogin) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('���̵� �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�.');history.go(-1);</script>");
			out.flush();
			return true;
		} else {
			return false;
		}	
	}
}
