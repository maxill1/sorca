package com.cblformatter.handler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.cblformatter.model.beans.Model;
import com.cblformatter.views.utils.GuiUtils;

public class ExportFileHandler extends AbstractHandler {


	public static final String ID = "SORCA.exportFile";;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if(Model.getFileBean().getFileSelected() == null 
				|| Model.getFileBean().getFileSelected().equals("")){
			GuiUtils.showError("Select an input File first");
			return null;
		}
		
		if((!Model.getFileBean().isExportSeparateInputArea() && !Model.getFileBean().isExportSeparateInputArea()) 
				&& !Model.getFileBean().isExportSingleArea()){
			GuiUtils.showError("Select an export type");
			return null;
		}
		
		
		//Folder selection
		File folderPath = new File(folderSelection());
		if(folderPath == null){
			return null;
		}
		if(folderPath.equals("")){
			GuiUtils.showError("Select A Folder");
			return null;
		}
		Model.getFileBean().setFolderSelected(folderPath.getAbsolutePath());
		
		//Progress
		Shell sh = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		.getShell();

		try {

			new ProgressMonitorDialog(sh).run(true, true, new ExportOperation());

		} catch (InvocationTargetException e) {

			e.printStackTrace();

			GuiUtils.showError(e.getCause().getMessage());

			return null;

		} catch (InterruptedException e) {

			GuiUtils.showError(e.getMessage());
			return null;

		}


		MessageDialog.openInformation(null, 
				"Salvataggio File", "File Salvati con successo su: " + folderPath);
	    System.out.println("File Input Salvato con successo");

		return null;

	}
	
	private String folderSelection(){
		Shell shell = new Shell();
		DirectoryDialog  dialog = new DirectoryDialog (shell, SWT.SAVE);

		String selected = dialog.open();

		System.out.println("Save to: " + selected);
		return selected;
	}
	
}
