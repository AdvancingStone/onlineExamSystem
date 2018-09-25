package com.bluehonour.test;

import org.junit.Test;

import com.bluehonour.view.ViewUtils;

public class ViewUtilsTest {

	@Test
	public void studentMenuViewTest() {
		int i = ViewUtils.studentMenuView();
		System.out.println(i);
	}
}
