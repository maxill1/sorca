package com.sorca.counters;




import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.sorca.model.beans.LinePropertyBean;
import com.sorca.model.beans.OccursBean;

public class OccursCounter {

	
	public static int contatoreOccurs(String inputLine, int counterOccurs) {
		
		//blocco
		if(inputLine.contains("PIC X.)") 
			|| inputLine.contains("PIC 9.") 
			|| inputLine.contains("(01)")){
			counterOccurs = counterOccurs - 1;
		}else{
				
				if(inputLine.contains("PIC 9(") || inputLine.contains("PIC X(")){
					int inizio = inputLine.lastIndexOf("(")+1;
					int fine = inputLine.lastIndexOf(")");
					String substring = inputLine.substring(inizio, fine).replace(".", "").trim();
		
					int daAggiungere = Integer.parseInt(substring);

					counterOccurs = counterOccurs + daAggiungere;
											
			}
				
				//gestione virgola virtuale
				if(inputLine.contains("V9(")){
					int inizio = inputLine.lastIndexOf("V9(")+3;
					int fine = inputLine.lastIndexOf(")");
					String substring = inputLine.substring(inizio, fine).replace(".", "").trim();

					int daAggiungere = Integer.parseInt(substring);

					counterOccurs = counterOccurs + daAggiungere;
					}
						
		}

		
		return counterOccurs;
	}
	
	
//	public static int contatoreOccurs(ArrayList<Occurs> occursList, LinkedHashMap linePropertyList){
//		
//		int counterOccurs = 0;
//		Occurs occurs;
//		LineProperty line;
//		int count = 0;
//		int partOccCount = 0;
//	//	ArrayList<Occurs> occursListH = createOccursHierarchy(occursList);
//		
//		//ottengo occurs da contare 
//		for(int x = 0; x<occursList.size(); x++){
//			occurs = occursList.get(x);
//		//	int hierarchy = occurs.getHierarchy();
//			int occIndex = occurs.getIndex();
//			int numOccurs = occurs.getNumOccurs();
//			int numLine = occurs.getLineNum();
//			int byteCount = occurs.getByteCount();
//
//			partOccCount =  partOccCount + partialOccursCount(occurs, linePropertyList);
//			
//			}		
//	
//		count = partOccCount; 
//		//TODO gestire occours innestati
//
//	return count;
//	}
//	
	
//	public static int partialOccursCount(Occurs occurs,	LinkedHashMap linePropertyList) {
//
//		LineProperty line;
//		int count = 0;
//		int partOccCount = 0;
//		int tempCount = 0;
//		//ArrayList<Occurs> occursListH = createOccursHierarchy(occursList);
//		LinkedHashMap lineListOccurs;
//
//		int occursIndex = occurs.getIndex();
//		int numOccurs = occurs.getNumOccurs();
//		int occursNumLine = occurs.getLineNum();
//		int occursPicValue = occurs.getByteCount();
//
//		//scorro le linee partendo dalla linea dell'occurs
//		for(int j = occursNumLine+1; j<linePropertyList.size(); j++){
//			//variabili di linea
//			line = (LineProperty) linePropertyList.get(j);			
//			int lineIndex = Integer.parseInt(line.getIndex());		
//			int linePicValue = line.getPicValue();
//			int lineNumOccurs = line.getOccurs();
//			int actualNumLine = j;
//
//			/*********** CONTROLLO LA LINEA *****/
//			int occoursLineCount = occursLineCount(linePropertyList, occursNumLine, actualNumLine );
//
//			LineProperty lineCount;
//			if( occoursLineCount  != 0){
//				//Se l'occurs ha più di 0 linee (quindi ha un corpo)
//
//				//riduco la lista ai soli campi dell'occurs selezionato
//				lineListOccurs = Lists.occursListTrim(linePropertyList, actualNumLine, occoursLineCount);
//
//				partOccCount = Counter.contatore(lineListOccurs, partOccCount);
//
//			}else{
//				//CONTA il singolo occurs
//				partOccCount = occursPicValue*numOccurs;
//				return partOccCount;
//
//			}
//
//
//		}		
//
//		return partOccCount;
//	}

