package com.cblformatter.model.beans;

public class Settings {

	private static int count = 32084; 
	private static int indexSpaces = 9;
	private static int picSpaces = 58;
	private static boolean add2ToIndex = true;
	private static String programCall = "I8D";
	private static String programCallAlt = "FI8";
	private static String codifica = "UTF-8";
	private static String EOL = "\r\n";
	private static boolean HandleErrors = true;
	private static boolean headerPresente = false;
	private static boolean fillerPresente = false;
	
	
	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		Settings.count = count;
	}
	public static int getIndexSpaces() {
		return indexSpaces;
	}
	public static void setIndexSpaces(int indexSpaces) {
		Settings.indexSpaces = indexSpaces;
	}
	public static int getPicSpaces() {
		return picSpaces;
	}
	public static void setPicSpaces(int picSpaces) {
		Settings.picSpaces = picSpaces;
	}
	public static boolean isAdd2ToIndex() {
		return add2ToIndex;
	}
	public static void setAdd2ToIndex(boolean add2ToIndex) {
		Settings.add2ToIndex = add2ToIndex;
	}
	public static String getProgramCall() {
		return programCall;
	}
	public static void setProgramCall(String programCall) {
		Settings.programCall = programCall;
	}
	public static String getProgramCallAlt() {
		return programCallAlt;
	}
	public static void setProgramCallAlt(String programCallAlt) {
		Settings.programCallAlt = programCallAlt;
	}
	public static String getCodifica() {
		return codifica;
	}
	public static void setCodifica(String codifica) {
		Settings.codifica = codifica;
	}
	public static String getEOL() {
		return EOL;
	}
	public static void setEOL(String eOL) {
		EOL = eOL;
	}
	public static boolean isHandleErrors() {
		return HandleErrors;
	}
	public static void setHandleErrors(boolean handleErrors) {
		HandleErrors = handleErrors;
	}
	public static void setHeaderPresente(boolean headerPresente) {
		Settings.headerPresente = headerPresente;
	}
	public static boolean isHeaderPresente() {
		return headerPresente;
	}
	public static void setFillerPresente(boolean fillerPresente) {
		Settings.fillerPresente = fillerPresente;
	}
	public static boolean isFillerPresente() {
		return fillerPresente;
	}


}
