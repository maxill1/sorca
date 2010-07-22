package com.cblformatter.views;
import java.io.File;


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.cblformatter.handler.CreateOutput;
import com.cblformatter.utils.Costants;


public class ProcessView extends ViewPart {
	private Group grpProcess;
	private Composite top;
	private Button btnBoth;
	private Button btnOutput;
	private Button btnInput;

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

		btnInput = new Button(grpProcess, SWT.NONE );
		GridData btnInputLData = new GridData();
		btnInput.setLayoutData(btnInputLData);
		btnInput.setText("Solo CBL di Input");
		btnInput.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				 IViewPart view = PlatformUI.getWorkbench()
			        .getActiveWorkbenchWindow()
			        .getActivePage()
			        .findView("CBLFormatter.FilesView");
			    if (view instanceof FilesView) {
			    	FilesView libroView = (FilesView) view;
			    	
			    	String filePath = ((FilesView) view).getFilePath().getText();
			    	String folderPath = ((FilesView) view).getFolderPath().getText();
			    	
			    	if(filePath.equals("none") || folderPath.equals("none")){
			    		MessageDialog.openError(null, "Errore", "Devi selezionare un file di input e una cartella di destinazione");
			    	}else{

			    	File fileInput = new File(filePath);
			    	File fileOutput = new File(folderPath);

				   // CreateOutput.createOutputFile(fileInput,fileOutput,Costants.input);
				    
				    PreView.createPreview();
				    
				    System.out.println("File Input Salvato con successo");
			    	}
			    }

			}
		});

		btnOutput = new Button(grpProcess, SWT.NONE);
		GridData btnOutputLData = new GridData();
		btnOutput.setLayoutData(btnOutputLData);
		btnOutput.setText("Solo CBL di Output");
		btnOutput.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				 IViewPart view = PlatformUI.getWorkbench()
			        .getActiveWorkbenchWindow()
			        .getActivePage()
			        .findView("CBLFormatter.FilesView");
			    if (view instanceof FilesView) {
			    	FilesView libroView = (FilesView) view;
			    	
			    	String filePath = ((FilesView) view).getFilePath().getText();
			    	String folderPath = ((FilesView) view).getFolderPath().getText();
			    	
			    	if(filePath.equals("none") || folderPath.equals("none")){
			    		MessageDialog.openError(null, "Errore", "Devi selezionare un file di input e una cartella di destinazione");
			    	}else{

			    	File fileInput = new File(filePath);
			    	File fileOutput = new File(folderPath);

				    CreateOutput.createOutputFile(fileInput,fileOutput,Costants.output);
				    System.out.println("File Output Salvato con successo");
			    	}
			    }

			}
		});


		btnBoth = new Button(grpProcess, SWT.NONE );
		GridData btnBothLData = new GridData();
		btnBoth.setLayoutData(btnBothLData);
		btnBoth.setText("Entrambi");
		btnBoth.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				 IViewPart view = PlatformUI.getWorkbench()
			        .getActiveWorkbenchWindow()
			        .getActivePage()
			        .findView("CBLFormatter.FilesView");
			    if (view instanceof FilesView) {
			    	FilesView libroView = (FilesView) view;
			    	
			    	String filePath = ((FilesView) view).getFilePath().getText();
			    	String folderPath = ((FilesView) view).getFolderPath().getText();
			    	
			    	if(filePath.equals("none") || folderPath.equals("none")){
			    		MessageDialog.openError(null, "Errore", "Devi selezionare un file di input e una cartella di destinazione");
			    	}else{

			    	File fileInput = new File(filePath);
			    	File fileOutput = new File(folderPath);

				    CreateOutput.createOutputFile(fileInput,fileOutput,Costants.input);
				    System.out.println("File Input Salvato con successo");

				    CreateOutput.createOutputFile(fileInput,fileOutput,Costants.output);
				    System.out.println("File Output Salvato con successo");
			    	}
			    }

			}
		});

		


	}

	@Override
	public void setFocus() {


	}

}
