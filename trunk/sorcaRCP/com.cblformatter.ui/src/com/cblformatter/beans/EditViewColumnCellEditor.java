package com.cblformatter.beans;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;

import com.cblformatter.model.beans.LinePropertyBean;
import com.cblformatter.views.utils.GuiUtils;

public class EditViewColumnCellEditor extends EditingSupport {
	TreeViewer v;
	TextCellEditor textCellEditor;
	int columnIndex;
	

		public EditViewColumnCellEditor(TreeViewer v, TextCellEditor textCellEditor,int columnIndex) {
			super(v);
			this.v = v;
			this.textCellEditor =textCellEditor;
			this.columnIndex = columnIndex;
		}

		protected boolean canEdit(Object element) {
			if(columnIndex == 5){
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

				return r.getSpecials();
							
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

				r.setSpecials(value.toString());
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
			
		}catch (NumberFormatException e) {
			GuiUtils.showError("Il valore deve essere numerico");
		}
		
		}
	
	

}
