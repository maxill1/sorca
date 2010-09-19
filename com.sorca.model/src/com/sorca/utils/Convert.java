package com.sorca.utils;

import com.sorca.model.beans.Model;

public class Convert {
	
/**
 * convert from a int to a String
 * @param value an int value
 * @return a String
 */

	public static String covertToPrint(int value){
		
		String val = "";
		if (value == 0){
			val = "";
		}else if(value > 0){
			val = value +"";
		}
		
		return val;
	}
	
	/**
	 * Add parenthesis to the value or if no value (00) return ""
	 * @param value the input value
	 * @return a String with "(value)" or ""
	 */
	
	public static String covertPicValueToPrint(String value){
		
		if(!value.equals("00")){
			value = "("+value +")";
		}else{
			value ="";
		}

		
		return value;
	}
	
	/**
	 * convert a Pic value from int to a String and add parenthesis
	 * @param value the input value
	 * @return  a String with  with "(value)" or "" if 0
	 */
	
	public static String covertPicValueToPrint(int value){
		String val = "";
		if (value == 0){
			val = "";
		}else if(value > 0){
			val = "("+value +")";
		}
		
		
		return val;
	}

	
	public static String covertFloatValueToPrint(String value) {

		
		if(!value.equals("00")){
			value = "V9("+value +")";
		}else{
			value ="";
		}

		
		return value;
	
	}
	
	public static String decodeEOL(){
		String eol = Model.getSettingsBean().getEOL();
		if(eol.equals("UNIX")){
			eol = "\n";
		}else if(eol.equals("DOS")){
			eol = "\r\n";
		}
		return eol;
	}
	
}
