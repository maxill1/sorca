package com.sorca.utils;



public class PlatformUtils {

	public static int getSelection() {
		
		String os = System.getProperty("os.name").toLowerCase();
		
		if(os.contains("linux")){
			return 1;
		}
		if(os.contains("windows")){
			return 0;
		}
		if(os.contains("osx")){
			return 1;
		}

		return 0;
	}
	
	public static String getEncoding() {
		
		String os = System.getProperty("os.name").toLowerCase();
		
		int sel = 0;
		if(os.contains("linux")){
			sel = 1;
		}
		if(os.contains("windows")){
			sel = 0;
		}
		if(os.contains("osx")){
			sel = 1;
		}

		return Constants.EncodingName[sel];
	}
	
	public static String getEOL() {
		
		String os = System.getProperty("os.name").toLowerCase();
		
		int sel = 0;
		if(os.contains("linux")){
			sel = 1;
		}
		if(os.contains("windows")){
			sel = 0;
		}
		if(os.contains("osx")){
			sel = 1;
		}

		return Constants.EOLname[sel];
	}

}
