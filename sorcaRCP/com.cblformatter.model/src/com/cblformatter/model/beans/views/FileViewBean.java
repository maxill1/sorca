package com.cblformatter.model.beans.views;

import com.cblformatter.model.beans.ModelObject;

public class FileViewBean extends ModelObject{
	
	private String fileSelected;
	private String folderSelected;
	private String count;
	private boolean exportSeparateInputArea 	= false;
	private boolean exportSeparateOutputArea  	= false;
	private boolean exportSingleArea  			= false;
	
	public boolean isExportSeparateInputArea() {
		return exportSeparateInputArea;
	}
	public void setExportSeparateInputArea(boolean exportSeparateInputArea) {
		propertyChangeSupport.firePropertyChange("exportSeparateInputArea", this.exportSeparateInputArea,
		this.exportSeparateInputArea = exportSeparateInputArea);
	}
	public boolean isExportSeparateOutputArea() {
		return exportSeparateOutputArea;
	}
	public void setExportSeparateOutputArea(boolean exportSeparateOutputArea) {
		propertyChangeSupport.firePropertyChange("exportSeparateOutputArea", this.exportSeparateOutputArea,
		this.exportSeparateOutputArea = exportSeparateOutputArea);
	}
		
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
	public void setExportSingleArea(boolean exportSingleArea) {
		propertyChangeSupport.firePropertyChange("exportSingleArea", this.exportSingleArea,
		this.exportSingleArea = exportSingleArea);
	}
	public boolean isExportSingleArea() {
		return exportSingleArea;
	}

}
