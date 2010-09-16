package com.cblformatter.utils;

import com.cblformatter.model.beans.Model;



public class Constants {

	
	/******** costanti Header e contatore ***********/
	

	private final static String programCall = "I8D";
	private final static String programCallAlt = "FI8";
	public static int input = 0;
	public static int output = 1;
	public static String dos = "\r\n";
	public static String unix = "\n";
	public static String UTF8 = "UTF-8";
	public static String ISO88591 = "ISO-8859-1";
	

	public static String EOL = Model.getSettingsBean().getEOL();
	


	public final static String headerOutput = 
		Index.addIndexSpaces()+"IDENTIFICATION DIVISION."+EOL+
		Index.addIndexSpaces()+"PROGRAM-ID. LOGZ."+EOL+
		Index.addIndexSpaces()+"ENVIRONMENT DIVISION."+EOL+
		Index.addIndexSpaces()+"DATA DIVISION."+EOL+
		Index.addIndexSpaces()+"WORKING-STORAGE SECTION."+EOL+
		Index.addIndexSpaces()+"01 CICS-CALL-REQ."+EOL+
		Index.addIndexSpaces()+"  03 NUM-CALL"+Pic.addPicSpaces("NUM-CALL","03")+"PIC 9."+EOL+
		Index.addIndexSpaces()+"  03 TOT-CALL"+Pic.addPicSpaces("TOT-CALL","03")+"PIC 9."+EOL+
		Index.addIndexSpaces()+"  03 DATI-MSG."+EOL+
		Index.addIndexSpaces()+"    05 COD-ERR"+Pic.addPicSpaces("COD-ERR","05")+"PIC X(2)."+EOL+
		Index.addIndexSpaces()+"    05 DATI-DESC"+Pic.addPicSpaces("DATI-DESC","05")+"PIC X(80)."+EOL;
	
	public final static String headerInput =
		Index.addIndexSpaces()+"IDENTIFICATION DIVISION."+EOL+
		Index.addIndexSpaces()+"PROGRAM-ID. LOGZ."+EOL+
		Index.addIndexSpaces()+"ENVIRONMENT DIVISION."+EOL+
		Index.addIndexSpaces()+"DATA DIVISION."+EOL+
		Index.addIndexSpaces()+"WORKING-STORAGE SECTION."+EOL+
		Index.addIndexSpaces()+"01 CICS-CALL-REQ."+EOL+
		Index.addIndexSpaces()+"  03 FLAG-ELAB"+Pic.addPicSpaces("FLAG-ELAB","03")+"PIC X."+EOL+
		Index.addIndexSpaces()+"  03 NUM-CALL"+Pic.addPicSpaces("NUM-CALL","03")+"PIC 9."+EOL+
		Index.addIndexSpaces()+"  03 TOT-CALL"+Pic.addPicSpaces("TOT-CALL","03")+"PIC 9."+EOL;

	
	public static final Object PARENT_INDEX 	= "00";
	public static final String SINGLE_AREA 		= "SINGLE_AREA";
	public static final String INPUT_AREA 		= "INPUT_AREA";
	public static final String OUTPUT_AREA 		= "OUTPUT_AREA";
	public static final String BOTH_AREA 		= "BOTH_AREA";
		
}
