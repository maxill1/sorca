package com.sorca.model.beans.views;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.sorca.model.beans.ModelObject;
import com.sorca.utils.CoreUtils;
import com.sorca.utils.PlatformUtils;

public class SettingsViewBean extends ModelObject{

	private  String count; 
	private  String indexSpaces;
	private  String picSpaces;
	private  boolean add2ToIndex;
	private  String encoding;
	private  String EOL;
	private  boolean handleErrors;
	private  boolean handleRedefines;
	private  boolean printHeader ;
	private  boolean printFiller;
	private boolean generateInput;
	private boolean generateOutput;
	private boolean autoUpdate;
	
	public SettingsViewBean(){	
		
		EOL = PlatformUtils.getEOL();
		encoding = PlatformUtils.getEncoding();
		count = System.getProperty("sorca.count");; 
		indexSpaces = System.getProperty("sorca.indexSpaces");
		picSpaces = System.getProperty("sorca.picSpaces");
		add2ToIndex = Boolean.valueOf(System.getProperty("sorca.add2ToIndex"));
		handleErrors = Boolean.valueOf(System.getProperty("sorca.handleErrors"));
		handleRedefines=  Boolean.valueOf(System.getProperty("sorca.handleRedefines"));
		printHeader =  Boolean.valueOf(System.getProperty("sorca.printHeader"));
		printFiller =  Boolean.valueOf(System.getProperty("sorca.printFiller"));
		generateInput = Boolean.valueOf(System.getProperty("sorca.generateInput"));
		generateOutput = Boolean.valueOf(System.getProperty("sorca.generateOuput"));
		autoUpdate = Boolean.valueOf(System.getProperty("sorca.update"));
		
	}

	public  String getEncoding() {
		return encoding;
	}
	public  String getCount() {
		return count;
	}
	public  String getEOL() {
		return EOL;
	}
	public  String getIndexSpaces() {
		return indexSpaces;
	}
	public  String getPicSpaces() {
		return picSpaces;
	}

	public  boolean isAdd2ToIndex() {
		return add2ToIndex;
	}
	public  boolean isPrintFiller() {
		return printFiller;
	}
	public  boolean isHandleErrors() {
		return handleErrors;
	}
	public  boolean isPrintHeader() {
		return printHeader;
	}
	public  void setAdd2ToIndex(boolean add2ToIndex) {
		propertyChangeSupport.firePropertyChange("add2ToIndex", this.add2ToIndex,
				this.add2ToIndex = add2ToIndex);
	}
	public  void setEncoding(String codifica) {
		propertyChangeSupport.firePropertyChange("codifica", this.encoding,
				this.encoding = codifica);

	}
	public  void setCount(String count) {
		
		
		try {
				Integer.parseInt(count);

		} catch (NumberFormatException e) {
			MessageDialog.openError(new Shell(), "Errore", "Non sono permessi caratteri, solo numeri");
		
			return;
		}
		
		propertyChangeSupport.firePropertyChange("count", this.count,
		this.count = count);
	}
	public  void setEOL(String eOL) {
		propertyChangeSupport.firePropertyChange("EOL", this.EOL,
		EOL = eOL);
	}
	public  void setPrintFiller(boolean PrintFiller) {
		propertyChangeSupport.firePropertyChange("PrintFiller", this.printFiller,
		this.printFiller = PrintFiller);
	}
	public  void setHandleErrors(boolean handleErrors) {
		propertyChangeSupport.firePropertyChange("HandleErrors", this.handleErrors,
		handleErrors = handleErrors);
	}
	public  void setPrintHeader(boolean PrintHeader) {
		propertyChangeSupport.firePropertyChange("PrintHeader", this.printHeader,
		this.printHeader = PrintHeader);
	}
	public  void setIndexSpaces(String indexSpaces) {
		
		try {
				Integer.parseInt(indexSpaces);

		} catch (NumberFormatException e) {
			MessageDialog.openError(new Shell(), "Errore", "Non sono permessi caratteri, solo numeri");
			return;
		}
		
		propertyChangeSupport.firePropertyChange("indexSpaces", this.indexSpaces,
		this.indexSpaces = indexSpaces);
	}
	public  void setPicSpaces(String picSpaces) {
		
		try {
				Integer.parseInt(picSpaces);

		} catch (NumberFormatException e) {
			MessageDialog.openError(new Shell(), "Errore", "Non sono permessi caratteri, solo numeri");
			return;
		}
		
		propertyChangeSupport.firePropertyChange("picSpaces", this.picSpaces,
		this.picSpaces = picSpaces);
	}


	public void setHandleRedefines(boolean handleRedefines) {
		propertyChangeSupport.firePropertyChange("handleRedefines", this.handleRedefines,
		this.handleRedefines = handleRedefines);
	}


	public boolean isHandleRedefines() {
		return handleRedefines;
	}


	public void setGenerateInput(boolean generateInput) {
		propertyChangeSupport.firePropertyChange("generateInput", this.generateInput,
				this.generateInput = generateInput);
	}


	public boolean isGenerateInput() {
		return generateInput;
	}


	public void setGenerateOutput(boolean generateOutput) {
		propertyChangeSupport.firePropertyChange("generateOutput", this.generateOutput,
		this.generateOutput = generateOutput);
	}


	public boolean isGenerateOutput() {
		return generateOutput;
	}

	public void setAutoUpdate(boolean autoUpdate) {
		propertyChangeSupport.firePropertyChange("autoUpdate", this.autoUpdate,
		this.autoUpdate = autoUpdate);
	}

	public boolean isAutoUpdate() {
		return autoUpdate;
	}

	
}
