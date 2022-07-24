package com.study.ch3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {
	@Autowired
	UserDao userDao;
	
	@Test
	public void updateUser() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2022, 5, 24, 9, 0, 0);
		
		userDao.deleteAll();
		User user = new User("qwer1", "1234", "qwer", "1234@study.ac.kr", new Date(cal.getTimeInMillis()), "kakao", new Date());
		int rowCnt = userDao.insertUser(user);
		assertTrue(rowCnt == 1);
	
		user.setPwd("4321");
		user.setEmail("study@study.ac.kr");
		rowCnt = userDao.updateUser(user);
		assertTrue(rowCnt == 1);
		
		User user2 = userDao.selectUser(user.getId());
		
		System.out.println("user = " + user);
		System.out.println("user2 = " + user);
		
		assertEquals(user, user2);
	}
}
