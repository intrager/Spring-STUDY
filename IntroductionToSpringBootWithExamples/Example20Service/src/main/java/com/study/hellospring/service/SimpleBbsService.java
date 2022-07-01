package com.study.hellospring.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.hellospring.dao.ISimpleBbsDAO;
import com.study.hellospring.dto.SimpleBbsDTO;

@Service
public class SimpleBbsService implements ISimpleBbsService {
	
	@Autowired
	ISimpleBbsDAO iSimpleBbsDao;
	
	@Override
	public List<SimpleBbsDTO> list() {
		return iSimpleBbsDao.listDAO();
	}
	
	@Override
	public SimpleBbsDTO view(String id) {
		return iSimpleBbsDao.viewDAO(id);
	}
	
	@Override
	public int write(Map<String, String> map) {
		int nResult = iSimpleBbsDao.writeDAO(map);
		return nResult;
	}
	
	@Override
	public int delete(String id) {
		int nResult = iSimpleBbsDao.deleteDAO(id);
		return nResult;
	}
	
	@Override
	public int count() {
		int nTotalCount = iSimpleBbsDao.articleCount();
		return nTotalCount;
	}
}
