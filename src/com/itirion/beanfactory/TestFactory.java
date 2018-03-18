package com.itirion.beanfactory;

import java.util.Arrays;
import java.util.Collection;

import com.itirion.bean.IndexBean;

public class TestFactory {
	private static IndexBean[] data =
		{
			new IndexBean("Local2", 222+""),
			new IndexBean("Local3", 333+""),
			new IndexBean("Local4", 444+""),
			new IndexBean("Local5", 555+""),
			new IndexBean("Local6", 666+"")

		};  
	
	public static Object[] getBeanArray()
	{
		return data;
	}


	/**
	 *
	 */
	public static Collection getBeanCollection()
	{
		return Arrays.asList(data);
	}

}
