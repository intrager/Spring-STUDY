package com.study.hellospring.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Repository는 stereo type의 일종.
 * stereo type은 빈을 등록하고 사용할 때, 개발자가 내부적으로 의미 파악을 쉽게 하기 위해 사용하는 별칭 
 * 여기에서는 이 클래스를 빈으로 등록하는데 데이터베이스와 관련된 처리 용도로 사용하겠다는 추가적인 의미를 부여함
 * 스프링 내부에서는 그냥 빈으로 등록됨
 */
@Repository
public class MyUserDAO {
	/**
	 * 자동주입 애노테이션을 사용. @Autowired 바로 밑 라인의 jdbcTemplate 변수에는 우리가 설정한 데이터베이스의 정보를 바탕으로
	 * 오라클 드라이버를 로드하고 데이터베이스에 접속한 후에 connection pool까지 생성한 정보가 들어오게 됨.
	 * 데이터베이스를 사용하기 위한 코드는 이 두 줄이 다임
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<MyUserDTO> list() {
		String query = "select * from myuser";
		/*
		 * jdbcTemplate 객체변수의 query 메서드를 이용해서, 바로 위 라인에서 만든 퀴리문을 실행함.
		 * SQL 퀴리의 결과를 ResultSet의 Row마다 DTO객체로 받아서 전체를 리스트 데이터로 만듦.
		 */
		List<MyUserDTO> list = jdbcTemplate.query(
				query, new BeanPropertyRowMapper<MyUserDTO>(MyUserDTO.class));
	
		 for(MyUserDTO my : list) {
		 		System.out.println(my); 
		 }
		
		return list;
	}
}