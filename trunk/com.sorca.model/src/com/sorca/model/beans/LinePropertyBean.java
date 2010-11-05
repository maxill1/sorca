package com.sorca.model.beans;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;

import com.sorca.utils.Convert;
import com.sorca.utils.Index;
import com.sorca.utils.LineUtils;
import com.sorca.utils.Pic;

public class LinePropertyBean extends ModelObject{
	
	//MODELINIT
	/**
	 * @uml.property  name="parent"
	 * @uml.associationEnd  readOnly="true"
	 */
	public LinePropertyBean parent;

	/**
	 * @uml.property  name="child"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.sorca.model.beans.LinePropertyBean"
	 */
	public ArrayList<LinePropertyBean> child = new ArrayList<LinePropertyBean>();

	/**
	 * This prepare a line with all the info contained in the line
	 * @param line the line object containing values
	 * @return a String ready to output file. 
	 * ex: 02 FIELD OCCOURS 1 PIC X(01).
	 */

	public String toString() {

		String print = "";
		String EOL = Convert.decodeEOL();
		String indice="";
		String campo="";
		String redefines = "";
		String PIC="";
		int virtualFloat = 0; 
		int value = 0;
		int occurs = 0;	
		int dichCount = 0;
		
		if(!getIndex().equals("") && !getField().equals("")){
			
		try{

		indice = getIndex();
		campo = getField();
		PIC = getPicType();
		value = getPicValue();
		virtualFloat = getVirtualFloat();
		redefines = getRedefines();
		occurs = getOccurs();
					
//Spostato l'incremento in importazione
//		if(!indice.equals("") && !LineUtils.isHeader(campo)){
//			indice = Index.increaseIndex(Integer.parseInt(indice));
//		}

		} catch (NumberFormatException e) {
			MessageDialog.openError(null, "Errore","Si è verificato un errore \n"+
			"Controlla che non ci siano commenti non asteriscati sul campo: " + campo);
			e.printStackTrace();
		}
		

			if (occurs != 0){
				String occursStr = "       OCCURS "+occurs;
				campo = campo + occursStr;
			}
		
		if (hasRedefines()){
			redefines = "       REDEFINES "+redefines;
			campo = campo + redefines;
		}

		

	
		dichCount = getDichCount();
		}
		
		if(dichCount != 0){
			print = "";
		}else if (!campo.equals("")){
			
		indice = Index.addIndexSpaces(indice);
		

		print = indice 
				+ " " 
				+ campo;
		
		if(!PIC.equals("")){
			print = print + Pic.addPicSpaces(campo,indice)
					+ PIC
					+ Convert.covertPicValueToPrint(LineUtils.formatNumber(value))
					+ Convert.covertFloatValueToPrint(LineUtils.formatNumber(virtualFloat));
		}
		
		print = print
				+ "."
				+EOL;
		
		}
		
		return print;
	}

	
	public static LinePropertyBean createModel() {
		
		LinePropertyBean root = Model.getParentLine();
		root.setIndex("00");
		root.setField("PARENT");

		return root;
	}
	
	
	public static LinePropertyBean updateModel(ArrayList<LinePropertyBean> child) {

		LinePropertyBean root = Model.getParentLine();
		root.child = child;
		
		return root;
}
	
	//END


	public LinePropertyBean() {

	}

	/**
	 * @uml.property  name="index"
	 */
	private String index;
	/**
	 * @uml.property  name="field"
	 */
	private String field;
	/**
	 * @uml.property  name="picType"
	 */
	private String picType;
	/**
	 * @uml.property  name="picValue"
	 */
	private int picValue;
	/**
	 * @uml.property  name="dichCount"
	 */
	private int dichCount;
	/**
	 * @uml.property  name="occurs"
	 */
	private int occurs = 1;
	/**
	 * @uml.property  name="fullLine"
	 */
	private String fullLine;
	/**
	 * @uml.property  name="redefines"
	 */
	private String redefines;
	/**
	 * @uml.property  name="childsPicValue"
	 */
	private int childsPicValue;

	/**
	 * @uml.property  name="numRiga"
	 */
	private int numRiga;

	/**
	 * @uml.property  name="virtualFloat"
	 */
	private int virtualFloat;
	
	/**
	 * @return
	 * @uml.property  name="virtualFloat"
	 */
	public int getVirtualFloat() {
		return virtualFloat;
	}


