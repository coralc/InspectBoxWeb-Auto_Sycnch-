package com.itirion.beanfactory;

import java.util.Arrays;
import java.util.Collection;

import com.itirion.bean.SummarieBean;

public class IndexFactory {

	private static SummarieBean[] data=null;
	
	public static SummarieBean[] getData() {
		return data;
	}


	public static void setData(SummarieBean[] mydata) {
		IndexFactory.data = mydata;
	}


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
