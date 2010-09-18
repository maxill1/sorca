package com.sorca.handler;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.sorca.model.beans.Model;
import com.sorca.views.utils.GuiUtils;
import com.sorca.views.utils.ProcessUtils;

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
    	

    	//Progress
		Shell sh = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		.getShell();

		try {

			new ProgressMonitorDialog(sh).run(true, true, 
					new ImportOperation());

		} catch (InvocationTargetException e) {

			e.printStackTrace();

			GuiUtils.showError(e.getCause().getMessage());

			return null;

		} catch (InterruptedException e) {

			GuiUtils.showError(e.getMessage());
			return null;

		}
 
		ProcessUtils.countFiller();
	    GuiUtils.getEditViewTableViewer().refresh();
		
		return null;
	}

}
