package com.sorca.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.sorca.model.beans.Model;
import com.sorca.model.beans.views.SettingsViewBean;

public class CoreUtils {
	
	public static void initConfigFile(){
		
		
		String nomeFileProps = "sorca.ini";

		try {

			Properties props = new Properties();
			
			Bundle bundle = Platform.getBundle("com.sorca.core");		
			InputStream is = bundle.getEntry("/" + nomeFileProps).openStream();
			props.load(is);


			String[] keys = getFileProperty(getFileINI());	


			for(int x = 0; x < keys.length; x++){
				if(keys[x] != null){
					System.setProperty(keys[x], props.getProperty(keys[x]));
				}
			}	

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static String[] getFileProperty(File file) {
		String[] keys = null;
		try {
			int numLines = getNumLine(file);
			FileReader in = new FileReader(file);

			BufferedReader br = new BufferedReader(in);
			keys = new String[numLines];	

			for(int x = 0; x<numLines; x++){
				String line = br.readLine();
				keys[x] = (line.substring(0,line.indexOf("="))).trim();  
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keys;		
	}

	private static int getNumLine(File file) {

		String line;
		int numLine = 0;

		try {
			FileReader in= new FileReader(file);
			BufferedReader br = new BufferedReader(in);

			line = br.readLine();

			while(line != null){
				numLine = numLine+1;
				line = br.readLine();
			}

		} catch (IOException e) {
			return numLine;
		}

		return numLine;
	}

	public static void saveConfigFile() {
		try {

			File configFile = getFileINI();

			int numLines = getNumLine(configFile);

			FileReader in = new FileReader(configFile);

			BufferedReader br = new BufferedReader(in);

			String outFile = "";

			for(int x = 0; x<numLines; x++){
				String line = br.readLine();
				if(line != null){
					outFile = outFile + mapConfig(line)+"\n";
				}
				//				outFile = outFile + (line.substring(0,line.indexOf("="))).trim();  
			}

			FileWriter out;
			out = new FileWriter(configFile);
			BufferedWriter bw = new BufferedWriter(out);

			bw.write(outFile);
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getRoot () {
		String path = null;
		try {
			path = FileLocator.toFileURL (
					Platform.getBundle ("com.sorca.core"). getEntry ("sorca.ini")). getPath ();

			path = path.replaceAll("sorca.ini", "");

		} catch (Exception e) {
			e.printStackTrace ();

		}

		return path;
	}


	public static File getFileINI() {
		String nomeFileProps = "sorca.ini";

		File file = new File (getRoot()+File.separatorChar+nomeFileProps);

		return file;
	}

	private static String mapConfig(String line) {
		SettingsViewBean bean = Model.getSettingsBean();

		if(!line.equals("")){
			String subLine = (line.substring(0,line.indexOf("="))).trim();

			if(subLine.trim().equals("sorca.update")){
				return subLine + " = " +  bean.isAutoUpdate();
			}
			if(subLine.trim().equals("sorca.count")){
				return subLine + " = " +  bean.getCount();
			}
			if(subLine.trim().equals("sorca.indexSpaces")){
				return subLine + " = " +  bean.getIndexSpaces();
			}
			if(subLine.trim().equals("sorca.picSpaces")){
				return subLine + " = " +  bean.getPicSpaces();
			}
			if(subLine.trim().equals("sorca.add2ToIndex")){
				return subLine + " = " +  bean.isAdd2ToIndex();
			}			
			if(subLine.trim().equals("sorca.handleErrors")){
				return subLine + " = " +  bean.isHandleErrors();
			}
			if(subLine.trim().equals("sorca.handleRedefines")){
				return subLine + " = " +  bean.isHandleRedefines();
			}
			if(subLine.trim().equals("sorca.printHeader")){
				return subLine + " = " +  bean.isPrintHeader();
			}
			if(subLine.trim().equals("sorca.printFiller")){
				return subLine + " = " +  bean.isPrintFiller();
			}
			if(subLine.trim().equals("sorca.generateInput")){
				return subLine + " = " +  bean.isGenerateInput();
			}
			if(subLine.trim().equals("sorca.generateOutput")){
				return subLine + " = " +  bean.isGenerateOutput();
			}

		}


		return line;
	}

}
