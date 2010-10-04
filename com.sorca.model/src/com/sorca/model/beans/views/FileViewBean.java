package com.sorca.model.beans.views;

import com.sorca.model.beans.ModelObject;

public class FileViewBean extends ModelObject{
	
	/**
	 * @uml.property  name="fileSelected"
	 */
	private String fileSelected;
	/**
	 * @uml.property  name="folderSelected"
	 */
	private String folderSelected;
	/**
	 * @uml.property  name="count"
	 */
	private String count;
	/**
	 * @uml.property  name="exportSeparateInputArea"
	 */
	private boolean exportSeparateInputArea 	= false;
	/**
	 * @uml.property  name="exportSeparateOutputArea"
	 */
	private boolean exportSeparateOutputArea  	= false;
	/**
	 * @uml.property  name="exportSingleArea"
	 */
	private boolean exportSingleArea  			= false;
	
	/**
	 * @return
	 * @uml.property  name="exportSeparateInputArea"
	 */
	public boolean isExportSeparateInputArea() {
		return exportSeparateInputArea;
	}
	/**
	 * @param exportSeparateInputArea
	 * @uml.property  name="exportSeparateInputArea"
	 */
	public void setExportSeparateInputArea(boolean exportSeparateInputArea) {
		propertyChangeSupport.firePropertyChange("exportSeparateInputArea", this.exportSeparateInputArea,
		this.exportSeparateInputArea = exportSeparateInputArea);
	}
	/**
	 * @return
	 * @uml.property  name="exportSeparateOutputArea"
	 */
	public boolean isExportSeparateOutputArea() {
		return exportSeparateOutputArea;
	}
	/**
	 * @param exportSeparateOutputArea
	 * @uml.property  name="exportSeparateOutputArea"
	 */
	public void setExportSeparateOutputArea(boolean exportSeparateOutputArea) {
		propertyChangeSupport.firePropertyChange("exportSeparateOutputArea", this.exportSeparateOutputArea,
		this.exportSeparateOutputArea = exportSeparateOutputArea);
	}
		
	/**
	 * @param fileSelected
	 * @uml.property  name="fileSelected"
	 */
	public void setFileSelected(String fileSelected) {
		propertyChangeSupport.firePropertyChange("fileSelected", this.fileSelected,
		this.fileSelected = fileSelected);
	}
	/**
	 * @return
	 * @uml.property  name="fileSelected"
	 */
	public String getFileSelected() {
		return fileSelected;
	}
	/**
	 * @param folderSelected
	 * @uml.property  name="folderSelected"
	 */
	public void setFolderSelected(String folderSelected) {
		propertyChangeSupport.firePropertyChange("folderSelected", this.folderSelected,
				this.folderSelected = folderSelected);
	}
	/**
	 * @return
	 * @uml.property  name="folderSelected"
	 */
	public String getFolderSelected() {
		return folderSelected;
	}
	/**
	 * @param count
	 * @uml.property  name="count"
	 */
	public void setCount(String count) {
		propertyChangeSupport.firePropertyChange("count", this.count,
		this.count = count);
	}
	/**
	 * @return
	 * @uml.property  name="count"
	 */
	public String getCount() {
		return count;
	}
	/**
	 * @param exportSingleArea
	 * @uml.property  name="exportSingleArea"
	 */
	public void setExportSingleArea(boolean exportSingleArea) {
		propertyChangeSupport.firePropertyChange("exportSingleArea", this.exportSingleArea,
		this.exportSingleArea = exportSingleArea);
	}
	/**
	 * @return
	 * @uml.property  name="exportSingleArea"
	 */
	public boolean isExportSingleArea() {
		return exportSingleArea;
	}

}
