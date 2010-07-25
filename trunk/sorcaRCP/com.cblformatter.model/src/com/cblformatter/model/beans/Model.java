package com.cblformatter.model.beans;




import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.cblformatter.model.beans.views.FileViewBean;
import com.cblformatter.model.beans.views.SettingsViewBean;

public class Model {
	
	private static SettingsViewBean settingsBean = null;
	private static FileViewBean fileBean = null;
	
	public Model(){
		
		 settingsBean = new SettingsViewBean();
		 setFileBean(new FileViewBean());
		 
		 parentLine = new LinePropertyBean();
		 parentLine.setField("PARENT");
		
	}
	
	
	private static LinkedHashMap<Integer, LinePropertyBean>  formattedLines;
	private static LinkedHashMap<Integer, LinePropertyBean>  rawLines;
	private static LinkedHashMap<Integer, LinePropertyBean> lineProperty;
	private static ArrayList<OccursBean> occurs;
	
	private static LinePropertyBean linea = new LinePropertyBean();

	public static void setOccurs(ArrayList<OccursBean> occurs) {
		Model.occurs = occurs;
	}

	public static ArrayList<OccursBean> getOccurs() {
		return occurs;
	}
	
	public static LinkedHashMap<Integer, LinePropertyBean> getRawLines() {
		return rawLines;
	}

	public static void setRawLines(LinkedHashMap<Integer, LinePropertyBean> linePropertyList) {
		Model.rawLines = linePropertyList;
	}

	public static LinkedHashMap<Integer, LinePropertyBean> getFormattedLines() {
		return formattedLines;
	}

	public static void setFormattedLines(LinkedHashMap<Integer, LinePropertyBean> formattedLines) {
		Model.formattedLines = formattedLines;
	}

	
	public static void setLineProperty(LinkedHashMap<Integer, LinePropertyBean>  lineProperty) {
		Model.lineProperty = lineProperty;
	}

	public static LinkedHashMap<Integer, LinePropertyBean>  getLineProperty() {
		return lineProperty;
	}

	public static void setSettingsBean(SettingsViewBean settingsBean) {
		Model.settingsBean = settingsBean;
	}

	public static SettingsViewBean getSettingsBean() {
		return settingsBean;
	}

	public static void setFileBean(FileViewBean fileBean) {
		Model.fileBean = fileBean;
	}

	public static FileViewBean getFileBean() {
		return fileBean;
	}
	
	
	private static ArrayList<LinePropertyBean> linee = new ArrayList<LinePropertyBean>();
	private static ArrayList<LinePropertyBean> lineeNonPulite = new ArrayList<LinePropertyBean>();



	public static void setLinee(ArrayList<LinePropertyBean> linee) {
		Model.linee = linee;
	}

	public static ArrayList<LinePropertyBean> getLinee() {
		return linee;
	}

	public static void setLineeNonPulite(
			ArrayList<LinePropertyBean> lineeNonPulite) {

		Model.lineeNonPulite = lineeNonPulite;
	}
	
	private static LinePropertyBean parentLine;

	public static LinePropertyBean getParentLine() {
		
		if(parentLine == null)
			return new LinePropertyBean();
		
		return parentLine;
		
	}
	
	
	
	
}
