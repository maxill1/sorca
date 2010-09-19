package com.sorca.views;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

import com.sorca.beans.EditViewColumnCellEditor;
import com.sorca.beans.EditViewContenProvider;
import com.sorca.beans.EditViewLabelProvider;
import com.sorca.handler.ExportFileHandler;
import com.sorca.handler.ImportFileHandler;
import com.sorca.language.Messages;
import com.sorca.model.beans.LinePropertyBean;
import com.sorca.model.beans.Model;
import com.sorca.views.utils.GuiUtils;

public class EditView extends ViewPart implements ISelectionChangedListener,PropertyChangeListener {
	

	private Button separateOutputCheck;
	private Button separateInputCheck;
	private Button process;

	public static final String ID = "CBLFormatter.EditView";

	private TreeViewer v;
	private Label count;
	private Button singleAreaCheck;
	
	public EditView() {
		Model.getFileBean().addPropertyChangeListener(this);
		Model.getSettingsBean().addPropertyChangeListener(this);

	}
	
	private File fileSelection(){
		Shell shell = new Shell();
		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
	    dialog.setFilterNames(new String[] { Messages.fileSelectionNameCBLTXT, Messages.fileSelectionNameAllFiles });
	    dialog.setFilterExtensions(new String[] { Messages.fileSelectionFilterCBLTXT, Messages.fileSelectionFilterALL });

		String selected = dialog.open();
		
	    System.out.println("opening:" + selected);
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
		
		String[] colNames = {Messages.TableIndex,Messages.TableField,Messages.TableOccurs,Messages.TablePicType,Messages.TablePicValue,Messages.TableTotLevel,Messages.TableRedefines};
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
		label.setText(Messages.TableTotArea);
		
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
		GuiUtils.addBindingContext(singleAreaCheck,Model.getFileBean(), "exportSingleArea");
		GuiUtils.addBindingContext(separateInputCheck,Model.getFileBean(), "exportSeparateInputArea");
		GuiUtils.addBindingContext(separateOutputCheck,Model.getFileBean(), "exportSeparateOutputArea");
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
		grpInput.setText(Messages.GroupActions);
	
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
		grpProcess.setText(Messages.GroupExport);

		process = new Button(grpProcess, SWT.NONE );
		GridData btnInputLData = new GridData();
		process.setLayoutData(btnInputLData);
		process.setText(Messages.OpenFolder);
		process.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				try {
					GuiUtils.getHandlerService(EditView.ID)
						.executeCommand(ExportFileHandler.ID, null);
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (NotDefinedException e) {
					e.printStackTrace();
				} catch (NotEnabledException e) {
					e.printStackTrace();
				} catch (NotHandledException e) {
					e.printStackTrace();
				}
			}
		});
		
		singleAreaCheck = new Button(grpProcess, SWT.CHECK);
		GridData btnOutputLData = new GridData();
		singleAreaCheck.setLayoutData(btnOutputLData);
		singleAreaCheck.setText(Messages.LabelSingleArea);
		singleAreaCheck.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent evt) {
				
				boolean multipleEnabled = true;
				
				if(singleAreaCheck.getSelection()){
					multipleEnabled = false;	
				}else{
					multipleEnabled = true;
				}
				
				Model.getFileBean().setExportSeparateInputArea(multipleEnabled);
				Model.getFileBean().setExportSeparateOutputArea(multipleEnabled);
				separateInputCheck.setEnabled(multipleEnabled);
				separateOutputCheck.setEnabled(multipleEnabled);
				Model.getSettingsBean().setPrintFiller(!multipleEnabled);
		
			}
		});

		separateInputCheck = new Button(grpProcess, SWT.CHECK);
		btnOutputLData = new GridData();
		separateInputCheck.setLayoutData(btnOutputLData);
		separateInputCheck.setText(Messages.LabelInputArea);

		separateOutputCheck = new Button(grpProcess, SWT.CHECK );
		GridData btnBothLData = new GridData();
		separateOutputCheck.setLayoutData(btnBothLData);
		separateOutputCheck.setText(Messages.LabelOutputArea);

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
		grpProcess.setText(Messages.Import);
		
		btnApriFile = new Button(grpProcess, SWT.NONE);
		btnApriFile.setText(Messages.OpenFile);
		
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
		btnApriFile.setText(Messages.pasteText);
		
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		System.out.println(evt.getPropertyName() + " changed to: "+evt.getNewValue());
		
		handleAdd2ToIndex(evt);
		
	}

	private void handleAdd2ToIndex(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("add2ToIndex")){
			String msg ="";
			LinePropertyBean line = Model.getParentLine();
			if(evt.getNewValue().equals(true)){
				line.add2ToIndex();
				msg = "ADDING";
			}else{
				line.remove2ToIndex();
				msg = "REMOVING";
			}
			System.out.println(msg+" 2 TO INDEX");
		}	

		 GuiUtils.getEditViewTableViewer().refresh();
		 
		
	}



}
