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
			System.out.println("mybatis�� �����մϴ�. ���̵��ߺ�üũ");
			List<String> dup = session.selectList("mybatis.mybatisMapper.dupID",registerRequest);
			System.out.println(dup);
			if (dup.isEmpty()) {
				System.out.println("���̵� �ߺ�x");
				return false;
			} else {
				System.out.println("���̵� �ߺ�o");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
			
		} finally {
			session.close();
		}
		
		
		
	}
	
	// Mybatis�� �̿��� ȸ������.
	public Boolean insertMemberByBatis(RegisterRequest registerRequest) {
		SqlSession session = sqlFactory.openSession();
		try {
		System.out.println("mybatis�� �����մϴ�. ȸ������");
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
		System.out.println("[�α���] ����� ���� ������ ���� : " + client);
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
		System.out.println("myPageInfoByBatis ����");
		return session.selectOne("myPageInfo",id);
		
	}

}



//DB�� ȸ������ ���� - old code
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
	 * = id + pwd; return idpw; } // select �� ����� �ݵ�� ����� �־�� mapRow() �޼ҵ带 �����Ѵ�.
	 * �׷��� ������ ����� ������ mapRow() �޼ҵ� �ȿ� String ������ ���� // null ���� ���� ���� �ƴϴ�. // �׷���
	 * ������ List<String> �� ������ isLogin.isEmpty() ���� true �̸� ����ֱ� ������ (mapRow() �޼ҵ尡
	 * ������� �ʱ� ������ // true ���� ������ ���̴�.
	 * },loginRequest.getId(),loginRequest.getPwd());
	 * System.out.println(isLogin.isEmpty()); if (isLogin.isEmpty()) {
	 * System.out.println("�α��� ����"); return false; } else {
	 * System.out.println("�α��� ���� ȯ���մϴ�. "+loginRequest.getId()+"��"); return true; }
	 * }
	 */

//���̵� �ߺ� üũ 
	/*
	 * public Boolean dupCheck(RegisterRequest registerRequest) { List<String> dupId
	 * = jdbcTemplate.query("select client_id from client where client_id = ?", new
	 * RowMapper<String>() {
	 * 
	 * @Override public String mapRow(ResultSet rs, int rowNum) throws SQLException
	 * { return rs.getString("client_id"); } },registerRequest.getId());
	 * 
	 * if (dupId.isEmpty()) { // ������ ���̵� �ִ� ��� return false; } else { return true;
	 * } }
	 */

