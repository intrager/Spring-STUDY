package com.study.ch3;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class DBConnectionTest2Test {
	@Autowired
	DataSource ds;
	
	// insert into user_info (id, pwd, name, email, birth, sns, reg_date) values("", now())
	
	@Test
	public void insertUserTest() throws Exception {
		User user = new User("qwer", "1234", "names", "qwer@study.ac.kr", new Date(), "facebook", new Date());
		deleteAll();
		int rowCnt = insertUser(user);
		
		assertTrue(rowCnt==1);
	}
	
	@Test
	public void selectUserTest() throws Exception {
		deleteAll();
		User user = new User("qwer", "1234", "names", "qwer@study.ac.kr", new Date(), "facebook", new Date());
		int rowCnt = insertUser(user);

		User user2 = selectUser("qwer");
		
		assertTrue(user.getId().equals("qwer"));
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		deleteAll();
		int rowCnt = deleteUser("qwer");
		assertTrue(rowCnt == 0);
		
		User user = new User("qwer", "1234", "names", "qwer@study.ac.kr", new Date(), "facebook", new Date());
		rowCnt = insertUser(user);
		assertTrue(rowCnt == 1);
		
		rowCnt = deleteUser(user.getId());
		assertTrue(rowCnt == 1);
		
		assertTrue(selectUser(user.getId()) == null);
	}
	
	@Test
	public void updateUserTest() throws Exception {
		deleteAll();
		int rowCnt = deleteUser("qwer");
		assertTrue(rowCnt == 0);
		
		User user = new User("qwer", "1234", "names", "qwer@study.ac.kr", new Date(), "facebook", new Date());
		rowCnt = insertUser(user);
		assertTrue(rowCnt == 1);
		
		user = new User("qwer", "1234", "test", "qwer1234@study.ac.kr", new Date(), "google", new Date());
		rowCnt = updateUser(user);
		assertTrue(rowCnt == 1);
		
		assertTrue(selectUser(user.getId()) != null);
	}
	
	@Test
	public void transactionTest() throws Exception {		
		Connection conn = null;
		try {
			deleteAll();
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			String sql = "insert into user_info values (?, ?, ?, ?, ?, ?, now())";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "asdf");
			pstmt.setString(2, "1234");
			pstmt.setString(3, "han");
			pstmt.setString(4, "qwer@study.ac.kr");
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
			pstmt.setString(6, "google");
			
			int rowCnt = pstmt.executeUpdate();
			
			pstmt.setString(1, "asdf");
			rowCnt = pstmt.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}
	
	// 매개변수로 받은 사용자 정보로 user_info 테이블을 update하는 메서드
	public int updateUser(User user) throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "update user_info "
				+ "set pwd=?, name=?, email=?, birth=?, sns=?, reg_date=? "
				+ "where id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getPwd());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getEmail());
		pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(5, user.getSns());
		pstmt.setTimestamp(6, new java.sql.Timestamp(user.getReg_date().getTime()));
		pstmt.setString(7, user.getId());
		
		return pstmt.executeUpdate();
	}
	
	public int deleteUser(String id) throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "delete from user_info where id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
//		int rowCnt = pstmt.executeUpdate();
//		return rowCnt;
		
		return pstmt.executeUpdate();
	}
	
	public User selectUser(String id) throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "select * from user_info where id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();	// SELECT
		
		if(rs.next()) {
			User user = new User();
			user.setId(rs.getString(1));
			user.setPwd(rs.getString(2));
			user.setName(rs.getString(3));
			user.setEmail(rs.getString(4));
			user.setBirth(new Date(rs.getDate(5).getTime()));
			user.setSns(rs.getString(6));
			user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
			
			return user;
		}
		return null;
	}

	// 사용자 정보를 user_info 테이블에 저장하는 메서드
	public int insertUser(User user) throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "insert into user_info values(?, ?, ?, ?, ?, ?, now())";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);	// SQL Injection 공격 대비, sql문 재사용을 통한 성능 향상
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(6, user.getSns());
		
		int rowCnt = pstmt.executeUpdate();	// INSERT, UPDATE, DELETE
		
		return rowCnt;
	}
	
	private void deleteAll() throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "delete from user_info";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}
	
	@Test
	public void springJdbcConnectionTest() throws Exception {
		Connection conn = ds.getConnection();
		
		System.out.println("conn = " + conn);
		assertTrue(conn != null);
	}	
}
