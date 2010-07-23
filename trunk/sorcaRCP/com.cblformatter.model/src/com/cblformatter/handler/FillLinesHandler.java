package com.cblformatter.handler;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;

import com.cblformatter.model.beans.Model;
import com.cblformatter.views.utils.Costants;
import com.cblformatter.views.utils.GuiUtils;

public class FillLinesHandler extends AbstractHandler {

	public static final String ID = "SORCA.fillLines";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
//
//		CreateOutputOperation a = new CreateOutputOperation();


		String filePath = "/opt/workspaces/I8DH471.TXT";
		String folderPath = "/home/luca/Scrivania";
		Model.getSettingsBean().setGenerateInput(true);
		
//    	String filePath = Model.getFileBean().getFileSelected();
//    	String folderPath = Model.getFileBean().getFolderSelected();
    	
    	if(filePath == null || folderPath == null || filePath.equals("none") || folderPath.equals("none")){
    		MessageDialog.openError(null, "Errore", "Devi selezionare un file di input e una cartella di destinazione");
    		return null;
    	}

    	if(!Model.getSettingsBean().isGenerateInput() && !Model.getSettingsBean().isGenerateOutput()){
    		MessageDialog.openError(null, "Errore", "Devi selezionare almeno una tipologia di file cbl da generare");
    		return null;
    	}
    	

    	File fileInput = new File(filePath);
    	File fileOutput = new File(folderPath);
    	
    	if(Model.getSettingsBean().isGenerateInput()){
    	    CreateOutput.createOutputFile(fileInput,fileOutput,Costants.input);    		
    	}    	

    	if(Model.getSettingsBean().isGenerateOutput()){
    	    CreateOutput.createOutputFile(fileInput,fileOutput,Costants.output);    		
    	}    	

//		MessageDialog.openInformation(null, "Salvataggio File", "File Salvati con successo");
//
//	    System.out.println("File Input Salvato con successo");

 
		
	    GuiUtils.getEditViewTableViewer().refresh();
		
		return null;
	}

}
