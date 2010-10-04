package com.sorca.model.beans.views;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.sorca.model.beans.ModelObject;
import com.sorca.utils.CoreUtils;
import com.sorca.utils.PlatformUtils;

public class SettingsViewBean extends ModelObject{

	/**
	 * @uml.property  name="count"
	 */
	private  String count; 
	/**
	 * @uml.property  name="indexSpaces"
	 */
	private  String indexSpaces;
	/**
	 * @uml.property  name="picSpaces"
	 */
	private  String picSpaces;
	/**
	 * @uml.property  name="add2ToIndex"
	 */
	private  boolean add2ToIndex;
	/**
	 * @uml.property  name="encoding"
	 */
	private  String encoding;
	/**
	 * @uml.property  name="eOL"
	 */
	private  String EOL;
	/**
	 * @uml.property  name="handleErrors"
	 */
	private  boolean handleErrors;
	/**
	 * @uml.property  name="handleRedefines"
	 */
	private  boolean handleRedefines;
	/**
	 * @uml.property  name="printHeader"
	 */
	private  boolean printHeader ;
	/**
	 * @uml.property  name="printFiller"
	 */
	private  boolean printFiller;
	/**
	 * @uml.property  name="generateInput"
	 */
	private boolean generateInput;
	/**
	 * @uml.property  name="generateOutput"
	 */
	private boolean generateOutput;
	/**
	 * @uml.property  name="autoUpdate"
	 */
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

	/**
	 * @return
	 * @uml.property  name="encoding"
	 */
	public  String getEncoding() {
		return encoding;
	}
	/**
	 * @return
	 * @uml.property  name="count"
	 */
	public  String getCount() {
		return count;
	}
	/**
	 * @return
	 * @uml.property  name="eOL"
	 */
	public  String getEOL() {
		return EOL;
	}
	/**
	 * @return
	 * @uml.property  name="indexSpaces"
	 */
	public  String getIndexSpaces() {
		return indexSpaces;
	}
	/**
	 * @return
	 * @uml.property  name="picSpaces"
	 */
	public  String getPicSpaces() {
		return picSpaces;
	}

	/**
	 * @return
	 * @uml.property  name="add2ToIndex"
	 */
	public  boolean isAdd2ToIndex() {
		return add2ToIndex;
	}
	/**
	 * @return
	 * @uml.property  name="printFiller"
	 */
	public  boolean isPrintFiller() {
		return printFiller;
	}
	/**
	 * @return
	 * @uml.property  name="handleErrors"
	 */
	public  boolean isHandleErrors() {
		return handleErrors;
	}
	/**
	 * @return
	 * @uml.property  name="printHeader"
	 */
	public  boolean isPrintHeader() {
		return printHeader;
	}
	/**
	 * @param add2ToIndex
	 * @uml.property  name="add2ToIndex"
	 */
	public  void setAdd2ToIndex(boolean add2ToIndex) {
		propertyChangeSupport.firePropertyChange("add2ToIndex", this.add2ToIndex,
				this.add2ToIndex = add2ToIndex);
	}
	/**
	 * @param codifica
	 * @uml.property  name="encoding"
	 */
	public  void setEncoding(String codifica) {
		propertyChangeSupport.firePropertyChange("codifica", this.encoding,
				this.encoding = codifica);

	}
	/**
	 * @param count
	 * @uml.property  name="count"
	 */
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
	/**
	 * @param eOL
	 * @uml.property  name="eOL"
	 */
	public  void setEOL(String eOL) {
		propertyChangeSupport.firePropertyChange("EOL", this.EOL,
		EOL = eOL);
	}
	/**
	 * @param PrintFiller
	 * @uml.property  name="printFiller"
	 */
	public  void setPrintFiller(boolean PrintFiller) {
		propertyChangeSupport.firePropertyChange("PrintFiller", this.printFiller,
		this.printFiller = PrintFiller);
	}
	public  void setHandleErrors(boolean handleErrors) {
		propertyChangeSupport.firePropertyChange("HandleErrors", this.handleErrors,
		handleErrors = handleErrors);
	}
	/**
	 * @param PrintHeader
	 * @uml.property  name="printHeader"
	 */
	public  void setPrintHeader(boolean PrintHeader) {
		propertyChangeSupport.firePropertyChange("PrintHeader", this.printHeader,
		this.printHeader = PrintHeader);
	}
	/**
	 * @param indexSpaces
	 * @uml.property  name="indexSpaces"
	 */
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
	/**
	 * @param picSpaces
	 * @uml.property  name="picSpaces"
	 */
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


	/**
	 * @param handleRedefines
	 * @uml.property  name="handleRedefines"
	 */
	public void setHandleRedefines(boolean handleRedefines) {
		propertyChangeSupport.firePropertyChange("handleRedefines", this.handleRedefines,
		this.handleRedefines = handleRedefines);
	}


	/**
	 * @return
	 * @uml.property  name="handleRedefines"
	 */
	public boolean isHandleRedefines() {
		return handleRedefines;
	}


	/**
	 * @param generateInput
	 * @uml.property  name="generateInput"
	 */
	public void setGenerateInput(boolean generateInput) {
		propertyChangeSupport.firePropertyChange("generateInput", this.generateInput,
				this.generateInput = generateInput);
	}


	/**
	 * @return
	 * @uml.property  name="generateInput"
	 */
	public boolean isGenerateInput() {
		return generateInput;
	}


	/**
	 * @param generateOutput
	 * @uml.property  name="generateOutput"
	 */
	public void setGenerateOutput(boolean generateOutput) {
		propertyChangeSupport.firePropertyChange("generateOutput", this.generateOutput,
		this.generateOutput = generateOutput);
	}


	/**
	 * @return
	 * @uml.property  name="generateOutput"
	 */
	public boolean isGenerateOutput() {
		return generateOutput;
	}

	/**
	 * @param autoUpdate
	 * @uml.property  name="autoUpdate"
	 */
	public void setAutoUpdate(boolean autoUpdate) {
		propertyChangeSupport.firePropertyChange("autoUpdate", this.autoUpdate,
		this.autoUpdate = autoUpdate);
	}

	/**
	 * @return
	 * @uml.property  name="autoUpdate"
	 */
	public boolean isAutoUpdate() {
		return autoUpdate;
	}

	
}
