package com.cblformatter.views;


import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import com.cblformatter.beans.EditViewColumnCellEditor;
import com.cblformatter.beans.EditViewContenProvider;
import com.cblformatter.beans.EditViewLabelProvider;
import com.cblformatter.handler.ExportFileHandler;
import com.cblformatter.handler.ImportFileHandler;
import com.cblformatter.model.beans.LinePropertyBean;
import com.cblformatter.model.beans.Model;
import com.cblformatter.views.utils.GuiUtils;

public class EditView extends ViewPart implements ISelectionChangedListener {
	

	private Button output;
	private Button input;
	private Button process;

	public static final String ID = "CBLFormatter.EditView";

	private TreeViewer v;
	private Label count;
	
	public EditView() {

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

	@Override
	public void createPartControl(Composite parent) {
		
	       parent.setLayout(new GridLayout());
		
		   createGruppoAzioni(parent);
		   
		   Composite tableContainer = new Composite(parent, SWT.NONE);
		   tableContainer.setLayout(new FillLayout());
		   tableContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		
		
//		Button b = new Button(parent,SWT.PUSH);
//		b.setText("Remove column");
		v = new TreeViewer(tableContainer, SWT.BORDER
				| SWT.FULL_SELECTION);
		
//		b.addSelectionListener(new SelectionListener() {
//
//			public void widgetDefaultSelected(SelectionEvent e) {
//				
//			}
//
//			public void widgetSelected(SelectionEvent e) {
//				v.getTree().getColumn(1).dispose();
//			}
//			
//		});
		
		v.getTree().setLinesVisible(true);
		v.getTree().setHeaderVisible(true);

				 
		TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(v,new FocusCellOwnerDrawHighlighter(v));
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(v) {
			protected boolean isEditorActivationEvent(
					ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
						|| event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
						|| (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR)
						|| event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
			}
		};
		
		TreeViewerEditor.create(v, focusCellManager, actSupport, ColumnViewerEditor.TABBING_HORIZONTAL
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);
		
		final TextCellEditor textCellEditor = new TextCellEditor(v.getTree());
		
		String[] colNames = {"Index","Field","OCC","P.type","P.Val","Totale livello","REDEFINES"};
		int[] colSize = {100,180,50,50,50,80,100};
		
		for(int x= 0; x<colNames.length;x++){
			TreeViewerColumn column = new TreeViewerColumn(v, SWT.NONE);
			column.getColumn().setWidth(colSize[x]);
			column.getColumn().setMoveable(true);
			column.getColumn().setText(colNames[x]);
			
			column.setEditingSupport(new EditViewColumnCellEditor(v,textCellEditor,x));
			
			
		}
	
		
		v.setLabelProvider(new EditViewLabelProvider());
		v.setContentProvider(new EditViewContenProvider());

		v.setInput(LinePropertyBean.updateModel(new ArrayList<LinePropertyBean>()));

		v.addSelectionChangedListener(this);

		Composite composite = new Composite(parent, SWT.BORDER);

		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		
		Label label = new Label(composite,SWT.NONE);
		label.setText("Tot Area");
		
		count = new Label(composite,SWT.RIGHT);
		count.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		bindGui();
	}


	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		
		
	}
	
	public TreeViewer getTv(){
		return v;
	}

	private void bindGui() {
		GuiUtils.addBindingContext(count,Model.getFileBean(), "count");
	}
	
	private void createGruppoAzioni(Composite top) {

		Group grpInput = null;
		
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
		grpInput.setText("Azioni");
	
		grpInput.setLayout(gridLayout);
		grpInput.setLayoutData(gridData);
		
		createGruppoImport(grpInput);
	
		createGruppoExport(grpInput);
		
		
	
		
	}

	private void createGruppoExport(Group grpInput) {


		Composite top = new Composite(grpInput, SWT.NONE);
		GridLayout composite1Layout = new GridLayout();
		composite1Layout.makeColumnsEqualWidth = true;
		GridData topLData = new GridData();
		topLData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
		topLData.grabExcessHorizontalSpace = true;
		topLData.grabExcessVerticalSpace = true;
		top.setLayoutData(topLData);
		top.setLayout(composite1Layout);

		Group grpProcess = new Group(top, SWT.NONE);
		GridLayout grpProcessLayout = new GridLayout();
		grpProcessLayout.numColumns = 3;
		grpProcess.setLayout(grpProcessLayout);
		GridData grpProcessLData = new GridData();
		grpProcessLData.horizontalAlignment = GridData.CENTER;

		grpProcess.setLayoutData(grpProcessLData);
		grpProcess.setText("Esporta");

		process = new Button(grpProcess, SWT.NONE );
		GridData btnInputLData = new GridData();
		process.setLayoutData(btnInputLData);
		process.setText("Apri cartella");
		process.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				try {
					GuiUtils.getHandlerService(EditView.ID).executeCommand(ExportFileHandler.ID, null);
			
				
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



	}

	private void createGruppoImport(Group grpInput) {
		Button btnApriFile = null;
		
		Composite top = new Composite(grpInput, SWT.NONE);
		GridLayout composite1Layout = new GridLayout();
		composite1Layout.makeColumnsEqualWidth = true;
		GridData topLData = new GridData();
		topLData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
		topLData.grabExcessHorizontalSpace = true;
		topLData.grabExcessVerticalSpace = true;
		top.setLayoutData(topLData);
		top.setLayout(composite1Layout);
	
		Group grpProcess = new Group(top, SWT.NONE);
		GridLayout grpProcessLayout = new GridLayout();
		grpProcessLayout.numColumns = 3;
		grpProcess.setLayout(grpProcessLayout);
		GridData grpProcessLData = new GridData();
		grpProcessLData.horizontalAlignment = GridData.CENTER;
	
		grpProcess.setLayoutData(grpProcessLData);
		grpProcess.setText("Importa");
		
		btnApriFile = new Button(grpProcess, SWT.NONE);
		btnApriFile.setText("Apri File");
		
		btnApriFile.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				File fileSel = fileSelection();
				Model.getFileBean().setFileSelected(fileSel.getPath());
				
				try {
					GuiUtils.getHandlerService(EditView.ID).executeCommand(ImportFileHandler.ID, null);
				} catch (ExecutionException e) {
					
				} catch (NotDefinedException e) {
				
					e.printStackTrace();
				} catch (NotEnabledException e) {
					
					e.printStackTrace();
				} catch (NotHandledException e) {
			
					e.printStackTrace();
				}
				
				
			}
		});
		
		
		
		
		btnApriFile = new Button(grpProcess, SWT.NONE);
		btnApriFile.setText("Incolla testo");
		
		btnApriFile.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
	
				TextDialog d = new TextDialog(new Shell());
				d.open();
		
				File fileSel = d.getFileTMP();
				fileSel.deleteOnExit();
	
				Model.getFileBean().setFileSelected(fileSel.getPath());
				
				try {
					GuiUtils.getHandlerService(EditView.ID).executeCommand(ImportFileHandler.ID, null);
				} catch (ExecutionException e) {
					
				} catch (NotDefinedException e) {
				
					e.printStackTrace();
				} catch (NotEnabledException e) {
					
					e.printStackTrace();
				} catch (NotHandledException e) {
			
					e.printStackTrace();
				}
				
	
				
			}
		});
	}



}
