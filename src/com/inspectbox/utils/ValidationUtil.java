package com.inspectbox.utils;

public class ValidationUtil {

	
	
	
	public static boolean isDouble(String strNumber){
		boolean isNumber=true;
		try{
			Double db=Double.valueOf(strNumber);
		}catch (Exception e) {
			isNumber=false;
		}		
		return isNumber;
	}
	
	public static boolean isInteger(String strNumber){
		boolean isNumber=true;
		try{
			Integer db=Integer.valueOf(strNumber);
		}catch (Exception e) {
			isNumber=false;
		}
		return isNumber;
	}
}