	public static int occursLineCount(LinkedHashMap linePropertyList, int occursNumLine, int actualNumLine) {
		boolean hasNext = false;
		int lineCount = 0;
		LinePropertyBean line;
		LinePropertyBean occLine = (LinePropertyBean) linePropertyList.get(occursNumLine);
		int occIndex = Integer.parseInt(occLine.getIndex());
		
		for(int x = actualNumLine; x< linePropertyList.size(); x++){
			line = (LinePropertyBean) linePropertyList.get(x);
			
			int lineIndex = Integer.parseInt(line.getIndex());
			
			if(lineIndex > occIndex && actualNumLine != occursNumLine){			
				lineCount = lineCount +1;

			}else if((lineIndex == occIndex || lineIndex< occIndex) && actualNumLine != occursNumLine){
				x = linePropertyList.size();
				return lineCount;
			}
			
		}

		//TODO invece di hasNext boolean contare quanti next ha cosi da sommare poi le linee in un ciclo fuori da questo metodo!
		return lineCount;
	}
	
	
	
	public static int occursLineCount(LinkedHashMap linePropertyList, int occursNumLine) {
		boolean hasNext = false;
		int lineCount = 0;
		LinePropertyBean line;
		LinePropertyBean occLine = (LinePropertyBean) linePropertyList.get(occursNumLine);
		int occIndex = Integer.parseInt(occLine.getIndex());
		int actualNumLine = occursNumLine+1;
		
		
		
		for(int x = actualNumLine; x< linePropertyList.size(); x++){
			line = (LinePropertyBean) linePropertyList.get(x);
			
			int lineIndex = Integer.parseInt(line.getIndex());
			String lineField = line.getField();
			int lineOccurs = line.getOccurs();
			int lineValue = line.getPicValue();
			

//			if(lineOccurs != 0 && lineIndex != occIndex){
//				return lineCount+1;
//			}
			
			if(lineIndex > occIndex && actualNumLine != occursNumLine){			
				lineCount = lineCount +1;

			}else if((lineIndex == occIndex || lineIndex< occIndex) && actualNumLine != occursNumLine){
				x = linePropertyList.size();
				return lineCount;
			}
			
		}

		//TODO invece di hasNext boolean contare quanti next ha cosi da sommare poi le linee in un ciclo fuori da questo metodo!
		return lineCount;
	}
	

	public static ArrayList createOccursHierarchy(ArrayList<OccursBean> occursList) {
		OccursBean occurs;
		OccursBean occurs2;
		int hierarchy = 0;
		
		ArrayList<OccursBean> occursListH = new ArrayList<OccursBean>();
		
		for(int x = 0; x<occursList.size();x++){
			occurs =  occursList.get(x);
			occurs2 =  occursList.get(x+1);
			
			if(occurs2.getIndex() > occurs.getIndex()){
				occurs.setHierarchy(hierarchy+1);		
			}else if(occurs2.getIndex() <= occurs.getIndex()){
				occurs.setHierarchy(1);	
			} 
			
			occursListH.add(occurs);
			
			
		}
		
		return occursListH;
		
	}

	public static boolean isFirstOccurs(){
		
		return false;
	}

	public static boolean isSelfOccurs(int lineIndex, int occurseIndex, int byteCount, int numLine, int iterator){
					boolean self;	
				// indice uguale e picValue presente e num riga diverso == fine occurs corrente
				if(lineIndex <= occurseIndex && byteCount > 0 && numLine != iterator){
						self =true;
					}else{
						self =false;
				}
				return self;
	}

	public static boolean hasInnestedOccurs(){

		return false;
	}
	
	
	
}
