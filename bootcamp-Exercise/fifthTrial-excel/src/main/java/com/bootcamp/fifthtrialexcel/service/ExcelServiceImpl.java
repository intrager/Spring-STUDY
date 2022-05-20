package com.bootcamp.fifthtrialexcel.service;

import com.bootcamp.fifthtrialexcel.domain.Excel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public class ExcelServiceImpl implements ExcelService {

    SqlSession sqlSession;
    public ExcelServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private static String namespace = "com.bootcamp.assignment.mybatis.mapper.ExcelMapper";

    @Override
    public int create(Excel excelDAO) {
        int count = 0;
        count = sqlSession.insert(namespace + ".create", excelDAO);
        return count;
    }

    @Override
    public Excel readById(Excel excelDAO) {
        return sqlSession.selectOne(namespace + ".read", excelDAO.getSeq());
    }

    @Override
    public List<Excel> readAll() {
        return sqlSession.selectOne(namespace + ".readList");
    }

    @Override
    public int update(Excel excelDAO) {
        int count = 0;
        count = sqlSession.insert(namespace + ".update", excelDAO);
        return count;
    }

    @Override
    public int delete(Excel excelDAO) {
        int count = 0;
        count = sqlSession.insert(namespace + ".delete", excelDAO.getSeq());
        return count;
    }
}
