package com.cblformatter.utils;




import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.cblformatter.model.beans.LinePropertyBean;
import com.cblformatter.model.beans.Model;

public class LineUtils {
	

	
	/**
	 * clean the line <p>
	 * if the line contains * (cobol comment) this method set the string to "" and the line is ignored
	 * <p>
	 * it removes the following String
	 * <p>
	 * SIGN IS LEADING SEPARATE
	 * <p>
	 * if the lines contains error this method adds 01 Error using checkIndexForErrors()
	 *  
	 * @param inputLine the line to clean
	 * @return a String
	 */
	
	
	
	public static String cleanLine(String inputLine){
		try{
		
			inputLine = inputLine.replace("\t", "");
			inputLine = inputLine.replace("\r", "");
			inputLine = inputLine.replace("\n", "");

		if(inputLine.contains("*") || inputLine.contains("SIGN IS LEADING SEPARATE")){
			inputLine = "";
		}
		
//		if(hasCharsAfterDot(inputLine)){
//			inputLine = addError(inputLine);
//			return inputLine;
//		}
		
		if(!startWithIndex(inputLine) && !startWith6Numbers(inputLine)&&!inputLine.trim().equals("")){
			inputLine = Index.checkIndexForErrorsAndClean(inputLine);
			
			if(inputLine.contains("FILLER")){
				inputLine = "01 "+inputLine;
			}

		}
		
		if(isAValidLine(inputLine)){							


			if(startWith6Numbers(inputLine)){
				inputLine = inputLine.substring(6);
			}
		}
						
			inputLine = inputLine.replace(".", "").trim();
		
			

		} catch (Exception e) {
			MessageDialog.openError(new Shell(),"Errore", "Si è verificato un errore \n"+
			"Controlla la riga: \n"
			+ inputLine);
			e.printStackTrace();
		}
		
		return inputLine;
		
	}
	
	private static boolean isAValidLine(String inputLine) {
		boolean valid = !inputLine.equals("");
//		valid = (inputLine.contains(Costants.getProgramcall())) ;
		valid = !LineUtils.isHeader(inputLine);
	
		return valid;
	}

	public static String addError(String inputLine) {
		return inputLine = "01 !!!!!!!ERROR!!!!!!!!" + inputLine;
	}

	public static boolean hasCharsAfterDot(String inputLine) {
		
		if(inputLine.trim().equals("") || !inputLine.trim().contains(".")){
			return false;
		}

		String charsAfterDot = inputLine.substring(inputLine.indexOf(".")).trim();
		
		if( charsAfterDot.length() > 1){
			return true;	
		}
		
		return false;
		
	}

	/**
	 * check if the line start with an index like 01, 02, 03 ...
	 * @param inputLine the line to check
	 * @return a boolean
	 */
	
	public static boolean startWithIndex(String inputLine){
		boolean start = false;
		
		for(int x = 0; x<20 ; x++){
			
			if(inputLine.trim().startsWith(formatNumber(x)+" ")){
				start = true;
			}
			
		}
				
		return start;
	}
	
	/**
	 * Check a line to see if it start with six numbers like 000100 (the cobol line)
	 * 
	 * @param inputLine the line to check
	 * @return a boolean
	 */
	
	public static boolean startWith6Numbers(String inputLine){
		boolean start = false;
		if(inputLine.trim().indexOf(" ") == 6){
			start = true;
		}
		if(inputLine.contains("*")){
			start = false;	
		}
		return start;
	}
	

	/**
	 * check if this line is part of an header
	 * @param print a String line
	 * @return
	 */
	
	
	public static boolean isHeader(String print){
		boolean isHeader = false;
		
		if(Constants.getHeaderSingleArea().contains(print.trim()) ||Constants.getHeaderInput().contains(print.trim()) || Constants.getHeaderOutput().contains(print.trim())){
		
		isHeader = true;
		}
		
		
		
		return isHeader;
		
	}
	
	
	/**
	 * Add the header to the input string \n
	 * @param print the input string
	 * @param header the header, can be "input" or output"
	 * @return a string with header at top of the input string
	 */
	
	public static String addHeader(String print, String type){
		
		if(type.equals(Constants.SINGLE_AREA)){
			print = Constants.getHeaderSingleArea() + print;
		}else if(type.equals(Constants.INPUT_AREA)){
			print = Constants.getHeaderInput() + print;
		}else if(type.equals(Constants.OUTPUT_AREA)){
			print = Constants.getHeaderOutput() + print;
		}
		
		return print;
	}
	
	/**
	 * Add the filler to the input string
	 * @param print the input string 
	 * @param fillerCount the size of the filler
	 * @return a string with filler at bottom of the input string
	 */
	
	public static String addFiller(String print, int fillerCount){
			
			String filler = "03 FILLER" 
				+Pic.addPicSpaces("FILLER", "03")
				+"PIC X("
				+fillerCount
				+").";
	
			filler = Index.addIndexSpaces(filler);
			
			print = print 
			+filler;
		

		return print;
		
	}
	

	/**
	 * This return a number formatted like this:
	 * 01
	 * 02
	 * 03
	 * 04
	 * ..
	 * 10
	 * ..
	 * 24
	 * ..
	 * 
	 * @param number an int to convert
	 * @return a string with two byte like "04"
	 */


public static String formatNumber(int number) {

	
	String indexStr = "";		
	if (number<10){
		indexStr = "0"+number;
	}else{
		indexStr = number+"";
	}
	
	return indexStr;
}

