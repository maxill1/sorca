package com.cblformatter.views;


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

import com.cblformatter.model.beans.Model;
import com.cblformatter.views.utils.GuiUtils;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SettingsView extends ViewPart {

	private Composite top = null;
	private Text spacePic;
	private Text spaceIndex;
	private Text txtbyte;
	private Button fillerPresente;
	private Button headerPresente;
	private Button add2ToIndex;
	private Button viewInnested;
	private Button handleRedefines = null;
	private Button handleErrors = null;
	private Combo comboEOL = null;
	private Combo comboCodifica = null;
	
	private void bindGUI() {

		
		
		GuiUtils.addBindingContext(
				comboCodifica,Model.getSettingsBean(), "codifica");
		GuiUtils.addBindingContext(
				comboEOL,Model.getSettingsBean(), "EOL");
		GuiUtils.addBindingContext(
				txtbyte,Model.getSettingsBean(), "count");
		GuiUtils.addBindingContext(
				handleErrors,Model.getSettingsBean(), "handleErrors");
		GuiUtils.addBindingContext(
				handleRedefines,Model.getSettingsBean(), "handleRedefines");	
		GuiUtils.addBindingContext(
				headerPresente,Model.getSettingsBean(), "headerPresente");
		GuiUtils.addBindingContext(
				spacePic,Model.getSettingsBean(), "picSpaces");

		GuiUtils.addBindingContext(
				spaceIndex,Model.getSettingsBean(), "indexSpaces");
		
		GuiUtils.addBindingContext(
				fillerPresente,Model.getSettingsBean(), "fillerPresente");
		GuiUtils.addBindingContext(
				add2ToIndex,Model.getSettingsBean(), "add2ToIndex");
		GuiUtils.addBindingContext(
				viewInnested,Model.getSettingsBean(), "viewInnested");
		
		
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
        	opzioni.setText("Opzioni");
        	opzioni.setLayout(gridLayout);
        		Label codificaLabel = new Label(opzioni, SWT.NONE);
        		codificaLabel.setText("Codifica");
  
    			comboCodifica = new Combo(opzioni, SWT.READ_ONLY);
    			comboCodifica.setItems(new String[] { "ISO-8859-1", "UTF-8"});
    			comboCodifica.setText("ISO-8859-1");
    	
    				ComboViewer comboViewer2 = new ComboViewer(comboCodifica, SWT.NULL);
    	
    	

			Label lineaCapo = new Label(opzioni, SWT.NONE);
			lineaCapo.setText("Linea a Capo");

			comboEOL = new Combo(opzioni, SWT.READ_ONLY);
			comboEOL.setItems(new String[] { "DOS", "UNIX"});
			comboEOL.setText("DOS");
			
				ComboViewer comboViewer1 = new ComboViewer(comboEOL, SWT.NULL);
	
		
			Label stampaErrLabel = new Label(opzioni, SWT.NONE);
			stampaErrLabel.setText("Stampa Errori");
		
			handleErrors = new Button(opzioni, SWT.CHECK);
			handleErrors.setSelection(true);
		
		
			Label label3 = new Label(opzioni, SWT.NONE);
			label3.setText("Gestisci Redefines");
	
			handleRedefines = new Button(opzioni, SWT.CHECK);	
		
	}


	private void creaControlliSpeciali(Composite top) {

		Group controlliSpeciali = new Group(top, SWT.NONE);
    	GridLayout group1Layout = new GridLayout();
    	group1Layout.numColumns = 2;
    	group1Layout.makeColumnsEqualWidth = true;
    	controlliSpeciali.setLayout(group1Layout);
    	controlliSpeciali.setText("Controlli Speciali");

    		Label header = new Label(controlliSpeciali, SWT.NONE);
    		header.setText("Header Presente");

    		headerPresente = new Button(controlliSpeciali, SWT.CHECK);
 
    		Label fillerPresenteLabel = new Label(controlliSpeciali, SWT.NONE);
    		fillerPresenteLabel.setText("Filler Presente");

    		GridData button3LData = new GridData();
    		button3LData.widthHint = 24;
    		button3LData.heightHint = 22;
    		fillerPresente = new Button(controlliSpeciali, SWT.CHECK);
    		fillerPresente.setLayoutData(button3LData);
    
    		Label aggiungi2IndiceLabel = new Label(controlliSpeciali, SWT.NONE);
    		aggiungi2IndiceLabel.setText("Aggiungi 2 All'indice");
   
    		add2ToIndex = new Button(controlliSpeciali, SWT.CHECK);
    		add2ToIndex.setSelection(true);

    		aggiungi2IndiceLabel = new Label(controlliSpeciali, SWT.NONE);
    		aggiungi2IndiceLabel.setText("Visulizz.strut. dati innestata");
   
    		
    		viewInnested = new Button(controlliSpeciali, SWT.CHECK);
    		viewInnested.setSelection(false);
    

	}


	
	public void creaGruppoCostanti(Composite top){
			Group costanti = new Group(top, SWT.NONE);
			GridLayout group1Layout1 = new GridLayout();
			
			group1Layout1.numColumns = 2;
			group1Layout1.makeColumnsEqualWidth = true;
			costanti.setLayout(group1Layout1);
			costanti.setText("Opzioni");
			
				Label byteLabel = new Label(costanti, SWT.NONE);
				byteLabel.setText("Byte Count");
		
				txtbyte = new Text(costanti, SWT.NONE);
				txtbyte.setText(Model.getSettingsBean().getCount()+"");
				GridData txtbyteLData = new GridData();
				txtbyteLData.horizontalAlignment = GridData.FILL;
				txtbyteLData.grabExcessHorizontalSpace = true;
				txtbyte.setLayoutData(txtbyteLData);
		
				Label indexLabel = new Label(costanti, SWT.NONE);
				indexLabel.setText("Spazi Indice");
		
				spaceIndex = new Text(costanti, SWT.NONE);
				spaceIndex.setText(Model.getSettingsBean().getIndexSpaces()+"");
				GridData spaceIndexLData = new GridData();
				spaceIndexLData.horizontalAlignment = GridData.FILL;
				spaceIndexLData.grabExcessHorizontalSpace = true;
				spaceIndex.setLayoutData(spaceIndexLData);
			
		
				Label PicLabel = new Label(costanti, SWT.NONE);
				PicLabel.setText("Pic Space");
		
				spacePic = new Text(costanti, SWT.NONE);
				spacePic.setText(Model.getSettingsBean().getPicSpaces()+"");
				GridData scapePicLData = new GridData();
				scapePicLData.horizontalAlignment = GridData.FILL;
				scapePicLData.grabExcessHorizontalSpace = true;
				spacePic.setLayoutData(scapePicLData);
				
		
	}
	
}
