package mypage;

import java.util.Map;

import db.MemberDAO;

public class MyPageService {
	MemberDAO memberDao;
	
	public MyPageService(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}
	
	public Map<String,Object> bringMyPageInfo(String id) {
		System.out.println("bringMyPageInfo ½ÇÇà");
		return memberDao.myPageInfoByBatis(id);
	}
	
}
