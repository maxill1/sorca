package com.cblformatter.views;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import com.cblformatter.model.beans.Model;
import com.cblformatter.views.utils.GuiUtils;


public class FilesView extends ViewPart {
	
	public static final String ID = "CBLFormatter.FilesView";
	
	private Composite top = null;
	private Group grpInput = null;
	private Label lblFile = null;
	private Button btnApriFile = null;
	private Button btnApriFolder = null;
	private Label FolderPath;
	
	private Label FilePath;
	private Label fileSelezionato = null;

	
	public FilesView() {

	}

	@Override
	public void createPartControl(Composite parent) {
        top = new Composite(parent, SWT.NONE);
        top.setLayout(new GridLayout());
        createGruppoFile();
        createGrpDestinazione();
        
        bindGui();
        
	}
	
	private void bindGui() {
		
		GuiUtils.addBindingContext(FilePath,Model.getFileBean(), "fileSelected");
		GuiUtils.addBindingContext(FolderPath,Model.getFileBean(), "folderSelected");
		
		
	}

	private void createGruppoFile() {

		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.verticalAlignment = GridData.CENTER;
		gridData4.horizontalAlignment = GridData.END;
		GridData gridData3 = new GridData();
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.verticalAlignment = GridData.CENTER;
		gridData3.horizontalAlignment = GridData.END;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.BEGINNING;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.CENTER;
		grpInput = new Group(top, SWT.NONE);
		grpInput.setText("Sorgente File");

		grpInput.setLayout(gridLayout);
		grpInput.setLayoutData(gridData);
		lblFile = new Label(grpInput, SWT.NONE);
		GridData lblFileRALData = new GridData();
		lblFileRALData.horizontalAlignment = GridData.END;
		lblFile.setLayoutData(lblFileRALData);
		lblFile.setText("Apri File");
		btnApriFile = new Button(grpInput, SWT.NONE);
		btnApriFile.setText("Seleziona");

	
		fileSelezionato = new Label(grpInput, SWT.NONE);
		fileSelezionato.setText("File:");
		
			FilePath = new Label(grpInput, SWT.NONE);
			GridData FilePathLData = new GridData();
			FilePathLData.horizontalAlignment = GridData.FILL;
			FilePathLData.grabExcessHorizontalSpace = true;
			FilePath.setText("none");
			FilePath.setLayoutData(FilePathLData);
			
		
			btnApriFile.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				File fileSel = fileSelection();
				Model.getFileBean().setFileSelected(fileSel.getPath());
			}
		});
	}

	private File fileSelection(){
			Shell shell = new Shell();
			FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		    dialog.setFilterNames(new String[] { "Cobol Files", "All Files (*.*)" });
		    dialog.setFilterExtensions(new String[] { "*.txt;*.cbl;*.TXT;*.CBL", "*.*" });

			String selected = dialog.open();
			
		    System.out.println("opening: " + selected);
			return new File(selected);
	}
	
	private String folderSelection(){
		Shell shell = new Shell();
		DirectoryDialog  dialog = new DirectoryDialog (shell, SWT.SAVE);
		
		String selected = dialog.open();
		
	    System.out.println("Save to: " + selected);
		return selected;
}
	
	@Override
	public void setFocus() {

	}

	/**
	 * This method initializes grpOutput	
	 *
	 */
	private void createGrpDestinazione() {
		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.verticalAlignment = GridData.CENTER;
		gridData4.horizontalAlignment = GridData.END;
		GridData gridData3 = new GridData();
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.verticalAlignment = GridData.CENTER;
		gridData3.horizontalAlignment = GridData.END;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.BEGINNING;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.CENTER;
		grpInput = new Group(top, SWT.NONE);
		grpInput.setText("Destinazione");

		grpInput.setLayout(gridLayout);
		grpInput.setLayoutData(gridData);
		lblFile = new Label(grpInput, SWT.NONE);
		GridData lblFileLData = new GridData();
		lblFileLData.horizontalAlignment = GridData.END;
		lblFile.setLayoutData(lblFileLData);
		lblFile.setText("Cartella");
		btnApriFolder = new Button(grpInput, SWT.NONE);
		btnApriFolder.setText("Seleziona");
		fileSelezionato = new Label(grpInput, SWT.NONE);
		fileSelezionato.setText("cartella sel:");
		
			FolderPath = new Label(grpInput, SWT.NONE);
			FolderPath.setText("none");
			GridData FolderPathLData = new GridData();
			FolderPathLData.horizontalAlignment = GridData.FILL;
			FolderPathLData.grabExcessHorizontalSpace = true;
			FolderPath.setLayoutData(FolderPathLData);
		
		btnApriFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
			
				String sel = folderSelection();
				Model.getFileBean().setFolderSelected(sel);
				
			}
		});
	}

}
