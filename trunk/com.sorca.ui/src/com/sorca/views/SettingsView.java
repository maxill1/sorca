package com.sorca.views;


import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.sorca.beans.fromModelToNameEOLConverter;
import com.sorca.beans.fromNameToModelEOLConverter;
import com.sorca.language.Messages;
import com.sorca.model.beans.Model;
import com.sorca.utils.Constants;
import com.sorca.utils.PlatformUtils;
import com.sorca.views.utils.GuiUtils;

public class SettingsView extends ViewPart {

	private Composite top = null;
	private Text spacePic;
	private Text spaceIndex;
	private Text txtbyte;
	private Button fillerPresente;
	private Button headerPresente;
	private Button add2ToIndex;
	private Button autoUpdate;
	private Button handleRedefines = null;
	private Button handleErrors = null;
	private Combo comboEOL = null;
	private Combo comboCodifica = null;
	
	private void bindGUI() {

		
		
		GuiUtils.addBindingContext(
				comboCodifica,Model.getSettingsBean(), "encoding");
		GuiUtils.addBindingContext(
				comboEOL,Model.getSettingsBean(), "EOL",
				new fromNameToModelEOLConverter(),
				new fromModelToNameEOLConverter());
		
		GuiUtils.addBindingContext(
				txtbyte,Model.getSettingsBean(), "count");
		GuiUtils.addBindingContext(
				handleErrors,Model.getSettingsBean(), "handleErrors");
		GuiUtils.addBindingContext(
				handleRedefines,Model.getSettingsBean(), "handleRedefines");	
		GuiUtils.addBindingContext(
				headerPresente,Model.getSettingsBean(), "printHeader");
		GuiUtils.addBindingContext(
				spacePic,Model.getSettingsBean(), "picSpaces");

		GuiUtils.addBindingContext(
				spaceIndex,Model.getSettingsBean(), "indexSpaces");
		
		GuiUtils.addBindingContext(
				fillerPresente,Model.getSettingsBean(), "printFiller");
		GuiUtils.addBindingContext(
				add2ToIndex,Model.getSettingsBean(), "add2ToIndex");
		GuiUtils.addBindingContext(
				autoUpdate,Model.getSettingsBean(), "autoUpdate");
		
		
	}
	
	public SettingsView() {

	}

	

	
	@Override
	public void createPartControl(Composite parent) {
	
        top = new Composite(parent, SWT.NONE);

		FillLayout topLayout = new FillLayout(org.eclipse.swt.SWT.VERTICAL);
        top.setLayout(topLayout);

        
        topLayout.type = SWT.VERTICAL;
        
        
        createGruppoOpzioni(top);
        creaControlliSpeciali(top);
        creaGruppoCostanti(top);
        
        bindGUI();
	}






	@Override
	public void setFocus() {

	}

	private void createGruppoOpzioni(Composite top) {
	

        	Group opzioni = new Group(top, SWT.NONE);
        	GridLayout gridLayout = new GridLayout();
        	gridLayout.numColumns = 2;
        	opzioni.setText(Messages.GroupOptions);
        	opzioni.setLayout(gridLayout);
        		Label codificaLabel = new Label(opzioni, SWT.NONE);
        		codificaLabel.setText(Messages.Encoding);
  
    			comboCodifica = new Combo(opzioni, SWT.READ_ONLY);
    			comboCodifica.setItems(Constants.EncodingName);
    			comboCodifica.setText(PlatformUtils.getEncoding());

			Label lineaCapo = new Label(opzioni, SWT.NONE);
			lineaCapo.setText(Messages.EOL);

			comboEOL = new Combo(opzioni, SWT.READ_ONLY);
			comboEOL.setItems(Constants.EOLname);
			comboEOL.setText(PlatformUtils.getEOL());
		
			Label stampaErrLabel = new Label(opzioni, SWT.NONE);
			stampaErrLabel.setText(Messages.PrintErrors);
		
			handleErrors = new Button(opzioni, SWT.CHECK);
			handleErrors.setSelection(true);
		
		
			Label label3 = new Label(opzioni, SWT.NONE);
			label3.setText(Messages.HandleRedefines);
	
			handleRedefines = new Button(opzioni, SWT.CHECK);	
			//TODO da completare
			handleRedefines.setEnabled(false);
		
	}


