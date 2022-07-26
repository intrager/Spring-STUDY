package com.study.ch4;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.ch4.domain.PageHandler;

//@RunWith(SpringJUnit4ClassRunner.class)
public class PageHandlerTest {
	@Test
	public void test() {
		PageHandler ph = new PageHandler(250,1);
		ph.print();
		System.out.println("ph = " + ph);
		assertTrue(ph.beginPage == 1);
		assertTrue(ph.endPage == 10);
	}
	
	@Test
	public void test2() {
		PageHandler ph = new PageHandler(250,11);
		ph.print();
		System.out.println("ph = " + ph);
		assertTrue(ph.beginPage == 11);
		assertTrue(ph.endPage == 20);
	}
	
	@Test
	public void test3() {
		PageHandler ph = new PageHandler(255,25);
		ph.print();
		System.out.println("ph = " + ph);
		assertTrue(ph.beginPage == 21);
		assertTrue(ph.endPage == 26);
	}
}
