package com.cblformatter.views;


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.cblformatter.model.beans.Settings;
import com.cblformatter.utils.Costants;


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
public class OptionView extends ViewPart {

	private Composite top = null;
	private Group opzioni = null;
	private Label codificaLabel = null;
	private Label lineaCapo = null;
	private Label indexLabel;
	private Label byteLabel;
	private Group costanti;
	private Text scapePic;
	private Text spaceIndex;
	private Text txtbyte;
	private Button btnFiller;
	private Button button1;
	private Label PicLabel;
	private Button btnIndice;
	private Label aggiungi2Indice;
	private Label fillerPresente;
	private Label header;
	private Group controlliSpeciali;
	private Label stampaErrLabel = null;
	private Label label3 = null;
	private Button checkBox = null;
	private Button handleErrors = null;
	private Combo comboLineaACapo = null;
	private ComboViewer comboViewer1 = null;
	private Combo comboCodifica = null;
	private ComboViewer comboViewer2 = null;
	public OptionView() {
		// TODO Auto-generated constructor stub
	}

	

	
	@Override
	public void createPartControl(Composite parent) {
	
        top = new Composite(parent, SWT.NONE);
		// TODO Auto-generated method stub

		FillLayout topLayout = new FillLayout(org.eclipse.swt.SWT.VERTICAL);
        top.setLayout(topLayout);

        
        topLayout.type = SWT.VERTICAL;
        
        
        createGruppoOpzioni(top);
        creaControlliSpeciali(top);
        creaGruppoCostanti(top);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	private void createGruppoOpzioni(Composite top) {
	
        {
        	opzioni = new Group(top, SWT.NONE);
        	GridLayout gridLayout = new GridLayout();
        	gridLayout.numColumns = 2;
        	opzioni.setText("Opzioni");
        	opzioni.setLayout(gridLayout);
        	{
        		codificaLabel = new Label(opzioni, SWT.NONE);
        		codificaLabel.setText("Codifica");
        	}
    		{
    			comboCodifica = new Combo(opzioni, SWT.READ_ONLY);
    			comboCodifica.setItems(new String[] { "ISO-8859-1", "UTF-8"});
    			comboCodifica.setText("ISO-8859-1");
    			{
    				comboViewer2 = new ComboViewer(comboCodifica, SWT.NULL);
    			}
    			comboCodifica.addSelectionListener(new SelectionAdapter() {
    				public void widgetSelected(SelectionEvent evt) {	
    					String codifica = Settings.getEOL();
    					if (comboCodifica.getText().equals("UTF-8")){
    						codifica = Costants.UTF8;
    					}else{
    						codifica = Costants.ISO88591;	
    					}
    					
    					Settings.setCodifica(codifica);
    					System.out.println("Proprietà settata a "+codifica);
    					
    					
    				}
    			});
    		}
        }

		{
			lineaCapo = new Label(opzioni, SWT.NONE);
			lineaCapo.setText("Linea a Capo");
		}
		{
			comboLineaACapo = new Combo(opzioni, SWT.READ_ONLY);
			comboLineaACapo.setItems(new String[] { "DOS", "UNIX"});
			comboLineaACapo.setText("DOS");
			{
				comboViewer1 = new ComboViewer(comboLineaACapo, SWT.NULL);
			}
			comboLineaACapo.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {	
					String EOL = Settings.getEOL();
					if (comboLineaACapo.getText().equals("DOS")){
						EOL = Costants.dos;
					}else if(comboLineaACapo.getText().equals("UNIX")){
						EOL = Costants.unix;	
					}
					
					Settings.setEOL(EOL);
					System.out.println("Proprietà settata a "+EOL+"A capo");
					
					
				}
			});
		}
		{
			stampaErrLabel = new Label(opzioni, SWT.NONE);
			stampaErrLabel.setText("Stampa Errori");
		}
		{
			handleErrors = new Button(opzioni, SWT.CHECK);
			handleErrors.setSelection(true);
			handleErrors.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					boolean handleErrors = Settings.isHandleErrors();
					Settings.setHandleErrors(!handleErrors);
					System.out.println("Proprietà settata a "+Settings.isHandleErrors());
				}
			});
		}
		{
			label3 = new Label(opzioni, SWT.NONE);
			label3.setText("Gestisci Redefines");
		}
		{
			checkBox = new Button(opzioni, SWT.CHECK);
			checkBox.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					MessageDialog.openError(null, "Errore", "Funzione non implementata");
				}
			});
		}
	
		
		
	}


	private void creaControlliSpeciali(Composite top) {

    	controlliSpeciali = new Group(top, SWT.NONE);
    	GridLayout group1Layout = new GridLayout();
    	group1Layout.numColumns = 2;
    	group1Layout.makeColumnsEqualWidth = true;
    	controlliSpeciali.setLayout(group1Layout);
    	controlliSpeciali.setText("Controlli Speciali");
    	{
    		header = new Label(controlliSpeciali, SWT.NONE);
    		header.setText("Header Presente");
    	}
    	{
    		button1 = new Button(controlliSpeciali, SWT.CHECK);
    		button1.addSelectionListener(new SelectionAdapter() {
    			public void widgetSelected(SelectionEvent evt) {
    				boolean header = Settings.isHeaderPresente();
    				Settings.setHeaderPresente(!header);
    				System.out.println("Proprietà settata a "+Settings.isHeaderPresente());
    				
    			}
    		});
    	}
    	{
    		fillerPresente = new Label(controlliSpeciali, SWT.NONE);
    		fillerPresente.setText("Filler Presente");
    	}
    	{
    		GridData button3LData = new GridData();
    		button3LData.widthHint = 24;
    		button3LData.heightHint = 22;
    		btnFiller = new Button(controlliSpeciali, SWT.CHECK);
    		btnFiller.setLayoutData(button3LData);
    		btnFiller.addSelectionListener(new SelectionAdapter() {
    			public void widgetSelected(SelectionEvent evt) {
    				boolean filler = Settings.isFillerPresente();
    				Settings.setFillerPresente(!filler);
    				System.out.println("Proprietà settata a "+Settings.isFillerPresente());
    				
    			}
    		});
    	}
    	{
    		aggiungi2Indice = new Label(controlliSpeciali, SWT.NONE);
    		aggiungi2Indice.setText("Aggiungi 2 All'indice");
    	}
    	{
    		btnIndice = new Button(controlliSpeciali, SWT.CHECK);
    		btnIndice.setSelection(true);
    		btnIndice.addSelectionListener(new SelectionAdapter() {
    			public void widgetSelected(SelectionEvent evt) {
    				boolean index = Settings.isAdd2ToIndex();
    				Settings.setAdd2ToIndex(!index);
    				System.out.println("Proprietà settata a "+Settings.isAdd2ToIndex());
    				
    			}
    		});
    	}
    

	}


	
	public void creaGruppoCostanti(Composite top){
		{
			costanti = new Group(top, SWT.NONE);
			GridLayout group1Layout1 = new GridLayout();
			
			group1Layout1.numColumns = 2;
			group1Layout1.makeColumnsEqualWidth = true;
			costanti.setLayout(group1Layout1);
			costanti.setText("Opzioni");
			{
				byteLabel = new Label(costanti, SWT.NONE);
				byteLabel.setText("Byte Count");
			}
			{
				txtbyte = new Text(costanti, SWT.NONE);
				txtbyte.setText(Settings.getCount()+"");
				GridData txtbyteLData = new GridData();
				txtbyteLData.horizontalAlignment = GridData.FILL;
				txtbyteLData.grabExcessHorizontalSpace = true;
				txtbyte.setLayoutData(txtbyteLData);
				txtbyte.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {
						
					
					try {
						
						String valore = txtbyte.getText().trim();
						int testo;
						if(!valore.equals("")){
							testo = Integer.parseInt(valore);
						}else{
							testo = 0;
							txtbyte.setText(testo+"");
						}
						
						Settings.setCount(testo);
						System.out.println("Proprietà settata a "+ testo);
					} catch (NumberFormatException e) {
						MessageDialog.openError(new Shell(), "Errore", "Non sono permessi caratteri, solo numeri");
					}

					}
				});
			}
			{
				indexLabel = new Label(costanti, SWT.NONE);
				indexLabel.setText("Spazi Indice");
			}
			{
				spaceIndex = new Text(costanti, SWT.NONE);
				spaceIndex.setText(Settings.getIndexSpaces()+"");
				GridData spaceIndexLData = new GridData();
				spaceIndexLData.horizontalAlignment = GridData.FILL;
				spaceIndexLData.grabExcessHorizontalSpace = true;
				spaceIndex.setLayoutData(spaceIndexLData);
				spaceIndex.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {


						try {
							String valore = spaceIndex.getText().trim();
							int testo;
							if(!valore.equals("")){
								testo = Integer.parseInt(valore);
							}else{
								testo = 0;
								spaceIndex.setText(testo+"");
							}

				Settings.setIndexSpaces(testo);
				System.out.println("Proprietà settata a "+ testo);
						} catch (NumberFormatException e) {
							MessageDialog.openError(new Shell(), "Errore", "Non sono permessi caratteri, solo numeri");
					}

					}
				});
				
			}
			{
				PicLabel = new Label(costanti, SWT.NONE);
				PicLabel.setText("Pic Space");
			}
			{
				scapePic = new Text(costanti, SWT.NONE);
				scapePic.setText(Settings.getPicSpaces()+"");
				GridData scapePicLData = new GridData();
				scapePicLData.horizontalAlignment = GridData.FILL;
				scapePicLData.grabExcessHorizontalSpace = true;
				scapePic.setLayoutData(scapePicLData);
				scapePic.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent evt) {

					try {
						String valore = scapePic.getText().trim();
						int testo;
						
						if(!valore.equals("")){
							testo = Integer.parseInt(valore);
						}else{
							testo = 0;
							scapePic.setText(testo+"");
						}
						
						Settings.setPicSpaces(testo);
						System.out.println("Proprietà settata a "+ testo);
					} catch (NumberFormatException e) {
						MessageDialog.openError(new Shell(), "Errore", "Non sono permessi caratteri, solo numeri");
					}

					}
				});
				
			}
		}
	}
	
}
