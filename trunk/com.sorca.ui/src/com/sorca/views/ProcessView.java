package com.sorca.views;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.part.ViewPart;

import com.sorca.handler.ImportFileHandler;
import com.sorca.model.beans.Model;
import com.sorca.views.utils.GuiUtils;


public class ProcessView extends ViewPart {
	protected static final String ID = "CBLFormatter.ProcessView";;
	/**
	 * @uml.property  name="grpProcess"
	 * @uml.associationEnd  
	 */
	private Group grpProcess;
	/**
	 * @uml.property  name="top"
	 * @uml.associationEnd  
	 */
	private Composite top;
	/**
	 * @uml.property  name="output"
	 * @uml.associationEnd  
	 */
	private Button output;
	/**
	 * @uml.property  name="input"
	 * @uml.associationEnd  
	 */
	private Button input;
	/**
	 * @uml.property  name="process"
	 * @uml.associationEnd  
	 */
	private Button process;

	public ProcessView() {

	}

	@Override
	public void createPartControl(Composite parent) {

		GridLayout parentLayout = new GridLayout();
		parentLayout.makeColumnsEqualWidth = true;
		parent.setLayout(parentLayout);
		top = new Composite(parent, SWT.NONE);
		GridLayout composite1Layout = new GridLayout();
		composite1Layout.makeColumnsEqualWidth = true;
		GridData topLData = new GridData();
		topLData.horizontalAlignment = GridData.CENTER;
		topLData.grabExcessHorizontalSpace = true;
		topLData.grabExcessVerticalSpace = true;
		top.setLayoutData(topLData);
		top.setLayout(composite1Layout);

		grpProcess = new Group(top, SWT.NONE);
		GridLayout grpProcessLayout = new GridLayout();
		grpProcessLayout.numColumns = 3;
		grpProcess.setLayout(grpProcessLayout);
		GridData grpProcessLData = new GridData();
		grpProcessLData.horizontalAlignment = GridData.CENTER;

		grpProcess.setLayoutData(grpProcessLData);
		grpProcess.setText("Genera CBL");

		process = new Button(grpProcess, SWT.NONE );
		GridData btnInputLData = new GridData();
		process.setLayoutData(btnInputLData);
		process.setText("Genera");
		process.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				try {
					GuiUtils.getHandlerService(ProcessView.ID).executeCommand(ImportFileHandler.ID, null);
			
				
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotDefinedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotEnabledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotHandledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				 IViewPart view = PlatformUI.getWorkbench()
//			        .getActiveWorkbenchWindow()
//			        .getActivePage()
//			        .findView("CBLFormatter.FilesView");
//			    if (view instanceof FilesView) {
//	
//			    	String filePath = ((FilesView) view).getFilePath().getText();
//			    	String folderPath = ((FilesView) view).getFolderPath().getText();
//			    	
//			    	if(filePath.equals("none") || folderPath.equals("none")){
//			    		MessageDialog.openError(null, "Errore", "Devi selezionare un file di input e una cartella di destinazione");
//			    	}else{
//
//			    	File fileInput = new File(filePath);
//			    	File fileOutput = new File(folderPath);
//
//				   // CreateOutput.createOutputFile(fileInput,fileOutput,Costants.input);
//				    
//			    	GuiUtils.showInfo("test", SWT.NONE);
//				    PreView dialog = new PreView( PlatformUI.getWorkbench().getDisplay().getActiveShell());
//				    dialog.open();
//				    
//				    System.out.println("File Input Salvato con successo");
//			    	}
//			    }

			}
		});

		input = new Button(grpProcess, SWT.CHECK);
		GridData btnOutputLData = new GridData();
		input.setLayoutData(btnOutputLData);
		input.setText("CBL Input");


		output = new Button(grpProcess, SWT.CHECK );
		GridData btnBothLData = new GridData();
		output.setLayoutData(btnBothLData);
		output.setText("CBL Output");


		
		bindGUI();

	}

	private void bindGUI() {

		GuiUtils.addBindingContext(
				input,Model.getSettingsBean(), "generateInput");
		GuiUtils.addBindingContext(
				output,Model.getSettingsBean(), "generateOutput");
	}

	@Override
	public void setFocus() {


	}

}
