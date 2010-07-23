package com.cblformatter.views.utils;

import com.cblformatter.counters.Counter;
import com.cblformatter.utils.LineUtils;
import com.cblformatter.utils.Pic;


public class searchLine {

	public static String searchIndex(String inputLine){
		
		String index = "";
		
		if(!inputLine.equals("") && !LineUtils.startWith6Numbers(inputLine)){
			
		index = inputLine.trim().substring(0, 2);
		}
		
		return index;
		
	}
	
	public static String searchField(String inputLine){
		String field = "";
		if(!inputLine.equals("")){
	
			field = inputLine;
	
		int fine = 0;
		if(field.contains("PIC")){
			fine = field.indexOf("PIC");	
		}else{ 
			if(field.contains("(")){
				fine=field.indexOf("(");
			}else{
			fine = field.length();
			}
		}
		if(!field.equals("")){
		field = field.substring(3, fine).replace(".", "").trim();	
		}
		if(field.contains(" OCCURS ")){
			fine = field.indexOf("OCCURS");	
			
			field = field.substring(0, fine).replace(".", "").trim();
		}
			
		
		
		
		}
		return field;
		
	}
	
	public static String searchPicType(String inputLine) {
		
		
		String pic = "";
		inputLine = Pic.checkPic(inputLine);
		
		if(inputLine.contains("PIC")){
			int inizio = inputLine.indexOf("PIC");
			int fine = inputLine.length();
			if(inputLine.contains("(")){
				fine = inputLine.indexOf("(");
			}
			if(inputLine.contains("PIC.")){
				fine = inputLine.indexOf(".");
			}	
			pic = inputLine.substring(inizio,fine);
		}
		
		
		return pic;
	}
	
	public static int searchPicValue(String inputLine) {
		int value = 0;
		int virgola = 0;
	
		inputLine = Pic.checkPic(inputLine);
				
		if(inputLine.contains("9(")||inputLine.contains("X(")){
			int inizio = inputLine.indexOf("(")+1;
			int fine = inputLine.indexOf(")");

			String substr = inputLine.substring(inizio, fine).replace(".", "").trim();

			value = Integer.parseInt(substr);
			
			//gestione virgola virtuale
			if(inputLine.contains("V9(")){
				inizio = inputLine.lastIndexOf("V9(")+3;
				fine = inputLine.lastIndexOf(")");
				String substring = inputLine.substring(inizio, fine).replace(".", "").trim();

				virgola = Integer.parseInt(substring);

				value = value + virgola;
				
				}
			
		}else{
			if(inputLine.contains("PIC X.") || inputLine.contains("PIC 9.")){
				value = 1;
			}
		}
		
		return value;
	}
	
	public static int searchOccurs(String inputLine) {
		
		int occurs = 0;		
		
		if(inputLine.contains("OCCURS")){
		
			occurs = Counter.numOccurs(inputLine);
		}
		
		
		return occurs;
	}

	public static int searchDichCount(String inputLine) {
		
		String dichCount = "";
		inputLine = Pic.checkPic(inputLine);
		int dichNum = 0;
		if(inputLine.contains("(") && !inputLine.contains("PIC") && !inputLine.contains("*")){
			
			int inizio = inputLine.indexOf("(")+1;
			int fine = inputLine.indexOf(")");

			
			dichCount = inputLine.substring(inizio,fine).replace(".","");
			

			if(inputLine.toLowerCase().contains("+ filler")){
				int fineFil = dichCount.indexOf("+ filler");
				dichCount = dichCount.substring(0,fineFil).trim();
			}
			
			dichNum = Integer.parseInt(dichCount);
		}
		

		return dichNum;
	}

	
}