	/**
	 * @param virtualFloat
	 * @uml.property  name="virtualFloat"
	 */
	public void setVirtualFloat(int virtualFloat) {
		this.virtualFloat = virtualFloat;
	}


	/**
	 * @return
	 * @uml.property  name="index"
	 */
	public String getIndex() {
		return index;
	}
	/**
	 * @param index
	 * @uml.property  name="index"
	 */
	public void setIndex(String index) {
		propertyChangeSupport.firePropertyChange("index", this.index,
				this.index = index);
	}
	/**
	 * @return
	 * @uml.property  name="field"
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field
	 * @uml.property  name="field"
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * @return
	 * @uml.property  name="picType"
	 */
	public String getPicType() {
		return picType;
	}
	/**
	 * @param picType
	 * @uml.property  name="picType"
	 */
	public void setPicType(String picType) {
		this.picType = picType;
	}
	/**
	 * @return
	 * @uml.property  name="picValue"
	 */
	public int getPicValue() {
		return picValue;
	}
	/**
	 * @param picValue
	 * @uml.property  name="picValue"
	 */
	public void setPicValue(int picValue) {
		this.picValue = picValue;
	}
	/**
	 * @param occurs
	 * @uml.property  name="occurs"
	 */
	public void setOccurs(int occurs) {
		this.occurs = occurs;
	}
	/**
	 * @return
	 * @uml.property  name="occurs"
	 */
	public int getOccurs() {
		return occurs;
	}
	/**
	 * @param dichCount
	 * @uml.property  name="dichCount"
	 */
	public void setDichCount(int dichCount) {
		this.dichCount = dichCount;
	}
	/**
	 * @return
	 * @uml.property  name="dichCount"
	 */
	public int getDichCount() {
		return dichCount;
	}
	/**
	 * @param fullLine
	 * @uml.property  name="fullLine"
	 */
	public void setFullLine(String fullLine) {
		this.fullLine = fullLine;
	}
	/**
	 * @return
	 * @uml.property  name="fullLine"
	 */
	public String getFullLine() {
		return fullLine;
	}
	/**
	 * @return
	 * @uml.property  name="redefines"
	 */
	public String getRedefines() {
	
		return redefines;
	}
	/**
	 * @param redefines
	 * @uml.property  name="redefines"
	 */
	public void setRedefines(String redefines) {
		this.redefines = redefines;
	}

	/**
	 * @return
	 * @uml.property  name="numRiga"
	 */
	public int getNumRiga(){
		
		return numRiga;
	}
	
	/**
	 * @param numRiga
	 * @uml.property  name="numRiga"
	 */
	public void setNumRiga(int numRiga) {
		this.numRiga = numRiga;
		}


	/**
	 * @param totPicChild
	 * @uml.property  name="childsPicValue"
	 */
	public void setChildsPicValue(int totPicChild) {
		this.childsPicValue = totPicChild;
	}


	/**
	 * @return
	 * @uml.property  name="childsPicValue"
	 */
	public int getChildsPicValue() {
		int tmpOccurs = 1;
		if(occurs != 0){
			tmpOccurs = occurs;
		}
		
		int count = getPicValue() + getVirtualFloat();
		
		for(int x=0;x<child.size();x++){
			LinePropertyBean bean = child.get(x);
			count += bean.getChildsPicValue();
		}
		
		childsPicValue = count * tmpOccurs;
		return childsPicValue;
	}
	
	public String toStringChilds() {
		
		String childsLine = "";
		
		for(int x=0;x<child.size();x++){
			LinePropertyBean bean = child.get(x);
			childsLine = childsLine + bean.toStringChilds();
		}
		
		return toString()+childsLine;
	}
	
	public void add2ToIndex() {

		for(int x=0;x<child.size();x++){
			LinePropertyBean bean = child.get(x);
			if(index != null){
				bean.setIndex(Index.increaseIndex(Integer.parseInt(index)));
			}
			bean.add2ToIndex();
		}

	}
	
	public void remove2ToIndex() {

		for(int x=0;x<child.size();x++){
			LinePropertyBean bean = child.get(x);
			if(index != null){
				bean.setIndex(Index.decreaseIndex(Integer.parseInt(index)));
			}
			bean.remove2ToIndex();
		}

	}


	public boolean hasRedefines() {	
		return getRedefines() == null || !getRedefines().trim().equals("");
	}
	
}
