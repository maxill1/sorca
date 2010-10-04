package com.sorca.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.sorca.model.beans.LinePropertyBean;
import com.sorca.model.beans.Model;
import com.sorca.utils.Constants;
import com.sorca.utils.LineUtils;
import com.sorca.views.utils.ProcessUtils;

public class ImportOperation implements IRunnableWithProgress {
	static IProgressMonitor monitor = null;
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
	InterruptedException {
		ImportOperation.monitor = monitor;

		monitor.subTask("");

		monitor.worked(10);

		try {
			
			importFile();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		monitor.worked(50);
		monitor.done();
		

	}

	public static boolean importFile() throws FileNotFoundException{
	
		
		String filePath = Model.getFileBean().getFileSelected();
		File inputFile = new File(filePath);

	
		//lettura file selezionato
		FileReader fr = new FileReader(inputFile);
		BufferedReader br = new BufferedReader(fr);
	
		/***** controllo colonne e numeri ***/
		//leggo ogni linea e creo una lista
		LinkedHashMap<Integer,String> lineeNonFormattate = LineUtils.popolaLineeNonFormattate(br);	
		monitor.worked(15);
		
		//creo una lista di linee con proprietà
		LinkedHashMap<Integer,LinePropertyBean> linePropertyList =	LineUtils.popolaDatiLinee(lineeNonFormattate);
		
		//creo lista ordinata
		LinkedHashMap<Integer,LinePropertyBean> linePropertyListSort = LineUtils.cleanList(linePropertyList);
		
		//TRASFORMO IN ARRAYLIST
		ArrayList<LinePropertyBean> linee = new ArrayList<LinePropertyBean>();
		linee.addAll(0,linePropertyListSort.values());
		monitor.worked(20);
		
		//CREO GERARCHIA FIGLI	
		linee = ProcessUtils.searchForChild(linee,0,null);
		
		monitor.worked(25);
	
		//AGGIORNO IL MODEL
		Model.setLinee(linee);
		LinePropertyBean.updateModel(linee);
		monitor.worked(30);
	
		return true;
	}

	public static String preparePrint(){

		String outputLines = "";
		ArrayList<LinePropertyBean> linee = new ArrayList<LinePropertyBean>(Model.getLinee());
		
		outputLines = outputLines + iterateLineProperty(outputLines,linee);

		/**** add Header e Filler ***/
		int count = Integer.parseInt(Model.getFileBean().getCount());
		
		if(Model.getSettingsBean().isPrintHeader()){
			if(Model.getFileBean().isExportSeparateInputArea() 
					|| Model.getFileBean().isExportSingleArea()){
				
				outputLines = LineUtils.addHeader(outputLines, Constants.INPUT_AREA);
				count = count + 3;
			}

			if(Model.getFileBean().isExportSeparateOutputArea()){
				outputLines = LineUtils.addHeader(outputLines, Constants.OUTPUT_AREA);
				count = count + 84;
			}
		}
		if(Model.getSettingsBean().isPrintFiller()){
			outputLines = LineUtils.addFiller(outputLines, count);
		}

		return outputLines;

	}

	private static String iterateLineProperty(String outputLines,ArrayList<LinePropertyBean> linee) {

		//Stampa
		for(int i=0; i<linee.size(); i++){

			LinePropertyBean line = (LinePropertyBean) linee.get(i);
//			if(line.child != null){
//				outputLines = iterateLineProperty(outputLines,linee) + outputLines;
//				linee.remove(line);
//			}

			outputLines = line.toStringChilds();
	


		}

		return outputLines;
	}
}
