package join;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import db.MemberDAO;

public class RegisterService {
	
	private MemberDAO memberDao;
	
	public RegisterService (MemberDAO memberDao) {
		this.memberDao = memberDao; 
	}
	
	public void processRegister(RegisterRequest registerRequest, HttpServletResponse response) throws Exception {
		if(!dupId(registerRequest,response)) {
			System.out.println("insertMember() �޼ҵ� ����");
			insertMember(registerRequest);
		} 
		return;
		
	}
	
	public boolean dupId(RegisterRequest registerRequest, HttpServletResponse response) throws Exception { 
		Boolean check = memberDao.dupCheckByBatis(registerRequest);
		if (check) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('���̵� �ߺ��Ǿ����ϴ�.');history.go(-1);</script>");
			out.flush();
		}
		return check;
	}
	
	public boolean insertMember(RegisterRequest registerRequest) {
		try {
			memberDao.insertMemberByBatis(registerRequest);	
			System.out.println("ȸ������ ����");
			return true;
		} catch (Exception e) {
			System.out.println("ȸ������ ����");
			e.printStackTrace();
			return false;
			
		}
			
		
	}
}
