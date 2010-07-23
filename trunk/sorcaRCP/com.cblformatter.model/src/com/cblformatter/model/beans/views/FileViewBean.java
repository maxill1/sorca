package com.cblformatter.model.beans.views;

import com.cblformatter.model.beans.ModelObject;

public class FileViewBean extends ModelObject{
	
	private String fileSelected;
	private String folderSelected;
	
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

}
