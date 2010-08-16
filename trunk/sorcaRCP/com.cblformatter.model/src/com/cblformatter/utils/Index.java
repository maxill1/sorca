package com.cblformatter.utils;

import com.cblformatter.model.beans.Model;


public class Index {

	/**
	 * Add the number of spaces specified in Constants class
	 * @return the number of spaces specified in Constants class
	 */
	
	public static String addIndexSpaces() {		

		int indexSpaces = Integer.parseInt(Model.getSettingsBean().getIndexSpaces());
		String print ="";
		String spaces = "";
			int totSpaces = indexSpaces;
			for(int x = 0; x < totSpaces; x++){
				spaces = spaces + " ";
				
			}
			print = spaces;
	
		return print;
	}
	
	/**
	 * Add spaces before the String, in relation to the index
	 * @param print the input line String
	 * @return the input String with spaces
	 */

	public static String addIndexSpaces(String print) {		
		String substring;
		int indexSpaces = Integer.parseInt(Model.getSettingsBean().getIndexSpaces());
		String spaces = "";

			substring = print.trim().substring(0,2);
			
			int index = Integer.parseInt(substring);
			int totSpaces = index+indexSpaces-1;
			for(int x = 0; x < totSpaces; x++){
				spaces = spaces + " ";
				
			}
			print = spaces + print;
	
		return print;
	}
	
	
	/**
	 *  Search the String for an index
	 * @param inputLine the input String
	 * @return if valid line and no index, the same input String, if no index found add 01 !!!ERROR!!! to the line 
	 */
	
	
	public static String checkIndexForErrors(String inputLine){
		if(!LineUtils.startWithIndex(inputLine) && ! LineUtils.startWith6Numbers(inputLine) && !inputLine.contains("*")){
			inputLine = "01 !!!ERROR!!!-" + inputLine.trim();
		}
		return inputLine;
		
	}
	
	/**
	 *  Search the String for an index
	 * @param inputLine the input String
	 * @return if valid line and no index, the same input String, if no index found it returns an empty string 
	 */


	public static String checkIndexForErrorsAndClean(String inputLine){
		if(!LineUtils.startWithIndex(inputLine) && ! LineUtils.startWith6Numbers(inputLine) && !inputLine.contains("*")){
			if(Model.getSettingsBean().isHandleErrors() && !LineUtils.isHeader(inputLine)){
				inputLine = LineUtils.addError(inputLine);
			}else{
				inputLine = "";
			}
		}
		return inputLine;
		
	}
	
	/**
	 * Increase the index by 2 and format the index number to a 2 char String 
	 * @param index an int 
	 * @return a String with 2 char increased by 2
	 */
	
	public static String increaseIndex(int index) {

		boolean add2ToIndex = Model.getSettingsBean().isAdd2ToIndex();

		if(add2ToIndex && !Model.getSettingsBean().isHeaderPresente()){
			if(index > 0){
				index = index + 2;
			}

		}
		String indexStr = LineUtils.formatNumber(index);


		return indexStr;
	}
	
	public static String decreaseIndex(int index) {

		boolean add2ToIndex = Model.getSettingsBean().isAdd2ToIndex();

		if(!add2ToIndex && !Model.getSettingsBean().isHeaderPresente()){
			if(index > 0){
				index = index - 2;
			}

		}
		String indexStr = LineUtils.formatNumber(index);


		return indexStr;
	}
	

/**
 * Increase the index by 2 and format the index number to a 2 char String
 * @param print a String 
 * @return the input String with the index replaced (+2)
 */

public static String replaceIndex(String print) {

	//TODO usare replaceIndex (INT) per fare la stessa cosa sulla stringa
	if(print.contains("11 I8")){
		print = print.trim().replaceFirst("11", "13")+"\n";
	}
	
	if(print.contains("10 I8")){
		print = print.trim().replaceFirst("10", "12")+"\n";
	}

	if(print.contains("09 I8")){
		print = print.trim().replaceFirst("09", "11")+"\n";
	}

	if(print.contains("08 I8")){
		print = print.trim().replaceFirst("08", "10")+"\n";
	}
	
	if(print.contains("07 I8")){
		print = print.trim().replaceFirst("07", "09")+"\n";
	}

	if(print.contains("06 I8")){
		print = print.trim().replaceFirst("06", "08")+"\n";
	}
	
	if(print.contains("05 I8")){
		print = print.trim().replaceFirst("05", "07")+"\n";
	}
	
	if(print.contains("04 I8")){
		print = print.trim().replaceFirst("04", "06")+"\n";
	}

	if(print.contains("03 I8")){
		print = print.trim().replaceFirst("03", "05")+"\n";
	}
	
	if(print.contains("02 I8")){
		print = print.trim().replaceFirst("02", "04")+"\n";
	}

	if(print.contains("01 I8")){

			print = print.trim().replaceFirst("01", "03")+"\n";
		
	} 
	return print;
}
}
