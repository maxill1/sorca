package com.cblformatter.model.beans;




import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.cblformatter.model.counters.OccursCounter;
import com.cblformatter.utils.LineUtils;
import com.cblformatter.utils.searchLine;

public class Lists {
	
	
	private static LinkedHashMap formattedLines;
	private static LinkedHashMap rawLines;
	private static LinkedHashMap lineProperty;
	private static ArrayList<Occurs> occurs;
	


	public static void setOccurs(ArrayList<Occurs> occurs) {
		Lists.occurs = occurs;
	}

	public static ArrayList<Occurs> getOccurs() {
		return occurs;
	}
	
	public static LinkedHashMap getRawLines() {
		return rawLines;
	}

	public static void setRawLines(LinkedHashMap rawLines) {
		rawLines = rawLines;
	}

	public static LinkedHashMap getFormattedLines() {
		return formattedLines;
	}

	public static void setFormattedLines(LinkedHashMap formattedLines) {
		formattedLines = formattedLines;
	}

	
	public static void setLineProperty(LinkedHashMap lineProperty) {
		lineProperty = lineProperty;
	}

	public static LinkedHashMap getLineProperty() {
		return lineProperty;
	}
	
/**
 * rimuove le linee senza indice (commenti o errori) o nulle
 * @param linePropertyList una lista di linee non pulita
 * @return un LinkedHashMap<Integer,LineProperty> 
 */
	
	public static LinkedHashMap<Integer,LineProperty> cleanList(LinkedHashMap<Integer,LineProperty> linePropertyList){
		LinkedHashMap<Integer,LineProperty> linePropertyListSort = new LinkedHashMap<Integer,LineProperty>();
		int increaser = 1;

		for(int i = 1; i<linePropertyList.size(); i++){
			LineProperty line = (LineProperty) linePropertyList.get(i);
			if(!line.getIndex().equals("") && !line.equals(null)){
				linePropertyListSort.put(increaser, line);
				increaser = increaser +1;
			}
		}
		
		return linePropertyListSort;
	}

	public static LinkedHashMap occursListTrim(LinkedHashMap linePropertyList, int actualNumLine, int occursLineCount){
		
	int iterator = 0;
	LinkedHashMap shortList = new LinkedHashMap();
	
	for(int k = actualNumLine; k<actualNumLine+occursLineCount; k++){
		LineProperty lineOcc = (LineProperty) linePropertyList.get(k);						
		shortList.put(iterator, lineOcc);
		iterator = iterator +1;
	}
	return shortList;
	}
	
	public static LinkedHashMap noOccursListTrim(LinkedHashMap linePropertyList, ArrayList<Occurs> occursList){
		linePropertyList = new LinkedHashMap();
		LinkedHashMap lineOccursList = new LinkedHashMap();
		Occurs occurs;
		int numOccurs = 0;
		
		for(int x = 0; x<occursList.size(); x++){
			occurs = occursList.get(x);
		//	int hierarchy = occurs.getHierarchy();
			int occIndex = occurs.getIndex();
			numOccurs = occurs.getNumOccurs();
			int numLine = occurs.getLineNum();
			int byteCount = occurs.getByteCount();
			
			int occursLineCount = OccursCounter.occursLineCount(linePropertyList, numLine, numLine+1);
	
			lineOccursList = occursListTrim(linePropertyList, numLine, occursLineCount);

			//rimuovo le occurs
			
			for(int k = numLine; k<occursLineCount; k++){
				
					linePropertyList.remove(k);
					
		}
		

//		for(int k = 1; k<linePropertyList.size(); k++){
//				
//			if(numOccurs == 0){
//				LineProperty line = (LineProperty) linePropertyList.get(k);					
//				linePropertyList.put(k, line);
//			}
//		
//		
		}
		return linePropertyList;
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
		Lists.setRawLines(lineeNonFormattate);


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
		
		public static LinkedHashMap<Integer, LineProperty> popolaDatiLinee(LinkedHashMap<Integer,String> lineeNonFormattate) {
			
			String inputLine = "";
			LinkedHashMap<Integer, LineProperty> linePropertyList =  new LinkedHashMap<Integer,LineProperty>();
			
			//formatto linee
			for(int i = 1; i<lineeNonFormattate.size(); i++){
				inputLine = lineeNonFormattate.get(i).toString();
				
				LineProperty line = new LineProperty();
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
				
				linePropertyList.put(i, line);	
				
						
			}
			
			Lists.setRawLines(linePropertyList);
			return linePropertyList;
		}
	
	
}
