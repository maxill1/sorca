package com.sorca.utils;

public class Decode {

	public static String indexOfEOLName(String b) {
		

		return null;
	}

	public static String fromEOLNameToEOLString(String b) {
		if(b == null)return Constants.dos;
		if(b.equals("DOS"))return Constants.dos;
		if(b.equals("UNIX"))return Constants.unix;
		return null;
	}

	public static String fromEOLStringToEOLName(String b) {
		if(b == null)return "DOS";
		if(b.equals(Constants.dos))return "DOS";
		if(b.equals(Constants.unix))return "UNIX";
		return null;
	}
	
}
