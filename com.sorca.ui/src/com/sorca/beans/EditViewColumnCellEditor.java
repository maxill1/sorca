package com.sorca.beans;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;

import com.sorca.model.beans.LinePropertyBean;
import com.sorca.views.utils.GuiUtils;
import com.sorca.views.utils.ProcessUtils;

public class EditViewColumnCellEditor extends EditingSupport {
	/**
	 * @uml.property  name="v"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	TreeViewer v;
	/**
	 * @uml.property  name="textCellEditor"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	TextCellEditor textCellEditor;
	/**
	 * @uml.property  name="columnIndex"
	 */
	int columnIndex;
	

		public EditViewColumnCellEditor(TreeViewer v, TextCellEditor textCellEditor,int columnIndex) {
			super(v);
			this.v = v;
			this.textCellEditor =textCellEditor;
			this.columnIndex = columnIndex;
		}

		protected boolean canEdit(Object element) {
			if(columnIndex == 0
					|| columnIndex == 5){
				return false;
			}
			return true;
		}

		protected CellEditor getCellEditor(Object element) {
			return textCellEditor;
		}

		protected Object getValue(Object element) {
			

			LinePropertyBean r = (LinePropertyBean) element;

			switch (columnIndex) {
			case 0:

				return r.getIndex();

			case 1:

				return r.getField();

			case 2:

				return r.getRedefines();
							
			case 3:

				return r.getPicType();

			case 4:
				
				return String.valueOf(r.getPicValue());
				

				
		}




			return "";

		}

		protected void setValue(Object element, Object value) {
			
			try{
			LinePropertyBean r = (LinePropertyBean) element;

			switch (columnIndex) {
			case 0:

				 r.setIndex(value.toString());
				 break;

			case 1:

				 r.setField(value.toString());
				 break;
			case 2:

				r.setRedefines(value.toString());
				 break;
			
			case 3:

				r.setPicType(value.toString());
				 break;
				 
			case 4:
				
				r.setPicValue(Integer.parseInt(value.toString()));
				 break;

				
		}

			v.update(element, null);

		    GuiUtils.getEditViewTableViewer().refresh();
			 
			 ProcessUtils.countFiller();
			
		}catch (NumberFormatException e) {
			GuiUtils.showError("Il valore deve essere numerico");
		}
		
		}
	
	

}
