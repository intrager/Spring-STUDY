package com.study.hellospring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.hellospring.dto.SimpleBbsDTO;

@Repository
public class SimpleBbsDAOImpl implements SimpleBbsDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SimpleBbsDTO> listDAO() {
		System.out.println("listDAO()");
		
		String query = "select * from simple_bbs order by id desc";
		List<SimpleBbsDTO> dtos = jdbcTemplate.query(
				query, new BeanPropertyRowMapper<SimpleBbsDTO>(SimpleBbsDTO.class));
				
		return dtos;
	}

	@Override
	public SimpleBbsDTO viewDAO(String id) {
		System.out.println("viewDAO()");
		
		String query = "select * from simple_bbs where id = " + id;
		SimpleBbsDTO dto = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<SimpleBbsDTO>(SimpleBbsDTO.class));
		
		return dto;
	}

	@Override
	public int writeDAO(String writer, String title, String content) {
		System.out.println("writeDAO()");
		
		String query = 
				"insert into simple_bbs (id, writer, title, content) " + 
				" values (simple_bbs_seq.nextval, ?, ?, ?)";
	
		return jdbcTemplate.update(query, writer, title, content);
	}

	@Override
	public int deleteDAO(String id) {
		System.out.println("deleteDAO()");
		
		String query = "delete from simple_bbs where id = ?";
		return jdbcTemplate.update(query, Integer.parseInt(id));
	}
	
}
