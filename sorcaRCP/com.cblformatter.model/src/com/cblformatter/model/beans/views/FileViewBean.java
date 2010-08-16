package com.cblformatter.model.beans.views;

import com.cblformatter.model.beans.ModelObject;

public class FileViewBean extends ModelObject{
	
	private String fileSelected;
	private String folderSelected;
	private String count;
	
	public void setFileSelected(String fileSelected) {
		propertyChangeSupport.firePropertyChange("fileSelected", this.fileSelected,
		this.fileSelected = fileSelected);
	}
	public String getFileSelected() {
		return fileSelected;
	}
	public void setFolderSelected(String folderSelected) {
		propertyChangeSupport.firePropertyChange("folderSelected", this.folderSelected,
				this.folderSelected = folderSelected);
	}
	public String getFolderSelected() {
		return folderSelected;
	}
	public void setCount(String count) {
		propertyChangeSupport.firePropertyChange("count", this.count,
		this.count = count);
	}
	public String getCount() {
		return count;
	}

}
