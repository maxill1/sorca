package com.cblformatter.model.beans;

public class LineProperty {
	private String index;
	private String field;
	private String picType;
	private int picValue;
	private int dichCount;
	private int occurs;
	
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

}
