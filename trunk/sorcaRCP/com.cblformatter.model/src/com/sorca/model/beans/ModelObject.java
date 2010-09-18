package com.sorca.model.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
public class ModelObject {

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	private String id;

	private ArrayList<PropertyChangeListener> propertyChangelisteners = new ArrayList<PropertyChangeListener>();

	public void clear(){
		for(int i=0;i<propertyChangelisteners.size();i++){
			removePropertyChangeListener((PropertyChangeListener)propertyChangelisteners.get(i));
		}
		propertyChangelisteners.clear();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangelisteners.add(listener);
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,newValue);
	}

	protected void firePropertyChange(String propertyName, int oldValue,
			int newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,newValue);
	}

	protected void firePropertyChange(String propertyName, boolean oldValue,
			boolean newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,newValue);
	}

	public void setId(String string) {
		Object oldValue = id;
		id = string;
		firePropertyChange("id", oldValue, id);
	}

	public String toString()
	{
		return id;
	}
}


