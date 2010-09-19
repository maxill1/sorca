package com.sorca.utils;




public class Constants {

	
	/******** costanti Header e contatore ***********/
	
	public static int input = 0;
	public static int output = 1;
	
	public static String[] EOLname = new String[] { "DOS", "UNIX"};
	public static String dos = "\r\n";
	public static String unix = "\n";
	
	public static String[] EncodingName = new String[] { "ISO-8859-1", "UTF-8"};
	public static String UTF8 = "UTF-8";
	public static String ISO88591 = "ISO-8859-1";

	public static final Object PARENT_INDEX 	= "00";
	public static final String SINGLE_AREA 		= "SINGLE_AREA";
	public static final String INPUT_AREA 		= "INPUT_AREA";
	public static final String OUTPUT_AREA 		= "OUTPUT_AREA";
	public static final String BOTH_AREA 		= "BOTH_AREA";
	
	public static String getHeaderOutput() {
		return Index.addIndexSpaces()+"IDENTIFICATION DIVISION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"PROGRAM-ID. LOGZ."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"ENVIRONMENT DIVISION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"DATA DIVISION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"WORKING-STORAGE SECTION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"01 CICS-CALL-REQ."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"  03 NUM-CALL"+Pic.addPicSpaces("NUM-CALL","03")+"PIC 9."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"  03 TOT-CALL"+Pic.addPicSpaces("TOT-CALL","03")+"PIC 9."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"  03 DATI-MSG."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"    05 COD-ERR"+Pic.addPicSpaces("COD-ERR","05")+"PIC X(2)."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"    05 DATI-DESC"+Pic.addPicSpaces("DATI-DESC","05")+"PIC X(80)."+Convert.decodeEOL();

	}
	
	public static String getHeaderInput() {
		return 		Index.addIndexSpaces()+"IDENTIFICATION DIVISION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"PROGRAM-ID. LOGZ."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"ENVIRONMENT DIVISION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"DATA DIVISION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"WORKING-STORAGE SECTION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"01 CICS-CALL-REQ."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"  03 FLAG-ELAB"+Pic.addPicSpaces("FLAG-ELAB","03")+"PIC X."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"  03 NUM-CALL"+Pic.addPicSpaces("NUM-CALL","03")+"PIC 9."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"  03 TOT-CALL"+Pic.addPicSpaces("TOT-CALL","03")+"PIC 9."+Convert.decodeEOL();

	}

	public static String getHeaderSingleArea() {
		
		return 
		Index.addIndexSpaces()+"IDENTIFICATION DIVISION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"PROGRAM-ID. LOGZ."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"ENVIRONMENT DIVISION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"DATA DIVISION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"WORKING-STORAGE SECTION."+Convert.decodeEOL()+
		Index.addIndexSpaces()+"01 CICS-CALL-REQ."+Convert.decodeEOL();
	
	}
		
}
