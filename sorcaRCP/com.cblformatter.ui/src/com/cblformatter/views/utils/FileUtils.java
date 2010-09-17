package com.cblformatter.views.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.cblformatter.model.beans.Model;

public class FileUtils {

	public static void scriviSuFile(File fileOut,String outPutLine) throws IOException{
	
		FileWriter fw = new FileWriter(fileOut);
	
		String fileSalvato = fileOut.getAbsolutePath();
	
		System.out.println(fileSalvato);
	
		fw.write(outPutLine);
		fw.flush();
		fw.close();
	
	}

	public static File creaFileOutput(File outputFile, File inputFile, String type){
		
		String fileName = inputFile.getName();
		String filePath = outputFile.getAbsolutePath()+"/";
		
		fileName = fileName.toLowerCase().replace(".txt","").replace(".cbl","")
		+"_"+type; 
		
		fileName = fileName.toUpperCase()+".cbl";
		
		String fileFormatted = filePath+fileName;	
		outputFile = new File(fileFormatted);
		
		return outputFile;
		
	}

	public static String encodeString(String toPrint) {
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

}
