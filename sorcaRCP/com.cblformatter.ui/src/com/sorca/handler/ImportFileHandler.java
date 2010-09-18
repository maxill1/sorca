package com.cblformatter.handler;

import java.io.FileNotFoundException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.cblformatter.model.beans.Model;
import com.cblformatter.views.utils.GuiUtils;

public class ImportFileHandler extends AbstractHandler {

	public static final String ID = "SORCA.importFile";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		
    	String filePath = Model.getFileBean().getFileSelected();;
    	
    	if(filePath == null){
    		return null;
    	}
    	

//    	if(filePath == null || folderPath == null || filePath.equals("none") || folderPath.equals("none")){
//    		MessageDialog.openError(null, "Errore", "Devi selezionare un file di input e una cartella di destinazione");
//    		return null;
//    	}
//
//    	if(!Model.getSettingsBean().isGenerateInput() && !Model.getSettingsBean().isGenerateOutput()){
//    		MessageDialog.openError(null, "Errore", "Devi selezionare almeno una tipologia di file cbl da generare");
//    		return null;
//    	}
    	

//    	File fileInput = new File(filePath);
//    	File fileOutput = new File(folderPath);
    	
//    	if(Model.getSettingsBean().isGenerateInput()){
//    	    CreateOutput.createOutputFile(fileInput,fileOutput,Costants.input);    		
//    	}    	
//
//    	if(Model.getSettingsBean().isGenerateOutput()){
//    	    CreateOutput.createOutputFile(fileInput,fileOutput,Costants.output);    		
//    	}    	
    	

		

		try {
			
			CreateOutput.importFile();
			CreateOutput.countFiller();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

//		MessageDialog.openInformation(null, "Salvataggio File", "File Salvati con successo");
//
//	    System.out.println("File Input Salvato con successo");

 
		
	    GuiUtils.getEditViewTableViewer().refresh();
		
		return null;
	}

}
