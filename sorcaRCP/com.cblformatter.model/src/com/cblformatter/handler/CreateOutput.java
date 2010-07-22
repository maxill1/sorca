package com.cblformatter.handler;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;

import com.cblformatter.model.beans.LineProperty;
import com.cblformatter.model.beans.Lists;
import com.cblformatter.model.beans.Occurs;
import com.cblformatter.model.beans.Settings;
import com.cblformatter.model.counters.Counter;
import com.cblformatter.utils.Costants;
import com.cblformatter.utils.LineUtils;
import com.cblformatter.utils.searchLine;

public class CreateOutput {

	public static void createOutputFile(File inputFile, File outputFile, int tipo){

		try {
		
		int indexSpaces = Settings.getIndexSpaces();
		int picSpaces = Settings.getIndexSpaces();
		boolean add2ToIndex = Settings.isAdd2ToIndex();
		String programCall = Settings.getProgramCall();
		String programCallAlt = Settings.getProgramCallAlt();
		String codifica = Settings.getCodifica();
		String EOL = Settings.getEOL();
		boolean HandleErrors = Settings.isHandleErrors();
		

		LinkedHashMap<Integer, LineProperty> cblFormatted = processFile(inputFile, indexSpaces, picSpaces, add2ToIndex, programCall ,programCallAlt, codifica, EOL, HandleErrors);
		
		//creo la stringa da stampare su file
		String toPrint = preparePrint(cblFormatted, tipo);

		//scrivo File
		
		//creo il file aggiungendo nome e tipo
		outputFile = creaFileOutput(outputFile, inputFile, tipo);
		
		
		//Esegui codifica file
		toPrint = encodeString(toPrint);
		
		//scrivo il file
		scriviSuFile(outputFile, toPrint);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
}



private static String encodeString(String toPrint) {
		String codificata = "";
		Charset charset = null;
		if(Settings.getCodifica().equals("UTF-8")){
			charset = Charset.forName("UTF-8");
			}else if(Settings.getCodifica().equals("ISO-8859-1")){
		    charset = Charset.forName("ISO-8859-1");
			}    
		    
			 CharsetDecoder decoder = charset.newDecoder();
		    CharsetEncoder encoder = charset.newEncoder();
			    

		    
		    try {
		        // Convert a string to ISO-LATIN-1 bytes in a ByteBuffer
		        // The new ByteBuffer is ready to be read.
		        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(toPrint));
		    
		        // Convert ISO-LATIN-1 bytes in a ByteBuffer to a character ByteBuffer and then to a string.
		        // The new ByteBuffer is ready to be read.
		        CharBuffer cbuf = decoder.decode(bbuf);
		       codificata = cbuf.toString();
		        
		    } catch (CharacterCodingException e) {
		    	MessageDialog.openError(new Shell(), "Errore di codifica", "il file non è stato codificato correttamente");
		    }
		    
		return codificata;
	}



private static LinkedHashMap<Integer,LineProperty> processFile(File inputFile,
									int indexSpaces,
									int picSpaces,
									boolean add2ToIndex,
									String programCall,
									String programCallAlt,
									String codifica,
									String EOL,
									boolean HandleErrors) throws FileNotFoundException{



	
	/** variabili*/
	
	//Liste
	LinkedHashMap<Integer,LineProperty>linePropertyList; //grezze
	LinkedHashMap<Integer,LineProperty> linePropertyListSort; //ordinate
	LinkedHashMap<Integer,String> lineeNonFormattate; //Stringhe non formattate
	
	
	String fileName = inputFile.getName();

	//lettura file selezionato
	FileReader fr = new FileReader(inputFile);
	BufferedReader br = new BufferedReader(fr);

	/***** controllo colonne e numeri ***/
	//leggo ogni linea e creo una lista
	lineeNonFormattate = Lists.popolaLineeNonFormattate(br);	
	
	//creo una lista di linee con proprietà
	linePropertyList =	Lists.popolaDatiLinee(lineeNonFormattate);
	
	//creo lista ordinata
	linePropertyListSort = Lists.cleanList(linePropertyList);
	Lists.setFormattedLines(linePropertyListSort);
		
	return linePropertyListSort;
}



public static String preparePrint(LinkedHashMap<Integer,LineProperty> linePropertyListSort, int tipo){
	String outPutLine ="";
	int hierarchy = 0;
	int counterOccurs = 0;
	int numOccurs = 0;
	int occursTab = 0;
	boolean occursMode = false;
	int count = Settings.getCount();
	
	
	//listaOccurs
	ArrayList<Occurs> occursList = new ArrayList<Occurs>();
	
	
	//Stampa
	for(int i=1; i<linePropertyListSort.size(); i++){
		
		
	LineProperty line = (LineProperty) linePropertyListSort.get(i);
			
		if(line.getOccurs() != 0 ){
			Occurs occurs = new Occurs();
			occurs.setLineNum(i);
			occurs.setHierarchy(hierarchy+1);
			occurs.setNumOccurs(line.getOccurs());
			occurs.setIndex(Integer.parseInt(line.getIndex()));
			occurs.setByteCount(line.getPicValue());
			occursList.add(occurs);	

			//occursMode = true;
		}
		Lists.setOccurs(occursList);

	outPutLine = outPutLine + LineUtils.printLine(line);
	
	}

	//TODO
	//LinkedHashMap countList = Lists.noOccursListTrim(linePropertyListSort, occursList);
	if(tipo == 0){
	count = Counter.contatore(linePropertyListSort, count, 1,true);
	}
	if(tipo == 0){
		count = Counter.contatore(linePropertyListSort, count, 1,false);
	}
	
	//counterOccurs = OccursCounter.contatoreOccurs(occursList, linePropertyListSort);

	
	count = count - counterOccurs; 
	
	/**** add Header e Filler ***/
	if(!Settings.isHeaderPresente()){
	if(tipo == 0){
		outPutLine = LineUtils.addHeader(outPutLine, "input");
	}
	if(tipo == 1){
		outPutLine = LineUtils.addHeader(outPutLine, "output");
	}
	}
	if(!Settings.isFillerPresente()){
	outPutLine = LineUtils.addFiller(outPutLine, count);
	}

	return outPutLine;
	
}

public static void scriviSuFile(File fileOut,String outPutLine) throws IOException{

	FileWriter fw = new FileWriter(fileOut);

	String fileSalvato = fileOut.getAbsolutePath();

	System.out.println(fileSalvato);

	fw.write(outPutLine);
	fw.flush();
	fw.close();

	MessageDialog.openInformation(null, "Salvataggio File", "File "+fileSalvato+" Salvato con successo");

}
	
		
	public static File creaFileOutput(File outputFile, File inputFile, int tipo){
		
		String type = "";
		if (tipo == 0){
			type = "INPUT";
		}else if(tipo == 1){
			type = "OUTPUT";
		}
	
	String fileName = inputFile.getName();


	String filePath = outputFile.getAbsolutePath()+"/";
	
	fileName = fileName.toLowerCase().replace(".txt","").replace(".cbl","").replace("BEAN", "").replace("-", "").replace("_", "")
	+"_"+type+".cbl"; 
	
	fileName = fileName.toUpperCase();
	
	
	String fileFormatted = filePath+fileName;
		
	outputFile = new File(fileFormatted);
	
	return outputFile;
	
}
	
}
