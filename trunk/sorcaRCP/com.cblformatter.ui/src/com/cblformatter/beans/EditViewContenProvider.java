package com.cblformatter.beans;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.cblformatter.handler.CreateOutput;
import com.cblformatter.model.beans.LinePropertyBean;
import com.cblformatter.model.beans.Model;
import com.cblformatter.views.utils.GuiUtils;

public class EditViewContenProvider implements ITreeContentProvider {

	public Object[] getElements(Object inputElement) {
//		if(((LinePropertyBean) inputElement).parent == null){
			return ((LinePropertyBean)inputElement).child.toArray();
//		}

//			return ((LinePropertyBean) inputElement).child.toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		if (newInput == null) return;
		if (!(newInput instanceof Model)) return;
		
		 GuiUtils.getEditViewTableViewer().refresh();
		 
		 CreateOutput.countFiller();
		
//		Model.getLinee().set(((LinePropertyBean) newInput).getNumRiga(),(LinePropertyBean) newInput);

		
	}

	public Object[] getChildren(Object parentElement) {
		return getElements((LinePropertyBean)parentElement);
	}

	public Object getParent(Object element) {
		if (element == null) {
			return null;
		}
		return ((LinePropertyBean) element).parent;
	}

	public boolean hasChildren(Object element) {
		return ((LinePropertyBean) element).child.size() > 0;
	}

}