	/**
	 * rimuove le linee senza indice (commenti o errori) o nulle
	 * @param linePropertyList una lista di linee non pulita
	 * @return un LinkedHashMap<Integer,LineProperty> 
	 */
		
		public static LinkedHashMap<Integer,LinePropertyBean> cleanList(LinkedHashMap<Integer,LinePropertyBean> linePropertyList){
			LinkedHashMap<Integer,LinePropertyBean> linePropertyListSort = new LinkedHashMap<Integer,LinePropertyBean>();
			int increaser = 1;
	
			for(int i = 1; i<=linePropertyList.size()+1; i++){
				LinePropertyBean line = (LinePropertyBean) linePropertyList.get(i);
				
				if(line != null && !line.getIndex().equals("")){
										
					line.setNumRiga(increaser);
					linePropertyListSort.put(increaser, line);
					increaser = increaser +1;
				}
			}
			
			return linePropertyListSort;
		}

	/**
	 * Scorre il buffered reader e crea una lista di Stringhe grezza
	 * @param br un buffer reader
	 * @return un LinkedHashMap<Integer, String> con dentro ogni riga del buffer
	 */
	
	
		public static LinkedHashMap<Integer, String> popolaLineeNonFormattate(
				BufferedReader br) {
			
			try {
			int x = 1;
			LinkedHashMap<Integer,String> lineeNonFormattate = new LinkedHashMap<Integer,String>();
			String inputLine;
			
				inputLine = br.readLine();
	
			while(inputLine != null){
				lineeNonFormattate.put(x,inputLine);
				x++;
				inputLine = br.readLine();
			}
	
	
			return lineeNonFormattate;
			
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	
		}

	/**
	  * Scorre la lista di linee non formattate, le pulisce e popola i dati per ogni linea: indice, campo, pic, occurs, ecc
	  * @param lineeNonFormattate Lista di linee non formattate
	  * @return un linkedhashmap di int e Oggetti Linea (con proprietà)
	  */
		
		public static LinkedHashMap<Integer, LinePropertyBean> popolaDatiLinee(LinkedHashMap<Integer,String> lineeNonFormattate) {
			
			String inputLine = "";
			LinkedHashMap<Integer, LinePropertyBean> linePropertyList =  new LinkedHashMap<Integer,LinePropertyBean>();
			
			//formatto linee
			for(int i = 1; i<=lineeNonFormattate.size(); i++){
				inputLine = lineeNonFormattate.get(i).toString();
				
				LinePropertyBean line = new LinePropertyBean();
	
				if(LineUtils.isHeader(inputLine)){
					inputLine = "";
						}
				
				if(hasRedefinesOnTwoLines(inputLine)){
					
					i++;
					
					String nextLine = lineeNonFormattate.get(i).toString();
					nextLine = LineUtils.cleanLine(nextLine);
					inputLine = inputLine + " " + nextLine;
					
				}
				
				inputLine = LineUtils.cleanLine(inputLine);
				
				line.setIndex(searchLine.searchIndex(inputLine));
				line.setField(searchLine.searchField(inputLine));
				line.setPicType(searchLine.searchPicType(inputLine));
				line.setPicValue(searchLine.searchPicValue(inputLine));
				line.setVirtualFloat(searchLine.searchVirtualFloat(inputLine));
				line.setOccurs(searchLine.searchOccurs(inputLine));
				line.setDichCount(searchLine.searchDichCount(inputLine));
				line.setRedefines(searchLine.searchSpecials(inputLine));
				line.setFullLine(inputLine);
				
				linePropertyList.put(i, line);	
						
			}
			

			Model.setRawLines(linePropertyList);
			return linePropertyList;
		}
		
		/**
		 * if it has redefines writter in more than one line.
		 * 
		 * ex:
		 * <p>003800         10 I8DH541-FAM-FLAG-SOST-CONIUGE    REDEFINES</p>        
		 * <p>003900            I8DH541-FAM-PERCENTUALE          PIC X(003). </p>     
		 * 
		 * @param inputLine
		 * @return
		 */

	private static boolean hasRedefinesOnTwoLines(String inputLine) {
	
		if(inputLine.contains("REDEFINES") && inputLine.indexOf(".") == -1){
			return true;
		}
		
		return false;
	}

	public static ArrayList<LinePropertyBean> popolaDatiLineeNUOVO(LinkedHashMap<Integer,String> lineeNonFormattate) {
		
		String inputLine = "";
		ArrayList<LinePropertyBean> linePropertyList =  new ArrayList<LinePropertyBean>();
		
		//formatto linee
		for(int i = 1; i<lineeNonFormattate.size(); i++){
			inputLine = lineeNonFormattate.get(i).toString();
			
			LinePropertyBean line = new LinePropertyBean();
			//TODO contatore raw
			//countRaw = Counter.contatoreRaw(inputLine, countRaw);
			
	
			if(LineUtils.isHeader(inputLine)){
				inputLine = "";
					}
			
			inputLine = LineUtils.cleanLine(inputLine);
			
			line.setIndex(searchLine.searchIndex(inputLine));
			line.setField(searchLine.searchField(inputLine));
			line.setPicType(searchLine.searchPicType(inputLine));
			line.setPicValue(searchLine.searchPicValue(inputLine));
			line.setOccurs(searchLine.searchOccurs(inputLine));
			line.setDichCount(searchLine.searchDichCount(inputLine));
			line.setNumRiga(i);
			
			linePropertyList.add(line);	
			
					
		}
		
		Model.setLinee(linePropertyList);
		return linePropertyList;
	}
	
	

}
