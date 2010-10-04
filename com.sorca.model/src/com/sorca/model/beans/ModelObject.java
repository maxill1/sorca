package com.sorca.model.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
public class ModelObject {

	/**
	 * @uml.property  name="propertyChangeSupport"
	 */
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	/**
	 * @uml.property  name="id"
	 */
	private String id;

	/**
	 * @uml.property  name="propertyChangelisteners"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.beans.PropertyChangeListener"
	 */
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

	/**
	 * @param string
	 * @uml.property  name="id"
	 */
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


