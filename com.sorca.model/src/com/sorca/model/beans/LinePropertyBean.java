package com.sorca.model.beans;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;

import com.sorca.utils.Convert;
import com.sorca.utils.Index;
import com.sorca.utils.LineUtils;
import com.sorca.utils.Pic;

public class LinePropertyBean extends ModelObject{
	
	//MODELINIT
	public LinePropertyBean parent;

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
					

		if(!indice.equals("") && !LineUtils.isHeader(campo)){
			indice = Index.increaseIndex(Integer.parseInt(indice));
		}

		} catch (NumberFormatException e) {
			MessageDialog.openError(null, "Errore","Si è verificato un errore \n"+
			"Controlla che non ci siano commenti non asteriscati sul campo: " + campo);
			e.printStackTrace();
		}
		

			if (occurs != 0){
				String occursStr = "OCCURS "+occurs;
				campo = campo + occursStr;
			}
		
		if (!redefines.equals("")){
			redefines = redefines +" ";
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

	private String index;
	private String field;
	private String picType;
	private int picValue;
	private int dichCount;
	private int occurs = 1;
	private String fullLine;
	private String redefines;
	private int childsPicValue;

	private int numRiga;

	private int virtualFloat;
	
	public int getVirtualFloat() {
		return virtualFloat;
	}


	public void setVirtualFloat(int virtualFloat) {
		this.virtualFloat = virtualFloat;
	}


	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		propertyChangeSupport.firePropertyChange("index", this.index,
				this.index = index);
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public int getPicValue() {
		return picValue;
	}
	public void setPicValue(int picValue) {
		this.picValue = picValue;
	}
	public void setOccurs(int occurs) {
		this.occurs = occurs;
	}
	public int getOccurs() {
		return occurs;
	}
	public void setDichCount(int dichCount) {
		this.dichCount = dichCount;
	}
	public int getDichCount() {
		return dichCount;
	}
	public void setFullLine(String fullLine) {
		this.fullLine = fullLine;
	}
	public String getFullLine() {
		return fullLine;
	}
	public String getRedefines() {
	
		return redefines;
	}
	public void setRedefines(String redefines) {
		this.redefines = redefines;
	}

	public int getNumRiga(){
		
		return numRiga;
	}
	
	public void setNumRiga(int numRiga) {
		this.numRiga = numRiga;
		}


	public void setChildsPicValue(int totPicChild) {
		this.childsPicValue = totPicChild;
	}


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
			childsLine += bean.toStringChilds();
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
	
}
