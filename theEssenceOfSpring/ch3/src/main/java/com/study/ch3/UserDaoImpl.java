package com.study.ch3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Component - @Controller, @Repository, @Service, @ControllerAdvice
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	DataSource ds;
	
	final int FAIL = 0;	// 의미를 분명하게 하기 위함
	
	@Override
	public int deleteUser(String id) {
		int rowCnt = FAIL;	// INSERT, DELETE, UPDATE
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from user_info where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
//			int rowCnt = pstmt.executeUpdate();
//			return rowCnt;
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return FAIL;
		} finally {
			// close()를 호출하다가 예외가 발생할 수 있으므로, try-catch로 감싸야 함
//			try { if(pstmt != null) pstmt.close(); } catch (SQLException e) {e.printStackTrace();}
//			try { if(conn != null) conn.close(); } catch (SQLException e) {e.printStackTrace();}
			close(pstmt, conn);
		}
	}
	
	@Override
	public User selectUser(String id) {
		User user = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String sql = "select * from user_info where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);	// SQL Injection 공격 대비, sql문 재사용을 통한 성능 향상
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirth(new Date(rs.getDate(5).getTime()));
				user.setSns(rs.getString(6));
				user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
			}
		} catch (SQLException e) {
			return null;
		} finally {
			// close()를 호출하다가 예외가 발생할 수 있으므로, try-catch로 감싸야 함.
			// close()의 호출순서는 생성된 순서의 역순
//			try { if(rs != null) rs.close(); } catch (SQLException e) {e.printStackTrace();}
//			try { if(pstmt != null) pstmt.close(); } catch (SQLException e) {e.printStackTrace();}
//			try { if(conn != null) conn.close(); } catch (SQLException e) {e.printStackTrace();}
			close(rs, pstmt, conn);
		}
		return user;
	}
	
	// 사용자 정보를 user_info 테이블에 저장하는 메서드
	@Override
	public int insertUser(User user) {
		int rowCnt = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into user_info values (?, ?, ?, ?, ? , ?, now())";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(6, user.getSns());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return FAIL;
		} finally {
			close(pstmt, conn);
		}
	}

	// 매개변수로 받은 사용자 정보로 user_info 테이블을 update하는 메서드
	@Override
	public int updateUser(User user) {
		int rowCnt = FAIL;	// INSERT, DELETE, UPDATE
		
		String sql = "update user_info " +
					 "set pwd = ?, name = ?, email = ?, birth = ?, sns = ?, reg_date = ? " +
					 "where id = ?";
		
		// try-with-resources - since jdk7
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);	// SQL Injection 공격 대비, sql 재사용을 통한 성능 향상
		) {
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(5, user.getSns());
			pstmt.setTimestamp(6, new java.sql.Timestamp(user.getReg_date().getTime()));
			pstmt.setString(7, user.getId());
			
			rowCnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return FAIL;
		}
		return rowCnt;
	}
	
	public void deleteAll() throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "delete from user_info";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);	// SQL Injection 공격 대비, sql 재사용을 통한 성능 향상
		pstmt.executeUpdate();	// INSERT, DELETE, UPDATE
	}
	
	private void close(AutoCloseable... acs) {
		for(AutoCloseable ac : acs)
			try { if (ac != null) ac.close(); } catch (Exception e) {e.printStackTrace();}
	}
}
