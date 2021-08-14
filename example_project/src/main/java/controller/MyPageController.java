package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import login.LoginRequest;
import mypage.MyPageService;

@Controller
public class MyPageController {
	MyPageService myPageService;
	HttpSession session;
	
	public MyPageController(MyPageService myPageService) {
		this.myPageService = myPageService;
	}
	
	@GetMapping("/myPage")
	public String moveToMyPage(HttpServletRequest request) {
		// �̹� ������� ���ǿ� ���� ��
		session = request.getSession(false);
		String user = ((LoginRequest)session.getAttribute("MemberInfo")).getId();
		// ������� ���� ������ �������� �޼ҵ� ����
		Map<String,Object> userInfo = myPageService.bringMyPageInfo(user);
		session.setAttribute("userInfo", userInfo);
		return "mypage/mypage";
	}
	
	@RequestMapping("/productReg")
	public String moveToProductReg() {
		return "product/productRegist";
	}

}
