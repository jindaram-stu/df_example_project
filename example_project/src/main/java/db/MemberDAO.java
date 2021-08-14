package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.cj.protocol.Resultset;

import join.RegisterRequest;
import login.LoginRequest;
import product.ProductRequest;

public class MemberDAO {
	
	@Autowired
	SqlSessionFactory sqlFactory;
	
	public Boolean dupCheckByBatis(RegisterRequest registerRequest ) {
		SqlSession session = sqlFactory.openSession();
		try {
			System.out.println("mybatis를 실행합니다. 아이디중복체크");
			List<String> dup = session.selectList("mybatis.mybatisMapper.dupID",registerRequest);
			System.out.println(dup);
			if (dup.isEmpty()) {
				System.out.println("아이디가 중복x");
				return false;
			} else {
				System.out.println("아이디가 중복o");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
			
		} finally {
			session.close();
		}
		
		
		
	}
	
	// Mybatis를 이용한 회원가입.
	public Boolean insertMemberByBatis(RegisterRequest registerRequest) {
		SqlSession session = sqlFactory.openSession();
		try {
		System.out.println("mybatis를 실행합니다. 회원가입");
		session.insert("mybatis.mybatisMapper.registerMember",registerRequest);
		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		
	}
	
	public Boolean LoginByBatis(LoginRequest loginRequest) {
		SqlSession session = sqlFactory.openSession();
		try {
		Map<String, Object> client = session.selectMap("mybatis.mybatisMapper.Login", loginRequest,"");
		System.out.println("[로그인] 기능을 통한 가져온 정보 : " + client);
		if (client.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		
	}
	
	public Map<String,Object> myPageInfoByBatis(String id) {
		SqlSession session = sqlFactory.openSession();
		System.out.println("myPageInfoByBatis 실행");
		return session.selectOne("myPageInfo",id);
		
	}

}



//DB에 회원정보 삽입 - old code
	/*
	 * public Boolean insertMember(RegisterRequest registerRequest) { try {
	 * KeyHolder keyHolder = new GeneratedKeyHolder(); jdbcTemplate.update(new
	 * PreparedStatementCreator() {
	 * 
	 * @Override public PreparedStatement createPreparedStatement(Connection con)
	 * throws SQLException { PreparedStatement ppst = con.
	 * prepareStatement("insert into client (client_name, client_id, client_pwd, client_email, client_address, client_tel, client_entre, client_kakao) "
	 * + "values (?,?,?,?,?,?,0,0)", new String[] {"client_num"}); ppst.setString(1,
	 * registerRequest.getName()); ppst.setString(2, registerRequest.getId());
	 * ppst.setString(3, registerRequest.getPwd()); ppst.setString(4,
	 * registerRequest.getEmail()); ppst.setString(5, registerRequest.getAddress());
	 * ppst.setString(6, registerRequest.getTel()); return ppst; } },keyHolder);
	 * return true; } catch (Exception e) { return false; } }
	 */

	/*
	 * public Boolean Login(LoginRequest loginRequest) {
	 * System.out.println(loginRequest.getId() + loginRequest.getPwd());
	 * List<String> isLogin = jdbcTemplate.
	 * query("select * from client where client_id = ? and client_pwd = ?", new
	 * RowMapper<String>() {
	 * 
	 * @Override public String mapRow(ResultSet rs, int rowNum) throws SQLException
	 * { String id = rs.getString("client_id"); String pwd =
	 * rs.getString("client_pwd"); System.out.println(id +" 33" + pwd); String idpw
	 * = id + pwd; return idpw; } // select 문 실행시 반드시 결과가 있어야 mapRow() 메소드를 실행한다.
	 * 그렇기 때문에 결과가 없으면 mapRow() 메소드 안에 String 변수들 값에 // null 값이 들어가는 것은 아니다. // 그렇기
	 * 떄문에 List<String> 형 변수인 isLogin.isEmpty() 값이 true 이면 비어있기 떄문에 (mapRow() 메소드가
	 * 실행되지 않기 때문에 // true 값이 나오는 것이다.
	 * },loginRequest.getId(),loginRequest.getPwd());
	 * System.out.println(isLogin.isEmpty()); if (isLogin.isEmpty()) {
	 * System.out.println("로그인 실패"); return false; } else {
	 * System.out.println("로그인 성공 환영합니다. "+loginRequest.getId()+"님"); return true; }
	 * }
	 */

//아이디 중복 체크 
	/*
	 * public Boolean dupCheck(RegisterRequest registerRequest) { List<String> dupId
	 * = jdbcTemplate.query("select client_id from client where client_id = ?", new
	 * RowMapper<String>() {
	 * 
	 * @Override public String mapRow(ResultSet rs, int rowNum) throws SQLException
	 * { return rs.getString("client_id"); } },registerRequest.getId());
	 * 
	 * if (dupId.isEmpty()) { // 동일한 아이디가 있는 경우 return false; } else { return true;
	 * } }
	 */

