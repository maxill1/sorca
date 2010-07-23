package com.cblformatter.beans;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.cblformatter.model.beans.LinePropertyBean;
import com.cblformatter.model.beans.Model;

public class EditViewContenProvider implements ITreeContentProvider {

	public Object[] getElements(Object inputElement) {
		return ((ArrayList<LinePropertyBean>) inputElement).toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		if (newInput == null) return;
		if (!(newInput instanceof Model)) return;
		
		Model.setLinee((ArrayList<LinePropertyBean>) newInput);

		
	}

	public Object[] getChildren(Object parentElement) {
		return getElements(parentElement);
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