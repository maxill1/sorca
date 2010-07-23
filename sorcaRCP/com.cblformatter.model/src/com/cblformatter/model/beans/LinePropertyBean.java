package com.cblformatter.model.beans;

import java.util.ArrayList;

public class LinePropertyBean {
	
	//MODELINIT
	public LinePropertyBean parent;

	public ArrayList<LinePropertyBean> child = new ArrayList<LinePropertyBean>();

	public int counter;

	public LinePropertyBean(int counter, LinePropertyBean parent) {
		this.parent = parent;
		this.counter = counter;
	}

	public String toString() {
		String rv = "Item ";
		if (parent != null) {
			rv = parent.toString() + ".";
		}

		rv += counter;

		return rv;
	}

	
	public static LinePropertyBean createModel() {

		LinePropertyBean root = new LinePropertyBean(0, null);
		root.counter = 0;

		LinePropertyBean tmp;
		LinePropertyBean subItem;
		for (int i = 1; i < 10; i++) {
			tmp = new LinePropertyBean(i, root);
			root.child.add(tmp);
			for (int j = 1; j < i; j++) {
				subItem = new LinePropertyBean(j, tmp);
				subItem.child.add(new LinePropertyBean(j * 100, subItem));
				tmp.child.add(subItem);
			}
		}

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
	private int occurs;
	private String fullLine;
	private String specials;

	private int numRiga;
	
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
	public String getSpecials() {
	
		return specials;
	}
	public void setSpecials(String specials) {
		this.specials = specials;
	}

	public int getNumRiga(){
		
		return numRiga;
	}
	
	public void setNumRiga(int numRiga) {
		this.numRiga = numRiga;
		}

}
