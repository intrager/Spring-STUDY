package com.study.ch4.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.ch4.dao.BoardDao;
import com.study.ch4.domain.BoardDto;

public interface BoardService {
	int getCount() throws Exception;
	int remove(Integer bno, String writer) throws Exception;
	int write(BoardDto boardDto) throws Exception;
	List<BoardDto> getList() throws Exception;
	BoardDto read(Integer bno) throws Exception;
	List<BoardDto> getPage(Map map) throws Exception;
	int modify(BoardDto boardDto) throws Exception;
	
}
