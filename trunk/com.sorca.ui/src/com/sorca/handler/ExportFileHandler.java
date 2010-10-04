package com.sorca.handler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.sorca.language.Messages;
import com.sorca.model.beans.Model;
import com.sorca.ui.SystemTraySupport;
import com.sorca.views.utils.GuiUtils;

public class ExportFileHandler extends AbstractHandler {


	public static final String ID = "SORCA.exportFile";; //$NON-NLS-1$

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if(Model.getFileBean().getFileSelected() == null 
				|| Model.getFileBean().getFileSelected().equals("")){ //$NON-NLS-1$
			GuiUtils.showError(Messages.Check_selectInputFile);
			return null;
		}
		
		if((!Model.getFileBean().isExportSeparateOutputArea() && !Model.getFileBean().isExportSeparateInputArea()) 
				&& !Model.getFileBean().isExportSingleArea()){
			GuiUtils.showError(Messages.Check_selectExportType);
			return null;
		}
		
		
		//Folder selection
		File folderPath = new File(folderSelection());

		if(folderPath.equals("")){ //$NON-NLS-1$
			GuiUtils.showError(Messages.Check_selectFolder);
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


//		MessageDialog.openInformation(null, 
//				"Salvataggio File", "File Salvati con successo su: " + folderPath);

		SystemTraySupport sys = new SystemTraySupport();

		sys.updateTrayMessage(Messages.Diagn_exportDone, Messages.Diagn_exportMessage+ folderPath);
		
	    System.out.println("File Output Salvato con successo"); //$NON-NLS-1$

		return null;

	}
	
	private String folderSelection(){
		Shell shell = new Shell();
		DirectoryDialog  dialog = new DirectoryDialog (shell, SWT.SAVE);

		String selected = dialog.open();

		System.out.println("Save to: " + selected); //$NON-NLS-1$
		return selected;
	}
	
}
