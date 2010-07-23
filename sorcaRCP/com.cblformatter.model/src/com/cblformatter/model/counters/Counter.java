package com.cblformatter.model.counters;




import java.util.LinkedHashMap;

import com.cblformatter.model.beans.LinePropertyBean;
import com.cblformatter.utils.LineUtils;

public class Counter {

	public static int contatore(LinkedHashMap lista, int count, int x,boolean input) {
		int occursMolt = 1;
		int occursIndex = 0;
		LinkedHashMap listaOcc = new LinkedHashMap();
		int occursLineCount = 0;	
		
		int countPartial = 0;
		
		//conteggio header
		if(input){
			countPartial = countPartial +3;	
		}else{
			countPartial = countPartial +84;	
		}


		for(; x<lista.size(); x++){
			
			LinePropertyBean line = (LinePropertyBean) lista.get(x);
			int lineIndex = Integer.parseInt(line.getIndex());
			String lineField = line.getField();
			int lineOccurs = line.getOccurs();
			int lineValue = line.getPicValue();
			int l = 0;
			occursLineCount = 0;
			
			//aggiorno l'appoggio moltiplicatore di occurs
			if(occursLineCount == 0 && occursMolt != 1){
				occursMolt = 1;
			}

			if(lineOccurs == 0){

				int value = line.getPicValue();
				countPartial = countPartial + value;
			}else {
				occursLineCount = OccursCounter.occursLineCount(lista, x);
				//listaOcc = Lists.occursListTrim(lista, x, occursLineCount);

				//Se occurs e valore, somma subito
				if(lineOccurs != 0  && lineValue != 0){
					countPartial = countPartial + (lineValue*occursMolt);	
				}else{

					if(line.getOccurs() != 0 ){
						occursMolt = lineOccurs*occursMolt;
						occursIndex = lineIndex;
					}

					l = x+1;

					int fineOcc = l+occursLineCount; 
					//TODO provare a mettere più uno per vedere la linea successiva non sommandola ma per uscire dal ciclo

					for(; l<fineOcc; l++){

						line = (LinePropertyBean) lista.get(l);

						lineIndex = Integer.parseInt(line.getIndex());
						lineField = line.getField();
						lineOccurs = line.getOccurs();
						lineValue = line.getPicValue();

						if(lineIndex == occursIndex || lineIndex <= occursIndex){
							//senno resetta 
							occursMolt = 1;
							occursIndex = 0;
						}else{

							//se ha occurs aggiungi moltiplicatore
							if(lineOccurs != 0 ){
								occursMolt = lineOccurs*occursMolt;
							}

							//se l'indice è maggiore o uguale a quello occurs, conta
							if(lineIndex >= occursIndex ){

								countPartial = countPartial + (lineValue*occursMolt);	

							}
							

						}


					}
					//riparte la conta da dove è finito l'occurs
					x=l;
				}

			}
			

		}

		count = count - countPartial;
		
		return count;
	}


	public static int contatoreOccurs(LinkedHashMap lista, int count, int x) {
		count = 0;
		LinkedHashMap listaOcc = new LinkedHashMap();
		int occursLineCount = 0;				

		for(; x<lista.size(); x++){
			LinePropertyBean line = (LinePropertyBean) lista.get(x);

			if(line.getOccurs() == 0){

				int value = line.getPicValue();
				count = count - value;
			}else {

				occursLineCount = OccursCounter.occursLineCount(lista, x);
				listaOcc = LineUtils.occursListTrim(lista, x, occursLineCount);

				if(occursLineCount == 0){
					count = count - (line.getPicValue()*line.getOccurs());

				}

				//TODO sommare i pin di input o output

				if((line.getOccurs() != 0 && occursLineCount != 0)){
					//countTemp = contatore(lista, countTemp,++x);
					count = count - contatoreOccurs(listaOcc, count,++x);

				}
			}
		}


		return count;
	}


	public static int contatore(String inputLine, int count, boolean occursMode, int counterOccurs, int numOccurs) {

		//blocco
		if(inputLine.contains("PIC X.)") 
				|| inputLine.contains("PIC 9.") 
				|| inputLine.contains("(01)")){
			count = count - 1;
		}else{

			if(inputLine.contains("PIC 9(") || inputLine.contains("PIC X(")){
				int inizio = inputLine.lastIndexOf("(")+1;
				int fine = inputLine.lastIndexOf(")");
				String substring = inputLine.substring(inizio, fine).replace(".", "").trim();

				int daSottrarre = Integer.parseInt(substring);

				count = count - daSottrarre;

			}

			//gestione virgola virtuale
			if(inputLine.contains("V9(")){
				int inizio = inputLine.lastIndexOf("V9(")+3;
				int fine = inputLine.lastIndexOf(")");
				String substring = inputLine.substring(inizio, fine).replace(".", "").trim();

				int daSottrarre = Integer.parseInt(substring);

				count = count - daSottrarre;
			}

		}

		if(occursMode){
			//in questo caso skippo perchè cè un contatore parallelo che conta gli occurs e poi li somma (di default somma 0)
		}else{
			count = count + (numOccurs*counterOccurs);
		}




		return count;
	}



	/**
	 * count the byte size using declared size tagged by "###"
	 * @param inputLine an input String
	 * @param countRaw the count variable
	 * @return the count variable less the count parsed from input String
	 */

	public static int contatoreRaw(String inputLine, int countRaw ){
		//valore calcolato
		if(inputLine.contains("###")){
			if(inputLine.contains("(")){
				int inizio = inputLine.indexOf("(")+1;
				int fine = inputLine.indexOf(")");
				String substring = inputLine.substring(inizio, fine).replace(".", "").trim();

				if(inputLine.toLowerCase().contains("+ filler")){
					int fineFil = substring.indexOf("+ filler");
					substring = substring.substring(0,fineFil).trim();
				}

				int daSottrarre = Integer.parseInt(substring);

				countRaw = countRaw - daSottrarre;
			}
		}

		//valore dichiarato dal file
		//	if(inputLine.contains("FILLER") && !inputLine.contains("###")){
		//		if(inputLine.contains("(")){
		//			int inizio = inputLine.indexOf("(")+1;
		//			int fine = inputLine.indexOf(")");
		//			String substring = inputLine.substring(inizio, fine).replace(".", "").trim();
		//			
		//			int filler = Integer.parseInt(substring);
		//			countRaw = countRaw - filler;
		//		}

		//	}

		return countRaw;
	}

	/**
	 * get the occurs number from the line
	 * @param inputLine a String
	 * @return a int parsed from the input String
	 */


	public static int numOccurs(String inputLine) {
		int numOccours = 0;
		if(inputLine.contains("OCCURS")){
			int inizio = inputLine.indexOf("OCCURS ")+7;
			int fine = 0;
			if(inputLine.length()>inizio+5){
				fine = inizio+5;	
			}else{
				fine = inizio+(inputLine.length()-inizio);
			}
			String substring = inputLine.substring(inizio, fine).replace(".", "").trim();

			int filler = Integer.parseInt(substring);
			numOccours = filler;
		}

		return numOccours;
	}



}


