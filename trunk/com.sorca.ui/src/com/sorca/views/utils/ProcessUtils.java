package com.sorca.views.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.sorca.counters.Counter;
import com.sorca.model.beans.LinePropertyBean;
import com.sorca.model.beans.Model;
import com.sorca.model.beans.OccursBean;
import com.sorca.utils.LineUtils;

public class ProcessUtils {

	//	public static LinkedHashMap<Integer,LinePropertyBean> processFile() throws FileNotFoundException{
	//
	//
	//	int indexSpaces = Integer.parseInt(Model.getSettingsBean().getIndexSpaces());
	//	int picSpaces = Integer.parseInt(Model.getSettingsBean().getIndexSpaces());
	//	boolean add2ToIndex = Model.getSettingsBean().isAdd2ToIndex();
	//	String programCall = Model.getSettingsBean().getProgramCall();
	//	String programCallAlt = Model.getSettingsBean().getProgramCallAlt();
	//	String codifica = Model.getSettingsBean().getCodifica();
	//	String EOL = Model.getSettingsBean().getEOL();
	//	boolean HandleErrors = Model.getSettingsBean().isHandleErrors();
	//	String filePath = Model.getFileBean().getFileSelected();
	//	File inputFile = new File(filePath);
	//
	//	
	//	/** variabili*/
	//	
	//	//Liste
	//	LinkedHashMap<Integer,LinePropertyBean>linePropertyList; //grezze
	//	LinkedHashMap<Integer,LinePropertyBean> linePropertyListSort; //ordinate
	//	LinkedHashMap<Integer,String> lineeNonFormattate; //Stringhe non formattate
	//	
	//	
	//	String fileName = inputFile.getName();
	//
	//	//lettura file selezionato
	//	FileReader fr = new FileReader(inputFile);
	//	BufferedReader br = new BufferedReader(fr);
	//
	//	/***** controllo colonne e numeri ***/
	//	//leggo ogni linea e creo una lista
	//	lineeNonFormattate = LineUtils.popolaLineeNonFormattate(br);	
	//	
	//	//creo una lista di linee con proprietà
	//	linePropertyList =	LineUtils.popolaDatiLinee(lineeNonFormattate);
	//
	////	Model.setLinee(LineUtils.popolaDatiLineeNUOVO(lineeNonFormattate));
	//	
	//	//creo lista ordinata
	//	linePropertyListSort = LineUtils.cleanList(linePropertyList);
	//	Model.setFormattedLines(linePropertyListSort);
	//	
	//	ArrayList<LinePropertyBean> linee = new ArrayList<LinePropertyBean>();
	//	linee.addAll(0,linePropertyListSort.values());
	//	
	//	linee = searchForChild(linee,0,null);
	//	
	//	
	////	ArrayList<LinePropertyBean> lineeNew = new ArrayList<LinePropertyBean>();
	////	lineeNew.add(LinePropertyBean.updateModel(linee))
	//	LinePropertyBean.updateModel(linee);
	//	
	////	Model.setLinee(lineeNew);
	//	
	////	LinePropertyBean.updateModel(lineeNew);
	//	
	//	return linePropertyListSort;
	//}



	private static ArrayList<LinePropertyBean> searchForChildTEST(
			ArrayList<LinePropertyBean> linee, int startCounter, LinePropertyBean parent) {

		startCounter = 1;

		LinePropertyBean current = linee.get(startCounter);

		//	
		//		if(parent != null){
		//			current.parent = parent;
		//			parent.child.add(current);
		//			
		//		}

		for(int x = 0; x< 3; x++){
			LinePropertyBean next;
			try{
				next = linee.get(startCounter+1);
			}catch (IndexOutOfBoundsException e) {
				return linee;
			}


			int currentIndex = Integer.parseInt(current.getIndex());
			int nextIndex =  Integer.parseInt(next.getIndex());

			if(currentIndex < nextIndex){
				int childCounter = startCounter+1;

				next.parent = current;
				current.child.add(next);

				//					searchForChild(linee,childCounter,current);

				linee.remove(childCounter);

			}else if(currentIndex > nextIndex){
				return linee;
			}

			startCounter++;
		}

		return linee;
	}

	public static ArrayList<LinePropertyBean> searchForChild(
			ArrayList<LinePropertyBean> linee, int startCounter, LinePropertyBean parent) {


		LinePropertyBean current = linee.get(startCounter);

		if(parent != null){
			current.parent = parent;
			parent.child.add(current);

		}

		LinePropertyBean next;
		try{
			next = linee.get(startCounter+1);
		}catch (IndexOutOfBoundsException e) {
			return linee;
		}


		int currentIndex = Integer.parseInt(current.getIndex());
		int nextIndex =  Integer.parseInt(next.getIndex());

		if(currentIndex < nextIndex){
			int childCounter = startCounter+1;

			searchForChild(linee,childCounter,current);

			linee.remove(childCounter);

		}else if(currentIndex == nextIndex){
			searchForChild(linee,startCounter+1,parent);
			linee.remove(startCounter);

		}else if(currentIndex > nextIndex){


			//INDIVIDUA il parent dal livello
			LinePropertyBean locParent = getParentFromIndex(current,next,parent);

			searchForChild(linee,startCounter+1,locParent);
			if(next.parent != null){
				linee.remove(startCounter);
			}
		}

		return linee;
	}

	public static LinePropertyBean getParentFromIndex(
			LinePropertyBean current, LinePropertyBean next, LinePropertyBean parent) {


		LinePropertyBean locParent = Model.getParentLine();

		int currentIndex = Integer.parseInt(current.getIndex());
		int nextIndex =  Integer.parseInt(next.getIndex());


		try{

			int indexDifference = currentIndex - nextIndex;


			if(indexDifference== 1){
				locParent = parent;
			}
			if(indexDifference== 2 || indexDifference== 3){
				locParent = parent.parent;
			}

			if(indexDifference== 4 || indexDifference== 5){
				locParent = parent.parent.parent;
			}

			if(indexDifference== 6 || indexDifference== 7){
				locParent = parent.parent.parent.parent;
			}

			if(indexDifference== 8 || indexDifference== 9){
				locParent = parent.parent.parent.parent.parent;
			}

			if(indexDifference== 10 || indexDifference== 11){
				locParent = parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 12 || indexDifference== 13){
				locParent = parent.parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 14 || indexDifference== 15){
				locParent = parent.parent.parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 16 || indexDifference== 17){
				locParent = parent.parent.parent.parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 18 || indexDifference== 19){
				locParent = parent.parent.parent.parent.parent.parent.parent.parent.parent.parent;
			}

			if(indexDifference== 20 || indexDifference== 21){
				locParent = parent.parent.parent.parent.parent.parent.parent.parent.parent.parent.parent;
			}


		}catch (NullPointerException e) {
			if(locParent == null){
				locParent = Model.getParentLine();
			}
		}

		//TODO TROVARE UN MODO MIGLIORE
		//IF 01 then parent is root
		if(nextIndex == 1 && locParent != null){
			return Model.getParentLine();
		}

		return locParent;
	}

	public static void countFiller() {

		int count = 0;
		for(int x = 0; x< Model.getLinee().size(); x++){
			count = count + Model.getLinee().get(x).getChildsPicValue();
		}

		Model.getFileBean().setCount(String.valueOf(count));
	}

}
