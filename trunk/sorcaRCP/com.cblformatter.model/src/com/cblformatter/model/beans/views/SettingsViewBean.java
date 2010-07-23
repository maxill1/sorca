package com.cblformatter.model.beans.views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.cblformatter.model.beans.ModelObject;

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
	private  boolean headerPresente = false;
	private  boolean fillerPresente = false;
	private boolean generateInput = false;
	private boolean generateOutput = false;
	
	public SettingsViewBean(){
		addPropertyChangeListener(this);
		
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
	public  boolean isFillerPresente() {
		return fillerPresente;
	}
	public  boolean isHandleErrors() {
		return HandleErrors;
	}
	public  boolean isHeaderPresente() {
		return headerPresente;
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
	public  void setFillerPresente(boolean fillerPresente) {
		propertyChangeSupport.firePropertyChange("fillerPresente", this.fillerPresente,
		this.fillerPresente = fillerPresente);
	}
	public  void setHandleErrors(boolean handleErrors) {
		propertyChangeSupport.firePropertyChange("HandleErrors", this.HandleErrors,
		HandleErrors = handleErrors);
	}
	public  void setHeaderPresente(boolean headerPresente) {
		propertyChangeSupport.firePropertyChange("headerPresente", this.headerPresente,
		this.headerPresente = headerPresente);
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
		this.generateInput = generateInput;
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


}
