package com.cblformatter.handler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.cblformatter.model.beans.LinePropertyBean;
import com.cblformatter.model.beans.Model;
import com.cblformatter.utils.Constants;
import com.cblformatter.utils.LineUtils;
import com.cblformatter.views.utils.FileUtils;

public class ExportOperation implements IRunnableWithProgress {

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
	InterruptedException {


		File folderPath = new File(Model.getFileBean().getFolderSelected());
		File inputFile = new File(Model.getFileBean().getFileSelected());

		monitor.subTask("");

		monitor.worked(10);
		try {

			//creo la stringa da stampare su file
			String toPrint = preparePrint();

			monitor.worked(15);
			//scrivo File

			File outputFile = null;

			boolean singleArea = Model.getFileBean().isExportSingleArea();
			boolean inputArea = Model.getFileBean().isExportSeparateInputArea();
			boolean outpuArea = Model.getFileBean().isExportSeparateOutputArea();

			//creo il file aggiungendo nome e tipo
			if(singleArea){
				outputFile = FileUtils.creaFileOutput(folderPath, inputFile, Constants.SINGLE_AREA);
				//Aggiunge header corretto
				toPrint = addHeaderAndFiller(toPrint,Constants.SINGLE_AREA);
				//Esegui codifica file
				toPrint = FileUtils.encodeString(toPrint);	
				//scrivo il file
				FileUtils.scriviSuFile(outputFile, toPrint);
			}else{
				if(inputArea && outpuArea){
					String toPrintOutput = toPrint;
					outputFile = FileUtils.creaFileOutput(folderPath, inputFile, Constants.INPUT_AREA);
					//Aggiunge header corretto
					toPrint = addHeaderAndFiller(toPrint,Constants.INPUT_AREA);
					//Esegui codifica file
					toPrint = FileUtils.encodeString(toPrint);	
					//scrivo il file
					FileUtils.scriviSuFile(outputFile, toPrint);

					monitor.worked(35);

					outputFile = FileUtils.creaFileOutput(folderPath, inputFile, Constants.OUTPUT_AREA);
					//Aggiunge header corretto
					toPrintOutput = addHeaderAndFiller(toPrintOutput,Constants.OUTPUT_AREA);
					//Esegui codifica file
					toPrint = FileUtils.encodeString(toPrintOutput);	
					//scrivo il file
					FileUtils.scriviSuFile(outputFile, toPrintOutput);

					monitor.worked(45);
				}else{
					if(inputArea){
						outputFile = FileUtils.creaFileOutput(folderPath, inputFile, Constants.INPUT_AREA);
						//Aggiunge header corretto
						toPrint = addHeaderAndFiller(toPrint,Constants.INPUT_AREA);
					}else{
						outputFile = FileUtils.creaFileOutput(folderPath, inputFile, Constants.OUTPUT_AREA);
						//Aggiunge header corretto
						toPrint = addHeaderAndFiller(toPrint,Constants.OUTPUT_AREA);
					}
					monitor.worked(35);
					//Esegui codifica file
					toPrint = FileUtils.encodeString(toPrint);	
					//scrivo il file
					FileUtils.scriviSuFile(outputFile, toPrint);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}


		monitor.worked(50);
		monitor.done();

	}

	private String addHeaderAndFiller(String outputLines, String type) {
		/**** add Header e Filler ***/
		int count = Integer.parseInt(Model.getFileBean().getCount());
		
		if(Model.getSettingsBean().isPrintHeader()){
			if(type.equals(Constants.SINGLE_AREA) ){
				
				outputLines = LineUtils.addHeader(outputLines, Constants.SINGLE_AREA);
				count = count + 0;
			}else if (type.equals(Constants.INPUT_AREA)){
				
				outputLines = LineUtils.addHeader(outputLines, Constants.INPUT_AREA);
				count = count + 3;
			}else{

				if(Model.getFileBean().isExportSeparateOutputArea()){
					outputLines = LineUtils.addHeader(outputLines, Constants.OUTPUT_AREA);
					count = count + 84;
				}
			}
		}
		if(Model.getSettingsBean().isPrintFiller() && !Model.getFileBean().isExportSingleArea()){
			
			int fillerSize = 
				Integer.parseInt(Model.getSettingsBean().getCount()) - count; 
			
			outputLines = LineUtils.addFiller(outputLines, fillerSize);
		}
	
		return outputLines;
	}

	public static String preparePrint(){

		String outputLines = "";
		ArrayList<LinePropertyBean> linee = new ArrayList<LinePropertyBean>(Model.getLinee());
		
		outputLines = outputLines + iterateLineProperty(outputLines,linee);



		return outputLines;

	}

	private static String iterateLineProperty(String outputLines,ArrayList<LinePropertyBean> linee) {

		//Stampa
		for(int i=0; i<linee.size(); i++){

			LinePropertyBean line = (LinePropertyBean) linee.get(i);

			outputLines = line.toStringChilds();

		}

		return outputLines;
	}
}