	private void creaControlliSpeciali(Composite top) {

		Group controlliSpeciali = new Group(top, SWT.NONE);
    	GridLayout group1Layout = new GridLayout();
    	group1Layout.numColumns = 2;
    	group1Layout.makeColumnsEqualWidth = true;
    	controlliSpeciali.setLayout(group1Layout);
    	controlliSpeciali.setText(Messages.GroupSpecial);

    		Label header = new Label(controlliSpeciali, SWT.NONE);
    		header.setText(Messages.PrintHeader);
    		header.setToolTipText(Messages.PrintHeaderHint);

    		headerPresente = new Button(controlliSpeciali, SWT.CHECK);

    		Label fillerPresenteLabel = new Label(controlliSpeciali, SWT.NONE);
    		fillerPresenteLabel.setText(Messages.PrintFiller);
    		fillerPresenteLabel.setToolTipText(Messages.PrintFillerHint);

    		GridData button3LData = new GridData();
    		button3LData.widthHint = 24;
    		button3LData.heightHint = 22;
    		fillerPresente = new Button(controlliSpeciali, SWT.CHECK);
    		fillerPresente.setLayoutData(button3LData);
    
    		Label aggiungi2IndiceLabel = new Label(controlliSpeciali, SWT.NONE);
    		aggiungi2IndiceLabel.setText(Messages.Add2ToIndex);
   
    		add2ToIndex = new Button(controlliSpeciali, SWT.CHECK);
    		add2ToIndex.setSelection(true);
    		//TODO da completare
    		add2ToIndex.setEnabled(false);

    		aggiungi2IndiceLabel = new Label(controlliSpeciali, SWT.NONE);
    		aggiungi2IndiceLabel.setText(Messages.AutoUpdate);
    		
    		autoUpdate = new Button(controlliSpeciali, SWT.CHECK);
    		autoUpdate.setSelection(false);
    

	}


	
	public void creaGruppoCostanti(Composite top){
			Group costanti = new Group(top, SWT.NONE);
			GridLayout group1Layout1 = new GridLayout();
			
			group1Layout1.numColumns = 2;
			group1Layout1.makeColumnsEqualWidth = true;
			costanti.setLayout(group1Layout1);
			costanti.setText(Messages.GroupOptions);
			
				Label byteLabel = new Label(costanti, SWT.NONE);
				byteLabel.setText(Messages.ByteCount);
		
				txtbyte = new Text(costanti, SWT.NONE);
				txtbyte.setText(Model.getSettingsBean().getCount()+"");
				GridData txtbyteLData = new GridData();
				txtbyteLData.horizontalAlignment = GridData.FILL;
				txtbyteLData.grabExcessHorizontalSpace = true;
				txtbyte.setLayoutData(txtbyteLData);
		
				Label indexLabel = new Label(costanti, SWT.NONE);
				indexLabel.setText(Messages.IndexSpaces);
		
				spaceIndex = new Text(costanti, SWT.NONE);
				spaceIndex.setText(Model.getSettingsBean().getIndexSpaces()+"");
				GridData spaceIndexLData = new GridData();
				spaceIndexLData.horizontalAlignment = GridData.FILL;
				spaceIndexLData.grabExcessHorizontalSpace = true;
				spaceIndex.setLayoutData(spaceIndexLData);
			
		
				Label PicLabel = new Label(costanti, SWT.NONE);
				PicLabel.setText(Messages.PicSpaces);
		
				spacePic = new Text(costanti, SWT.NONE);
				spacePic.setText(Model.getSettingsBean().getPicSpaces()+"");
				GridData scapePicLData = new GridData();
				scapePicLData.horizontalAlignment = GridData.FILL;
				scapePicLData.grabExcessHorizontalSpace = true;
				spacePic.setLayoutData(scapePicLData);
				
		
	}
	
}
