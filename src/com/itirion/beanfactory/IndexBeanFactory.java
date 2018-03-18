package com.itirion.beanfactory;


import java.util.Arrays;
import java.util.Collection;

import com.itirion.bean.IndexBean;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: CustomBeanFactory.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class IndexBeanFactory
{


	/**
	 *
	 */
	private static IndexBean[] data =
		{
			new IndexBean("Local1", "James Peterson")
		};  
			

	/**
	 *
	 */
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
