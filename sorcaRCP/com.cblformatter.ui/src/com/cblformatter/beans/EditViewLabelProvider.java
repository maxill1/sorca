package com.cblformatter.beans;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.cblformatter.model.beans.LinePropertyBean;

public class EditViewLabelProvider implements  ITableLabelProvider  {
	
	
	public String getColumnText(Object element, int columnIndex) {
		LinePropertyBean r = (LinePropertyBean) element;

		switch (columnIndex) {
		case 0:

			return r.getIndex();

		case 1:

			return r.getField();

		case 2:

			return getSpecial((LinePropertyBean)element);
	
		case 3:


			return r.getPicType();
			
		
		case 4:

			return getPicValue(r.getPicValue());

		case 5:
			
			return String.valueOf(r.getChildsPicValue());
			

			
	}




		return "";

	}

	private String getPicValue(int picValue) {

		if(picValue == 0){
			return "";
		}
		
		return String.valueOf(picValue);
	
	}

	private String getSpecial(LinePropertyBean element) {

		if(element.getOccurs() != 0){
			element.setSpecials("OCCURS "+ element.getOccurs());
		}
		
		return element.getSpecials();
	
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	


}
