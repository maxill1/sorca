package com.cblformatter.utils;




import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.cblformatter.model.beans.LineProperty;
import com.cblformatter.model.beans.Settings;

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
		if(!startWithIndex(inputLine) && !startWith6Numbers(inputLine)&&!inputLine.trim().equals("")){
			inputLine = Index.checkIndexForErrorsAndClean(inputLine);
			
			if(inputLine.contains("FILLER")){
				inputLine = "01 "+inputLine;
			}

		}
		
		if(!inputLine.equals("") && (inputLine.contains(Costants.getProgramcall())) && !LineUtils.isHeader(inputLine)){							


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
	
	/**
	 * check if the line start with an index like 01, 02, 03 ...
	 * @param inputLine the line to check
	 * @return a boolean
	 */
	
	public static boolean startWithIndex(String inputLine){
		boolean start = false;
		
		for(int x = 0; x<13 ; x++){
			
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
	 * This prepare a line with all the info contained in the line
	 * @param line the line object containing values
	 * @return a String ready to output file. 
	 * ex: 02 FIELD OCCOURS 1 PIC X(01).
	 */


	public static String printLine(LineProperty line){
		String print = "";
		String EOL = Settings.getEOL();
		String indice="";
		String campo="";
		String PIC="";
		int value = 0;
		int occurs = 0;
		String occursStr = "";	
		int dichCount = 0;
	
		
		
		if(!line.getIndex().equals("") && !line.getField().equals("")){
			
		try{

		indice = line.getIndex();
		campo = line.getField();
		PIC = line.getPicType();
		value = line.getPicValue();
		occurs = line.getOccurs();
					

		if(!indice.equals("") && !LineUtils.isHeader(campo)){
			indice = Index.replaceIndex(Integer.parseInt(indice));
		}

		} catch (NumberFormatException e) {
			MessageDialog.openError(null, "Errore","Si è verificato un errore \n"+
			"Controlla che non ci siano commenti non asteriscati sul campo: " + campo);
			e.printStackTrace();
		}
		
		if (occurs != 0){
			occursStr = "   OCCURS " + occurs;
			campo = campo + occursStr;
		}
	
		dichCount = line.getDichCount();
		}
		
		if(dichCount != 0){
			print = "";
		}else if (!campo.equals("")){
			
		indice = Index.addIndexSpaces(indice);
		

		print = indice 
				+ " " 
				+ campo;
		
		if(!PIC.equals("")){
			print = print + Pic.addPicSpaces(campo,indice)
					+ PIC
					+ Convert.covertPicValueToPrint(formatNumber(value));
		}
		
		print = print
				+ "."
				+EOL;
		
		}
		
		return print;
	}
	/**
	 * check if this line is part of an header
	 * @param print a String line
	 * @return
	 */
	
	
	public static boolean isHeader(String print){
		boolean isHeader = false;
		
		if(Costants.getHeaderInput().contains(print.trim()) || Costants.getHeaderOutput().contains(print.trim())){
		
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
	
	public static String addHeader(String print, String header){
		if(header.equals("input")){
			print = Costants.getHeaderInput() + print;
		}else if(header.equals("output")){
			print = Costants.getHeaderOutput() + print;
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
	
	

}
