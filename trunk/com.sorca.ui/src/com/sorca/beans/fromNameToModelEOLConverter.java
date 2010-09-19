package com.sorca.beans;

import org.eclipse.core.databinding.UpdateValueStrategy;

import com.sorca.utils.Decode;

public class fromNameToModelEOLConverter extends UpdateValueStrategy {

	public Object convert(Object fromObject) {		
		String b = (String)fromObject;	
		return Decode.fromEOLNameToEOLString(b);				
	}
	
	public Object getFromType(){
		return String.class;
	}

	public Object getToType(){
		return String.class;
	}
}
