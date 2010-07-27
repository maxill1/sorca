package com.cblformatter.model.beans;

import java.util.ArrayList;

public class LinePropertyBean {
	
	//MODELINIT
	public LinePropertyBean parent;

	public ArrayList<LinePropertyBean> child = new ArrayList<LinePropertyBean>();


	public String toString() {
		String rv = getIndex() + " - " + getField();

		return rv;
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
		this.index = index;
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

}
