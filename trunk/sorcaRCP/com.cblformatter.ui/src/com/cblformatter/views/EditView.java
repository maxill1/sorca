package com.cblformatter.views;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.cblformatter.beans.EditViewContenProvider;
import com.cblformatter.beans.EditViewLabelProvider;
import com.cblformatter.model.beans.LinePropertyBean;
import com.cblformatter.model.beans.Model;

public class EditView extends ViewPart implements ISelectionChangedListener  {

	public static final String ID = "CBLFormatter.EditView";

	private TreeViewer v;
	
	public EditView() {

	}

	@Override
	public void createPartControl(Composite parent) {

		
		
//		Button b = new Button(parent,SWT.PUSH);
//		b.setText("Remove column");
		v = new TreeViewer(parent, SWT.BORDER
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
		
		String[] colNames = {"Index","Field","Special","Pic type","Pic Val"};
		
		for(int x= 0; x<colNames.length;x++){
			TreeViewerColumn column = new TreeViewerColumn(v, SWT.NONE);
			column.getColumn().setWidth(200);
			column.getColumn().setMoveable(true);
			column.getColumn().setText(colNames[x]);
			
			column.setEditingSupport(new EditingSupport(v) {
				protected boolean canEdit(Object element) {
					return false;
				}

				protected CellEditor getCellEditor(Object element) {
					return textCellEditor;
				}

				protected Object getValue(Object element) {
					return ((LinePropertyBean) element).counter + "";
				}

				protected void setValue(Object element, Object value) {
					((LinePropertyBean) element).counter = Integer
							.parseInt(value.toString());
					v.update(element, null);
				}
			});
			
		}
	
		
		v.setLabelProvider(new EditViewLabelProvider());
		v.setContentProvider(new EditViewContenProvider());

		v.setInput(LinePropertyBean.createModel());

		v.addSelectionChangedListener(this);

	
	}
	
	public void temp(){
//		TreeViewerColumn column = new TreeViewerColumn(v, SWT.NONE);
//		column.getColumn().setWidth(200);
//		column.getColumn().setMoveable(true);
//		column.getColumn().setText("Index");
//		column.setLabelProvider(new ColumnLabelProvider() {
//
//			public String getText(Object element) {
//				return "Column 1 => " + element.toString();
//			}
//
//		});
//		column.setEditingSupport(new EditingSupport(v) {
//			protected boolean canEdit(Object element) {
//				return false;
//			}
//
//			protected CellEditor getCellEditor(Object element) {
//				return textCellEditor;
//			}
//
//			protected Object getValue(Object element) {
//				return ((LinePropertyBean) element).counter + "";
//			}
//
//			protected void setValue(Object element, Object value) {
//				((LinePropertyBean) element).counter = Integer
//						.parseInt(value.toString());
//				v.update(element, null);
//			}
//		});
//
//		column = new TreeViewerColumn(v, SWT.NONE);
//		column.getColumn().setWidth(200);
//		column.getColumn().setMoveable(true);
//		column.getColumn().setText("Field Name");
//		column.setLabelProvider(new ColumnLabelProvider() {
//
//			public String getText(Object element) {
//				return "Column 2 => " + element.toString();
//			}
//
//		});
//		column.setEditingSupport(new EditingSupport(v) {
//			protected boolean canEdit(Object element) {
//				return true;
//			}
//
//			protected CellEditor getCellEditor(Object element) {
//				return textCellEditor;
//			}
//
//			protected Object getValue(Object element) {
//				return ((LinePropertyBean) element).counter + "";
//			}
//
//			protected void setValue(Object element, Object value) {
//				((LinePropertyBean) element).counter = Integer
//						.parseInt(value.toString());
//				v.update(element, null);
//			}
//		});
//		
//		column = new TreeViewerColumn(v, SWT.NONE);
//		column.getColumn().setWidth(200);
//		column.getColumn().setMoveable(true);
//		column.getColumn().setText("Others");
//		column.setLabelProvider(new ColumnLabelProvider() {
//
//			public String getText(Object element) {
//				return "Column 3 => " + element.toString();
//			}
//
//		});
//		column.setEditingSupport(new EditingSupport(v) {
//			protected boolean canEdit(Object element) {
//				return true;
//			}
//
//			protected CellEditor getCellEditor(Object element) {
//				return textCellEditor;
//			}
//
//			protected Object getValue(Object element) {
//				return ((LinePropertyBean) element).counter + "";
//			}
//
//			protected void setValue(Object element, Object value) {
//				((LinePropertyBean) element).counter = Integer
//						.parseInt(value.toString());
//				v.update(element, null);
//			}
//		});
//		
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


}
