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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.cblformatter.counters.Counter;
import com.cblformatter.model.beans.LinePropertyBean;
import com.cblformatter.model.beans.Model;
import com.cblformatter.model.beans.OccursBean;
import com.cblformatter.views.utils.LineUtils;

public class CreateOutput {

	public static void createOutputFile(File inputFile, File outputFile, int tipo){

		try {
		
		int indexSpaces = Integer.parseInt(Model.getSettingsBean().getIndexSpaces());
		int picSpaces = Integer.parseInt(Model.getSettingsBean().getIndexSpaces());
		boolean add2ToIndex = Model.getSettingsBean().isAdd2ToIndex();
		String programCall = Model.getSettingsBean().getProgramCall();
		String programCallAlt = Model.getSettingsBean().getProgramCallAlt();
		String codifica = Model.getSettingsBean().getCodifica();
		String EOL = Model.getSettingsBean().getEOL();
		boolean HandleErrors = Model.getSettingsBean().isHandleErrors();
		

		LinkedHashMap<Integer, LinePropertyBean> cblFormatted = processFile();
		
//		//creo la stringa da stampare su file
//		String toPrint = preparePrint(cblFormatted, tipo);
//
//		//scrivo File
//		
//		//creo il file aggiungendo nome e tipo
//		outputFile = creaFileOutput(outputFile, inputFile, tipo);
//		
//		
//		//Esegui codifica file
//		toPrint = encodeString(toPrint);
//		
//		//scrivo il file
//		scriviSuFile(outputFile, toPrint);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
}



private static String encodeString(String toPrint) {
		String codificata = "";
		Charset charset = null;
		if(Model.getSettingsBean().getCodifica().equals("UTF-8")){
			charset = Charset.forName("UTF-8");
			}else if(Model.getSettingsBean().getCodifica().equals("ISO-8859-1")){
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



public static LinkedHashMap<Integer,LinePropertyBean> processFile() throws FileNotFoundException{


	int indexSpaces = Integer.parseInt(Model.getSettingsBean().getIndexSpaces());
	int picSpaces = Integer.parseInt(Model.getSettingsBean().getIndexSpaces());
	boolean add2ToIndex = Model.getSettingsBean().isAdd2ToIndex();
	String programCall = Model.getSettingsBean().getProgramCall();
	String programCallAlt = Model.getSettingsBean().getProgramCallAlt();
	String codifica = Model.getSettingsBean().getCodifica();
	String EOL = Model.getSettingsBean().getEOL();
	boolean HandleErrors = Model.getSettingsBean().isHandleErrors();
	String filePath = Model.getFileBean().getFileSelected();
	File inputFile = new File(filePath);

	
	/** variabili*/
	
	//Liste
	LinkedHashMap<Integer,LinePropertyBean>linePropertyList; //grezze
	LinkedHashMap<Integer,LinePropertyBean> linePropertyListSort; //ordinate
	LinkedHashMap<Integer,String> lineeNonFormattate; //Stringhe non formattate
	
	
	String fileName = inputFile.getName();

	//lettura file selezionato
	FileReader fr = new FileReader(inputFile);
	BufferedReader br = new BufferedReader(fr);

	/***** controllo colonne e numeri ***/
	//leggo ogni linea e creo una lista
	lineeNonFormattate = LineUtils.popolaLineeNonFormattate(br);	
	
	//creo una lista di linee con proprietà
	linePropertyList =	LineUtils.popolaDatiLinee(lineeNonFormattate);

//	Model.setLinee(LineUtils.popolaDatiLineeNUOVO(lineeNonFormattate));
	
	//creo lista ordinata
	linePropertyListSort = LineUtils.cleanList(linePropertyList);
	Model.setFormattedLines(linePropertyListSort);
	
	ArrayList<LinePropertyBean> linee = new ArrayList<LinePropertyBean>();
	linee.addAll(0,linePropertyListSort.values());
	
	linee = searchForChild(linee,0,null);
	
	
//	ArrayList<LinePropertyBean> lineeNew = new ArrayList<LinePropertyBean>();
//	lineeNew.add(LinePropertyBean.updateModel(linee))
	LinePropertyBean.updateModel(linee);
	
//	Model.setLinee(lineeNew);
	
//	LinePropertyBean.updateModel(lineeNew);
	
	return linePropertyListSort;
}



public static String preparePrint(LinkedHashMap<Integer,LinePropertyBean> linePropertyListSort, int tipo){
	String outPutLine ="";
	int hierarchy = 0;
	int counterOccurs = 0;
	int numOccurs = 0;
	int occursTab = 0;
	boolean occursMode = false;
	int count = Integer.parseInt(Model.getSettingsBean().getCount());
	
	
	//listaOccurs
	ArrayList<OccursBean> occursList = new ArrayList<OccursBean>();
	
	
	//Stampa
	for(int i=1; i<linePropertyListSort.size(); i++){
		
		
	LinePropertyBean line = (LinePropertyBean) linePropertyListSort.get(i);
			
		if(line.getOccurs() != 0 ){
			OccursBean occurs = new OccursBean();
			occurs.setLineNum(i);
			occurs.setHierarchy(hierarchy+1);
			occurs.setNumOccurs(line.getOccurs());
			occurs.setIndex(Integer.parseInt(line.getIndex()));
			occurs.setByteCount(line.getPicValue());
			occursList.add(occurs);	

			//occursMode = true;
		}
		Model.setOccurs(occursList);

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
	if(!Model.getSettingsBean().isHeaderPresente()){
	if(tipo == 0){
		outPutLine = LineUtils.addHeader(outPutLine, "input");
	}
	if(tipo == 1){
		outPutLine = LineUtils.addHeader(outPutLine, "output");
	}
	}
	if(!Model.getSettingsBean().isFillerPresente()){
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



	private static ArrayList<LinePropertyBean> searchForChild(
			ArrayList<LinePropertyBean> linee, int startCounter, LinePropertyBean parent) {
	
		
		LinePropertyBean current = linee.get(startCounter);
	
		if(current.getField().contains("I8DH411-DICHIARATO")){
			int x = 0;
		}
	
		if(parent != null){
			current.parent = parent;
			parent.child.add(current);
			
		}
	
		LinePropertyBean next;
		try{
		next = linee.get(startCounter+1);
		}catch (IndexOutOfBoundsException e) {
			return linee;
		}
		
			
		int currentIndex = Integer.parseInt(current.getIndex());
		int nextIndex =  Integer.parseInt(next.getIndex());
			
				if(currentIndex < nextIndex){
					int childCounter = startCounter+1;
					
					searchForChild(linee,childCounter,current);

					linee.remove(childCounter);
					
				}else if(currentIndex == nextIndex){
					searchForChild(linee,startCounter+1,parent);
					linee.remove(startCounter);
					
				}else if(currentIndex > nextIndex){
					
					//INDIVIDUA il parent dal livello
					LinePropertyBean locParent = getParentFromIndex(current,next,parent);

					searchForChild(linee,startCounter+1,locParent);
					if(next.parent != null){
						linee.remove(startCounter);
					}
				}

		return linee;
	}



	private static LinePropertyBean getParentFromIndex(
			LinePropertyBean current, LinePropertyBean next, LinePropertyBean parent) {
		
		int currentIndex = Integer.parseInt(current.getIndex());
		int nextIndex =  Integer.parseInt(next.getIndex());
	
		LinePropertyBean locParent = Model.getParentLine();
		
		int indexDifference = currentIndex - nextIndex;
		
		switch (indexDifference) {
		case 2:
			locParent = parent.parent;
			break;
			
		case 4:
			locParent = parent.parent.parent;
			break;
			
		case 6:
			locParent = parent.parent.parent.parent;
			break;
			
		case 8:
			locParent = parent.parent.parent.parent.parent;
			break;

		default:
			break;
		}
		
	
	return locParent;
	}



	private static ArrayList<LinePropertyBean> searchForChildTEST(
			ArrayList<LinePropertyBean> linee, int startCounter, LinePropertyBean parent) {
	
		startCounter = 1;
		
		LinePropertyBean current = linee.get(startCounter);
		
//	
//		if(parent != null){
//			current.parent = parent;
//			parent.child.add(current);
//			
//		}
	
		for(int x = 0; x< 3; x++){
		LinePropertyBean next;
		try{
		next = linee.get(startCounter+1);
		}catch (IndexOutOfBoundsException e) {
			return linee;
		}
		
			
		int currentIndex = Integer.parseInt(current.getIndex());
		int nextIndex =  Integer.parseInt(next.getIndex());
			
				if(currentIndex < nextIndex){
					int childCounter = startCounter+1;
					
					next.parent = current;
					current.child.add(next);
					
//					searchForChild(linee,childCounter,current);
					
					linee.remove(childCounter);
					
				}else if(currentIndex > nextIndex){
					return linee;
				}
				
				startCounter++;
		}
	
		return linee;
	}



	public static boolean importFile() throws FileNotFoundException{
	
		
    	String filePath = Model.getFileBean().getFileSelected();
    	File inputFile = new File(filePath);
	

		String fileName = inputFile.getName();
	
		//lettura file selezionato
		FileReader fr = new FileReader(inputFile);
		BufferedReader br = new BufferedReader(fr);
	
		/***** controllo colonne e numeri ***/
		//leggo ogni linea e creo una lista
		LinkedHashMap<Integer,String> lineeNonFormattate = LineUtils.popolaLineeNonFormattate(br);	
		
		//creo una lista di linee con proprietà
		LinkedHashMap<Integer,LinePropertyBean> linePropertyList =	LineUtils.popolaDatiLinee(lineeNonFormattate);
	
		//creo lista ordinata
		LinkedHashMap<Integer,LinePropertyBean> linePropertyListSort = LineUtils.cleanList(linePropertyList);
		
		
		//TRASFORMO IN ARRAYLIST
		ArrayList<LinePropertyBean> linee = new ArrayList<LinePropertyBean>();
		linee.addAll(0,linePropertyListSort.values());
		
		//CREO GERARCHIA FIGLI
		linee = searchForChild(linee,0,null);

		//AGGIORNO IL MODEL
		LinePropertyBean.updateModel(linee);
	
		return true;
	}
	
}
