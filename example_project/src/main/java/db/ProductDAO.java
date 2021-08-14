package db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.cj.Session;

import product.ProductImageRequest;
import product.ProductRequest;

public class ProductDAO {

	@Autowired
	SqlSessionFactory sqlFactory;
	
	public void insertProductByBatis(ProductRequest productReqeust) {
		SqlSession session = sqlFactory.openSession();
		try {
			session.insert("mybatis.mybatisMapper.registProduct",productReqeust);
			System.out.println("성공적으로 insert 했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류가 발생했습니다.");
			
		} finally {
			session.close();
		}
	}
	
	public int getProductIdByBatis(ProductRequest productRequest) {
		SqlSession session = sqlFactory.openSession();
		try {
			return session.selectOne("getProductId",productRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public void insertProductImageByBatis(ProductImageRequest productImageRequest) {
		SqlSession session = sqlFactory.openSession();
		try {
			session.insert("registProductImage",productImageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
