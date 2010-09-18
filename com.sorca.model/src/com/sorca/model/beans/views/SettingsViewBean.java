package com.sorca.model.beans.views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.sorca.model.beans.ModelObject;

public class SettingsViewBean extends ModelObject{

	private  String count = "32084"; 
	private  String indexSpaces = "9";
	private  String picSpaces = "58";
	private  boolean add2ToIndex = true;
	private  String programCall = "I8D";
	private  String programCallAlt = "FI8";
	private  String codifica = "UTF-8";
	private  String EOL = "\r\n";
	private  boolean HandleErrors = true;
	private  boolean HandleRedefines= true;
	private  boolean printHeader = true;
	private  boolean printFiller = true;
	private boolean generateInput = false;
	private boolean generateOutput = false;
	private boolean viewInnested = true;
	
	public SettingsViewBean(){	
	}

	public  String getCodifica() {
		return codifica;
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
	public  String getProgramCall() {
		return programCall;
	}
	public  String getProgramCallAlt() {
		return programCallAlt;
	}
	public  boolean isAdd2ToIndex() {
		return add2ToIndex;
	}
	public  boolean isPrintFiller() {
		return printFiller;
	}
	public  boolean isHandleErrors() {
		return HandleErrors;
	}
	public  boolean isPrintHeader() {
		return printHeader;
	}
	public  void setAdd2ToIndex(boolean add2ToIndex) {
		propertyChangeSupport.firePropertyChange("add2ToIndex", this.add2ToIndex,
				this.add2ToIndex = add2ToIndex);
	}
	public  void setCodifica(String codifica) {
		propertyChangeSupport.firePropertyChange("codifica", this.codifica,
				this.codifica = codifica);

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
		propertyChangeSupport.firePropertyChange("HandleErrors", this.HandleErrors,
		HandleErrors = handleErrors);
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
	public  void setProgramCall(String programCall) {
		propertyChangeSupport.firePropertyChange("programCall", this.programCall,
		this.programCall = programCall);
	}
	public  void setProgramCallAlt(String programCallAlt) {
		propertyChangeSupport.firePropertyChange("programCallAlt", this.programCallAlt,
		this.programCallAlt = programCallAlt);
	}


	public void setHandleRedefines(boolean handleRedefines) {
		propertyChangeSupport.firePropertyChange("handleRedefines", this.HandleRedefines,
		this.HandleRedefines = handleRedefines);
	}


	public boolean isHandleRedefines() {
		return HandleRedefines;
	}


	public void setGenerateInput(boolean generateInput) {
		propertyChangeSupport.firePropertyChange("generateInput", this.generateInput,
				this.generateInput = generateInput);
	}


	public boolean isGenerateInput() {
		return generateInput;
	}


	public void setGenerateOutput(boolean generateOutput) {
		this.generateOutput = generateOutput;
	}


	public boolean isGenerateOutput() {
		return generateOutput;
	}


	public void setViewInnested(boolean viewInnested) {
		propertyChangeSupport.firePropertyChange("viewInnested", this.viewInnested,
				this.viewInnested = viewInnested);
	}


	public boolean isViewInnested() {
		return viewInnested;
	}

	
}
