package com.cblformatter.utils;

import com.cblformatter.model.beans.Model;



public class Pic {
	
	/**
	 * This add spaces to reach the column number declared in Constants
	 * @param field the field before the spaces, needed to count the remaining spaces
	 * @param index the index number, needed to count the remaining spaces
	 * @return the remaining spaces to reach the column number declared in Constants
	 */

	public static String addPicSpaces(String field, String index){
		int lunghezzaCol = Integer.parseInt(Model.getSettingsBean().getPicSpaces())-field.length()-Integer.parseInt(index.trim())-Integer.parseInt(Model.getSettingsBean().getIndexSpaces())-2;
		String spaces = "";
		
		for(int a = 0; a<lunghezzaCol; a++){
			spaces = spaces + " ";
		}
		return spaces;
	}
	
	/**
	 * search and replace the String for not allowed type
	 * <p>
	 * PIC S9(13). to PIC X(14).
	 * @param inputLine the input String to check
	 * @return a String with all the occurence of PIC S9(13). replaced.
	 */
	
	public static String checkPic(String inputLine){
		
		//Controllo PIC
		if(inputLine.contains("S9(13)") || inputLine.contains("S9(013)")){
			inputLine = inputLine.replace("S9(13)", "X(14)");
			inputLine = inputLine.replace("S9(013)", "X(14)");
		}
		if((inputLine.contains("PIC 9") || inputLine.contains("PIC X")) 
				&& (!inputLine.contains("(") && !inputLine.contains(")"))){
			inputLine = inputLine.replace("PIC 9", "PIC 9(01)");
			inputLine = inputLine.replace("PIC X", "PIC X(01)");
		}
		
		return inputLine;
		
	}
	
}
