package com.sorca.handler;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.sorca.language.Messages;
import com.sorca.model.beans.Model;
import com.sorca.ui.SystemTraySupport;
import com.sorca.views.utils.GuiUtils;
import com.sorca.views.utils.ProcessUtils;

public class ImportFileHandler extends AbstractHandler {

	public static final String ID = "SORCA.importFile"; //$NON-NLS-1$

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		
    	String filePath = Model.getFileBean().getFileSelected();;
    	
    	if(filePath == null){
    		return null;
    	}
    	
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
		
	    
		
		SystemTraySupport sys = new SystemTraySupport();

		sys.updateTrayMessage(Messages.Diagn_importDone, Messages.Diagn_importMessagePart1+ filePath + Messages.Diagn_importMessagePart2);
	    
		return null;
	}

}
