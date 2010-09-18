package com.sorca.views;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.sorca.views.utils.GuiUtils;

public class TextDialog extends Dialog {

	private StyledText textArea; 
	private File fileTMP;
	
	@Override
	protected void okPressed() {

		save();
		
		super.okPressed();
	}


	protected TextDialog(Shell parentShell) {
		super(parentShell);
		parentShell.setText("Paste text here");
		parentShell.setSize(800, 600);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		textArea = new StyledText(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		gd.minimumHeight = 600;
		gd.minimumWidth = 800;
		textArea.setLayoutData(gd);
		
		textArea.addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
//		setModified(true);
		}
		});
	
		return parent;
	}


	public void setFileTMP(File fileTMP) {
		this.fileTMP = fileTMP;
	}

	public File getFileTMP() {
		return fileTMP;
	}
	

		private void save() {

			String tempDir = GuiUtils.getRoot()+"sorcaTMP.txt";

		if("".equals(textArea.getText())) return;

		File f = new File(tempDir);
		try {

		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(textArea.getText());
		bw.close();
		
		fileTMP = f;
		} catch (IOException e) {
		e.printStackTrace();
		}
		}



}
