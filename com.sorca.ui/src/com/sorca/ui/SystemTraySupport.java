package com.sorca.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

import com.sorca.views.utils.GuiUtils;

public class SystemTraySupport {

	static Shell shell = new Shell();
	static SelectionAdapter listener = null;
	
	public SystemTraySupport(){

		if(!trayItemsExist()){

			Display display = (Display) GuiUtils.getDisplay();
			TrayItem trayItem = new TrayItem(
					display.getSystemTray(), SWT.NONE);
			trayItem.setImage(new Image(display, GuiUtils.getRoot()+"/icons/sorca.ico"));
			trayItem.setToolTipText("SORCA");
		}

	}

	private boolean trayItemsExist() {
		Display display = (Display)GuiUtils.getDisplay();
		return display.getSystemTray().getItems().length >=1;
	}

	public void updateTrayMessage(String toolTip,String bodyMessage){

		//TODO aggiungere nuovo TrayItem, con notifica, al click il tray scompare.
		
		Display display = (Display)GuiUtils.getDisplay();

		TrayItem item = null;
		final ToolTip tip = new ToolTip(shell, SWT.BALLOON | SWT.ICON_INFORMATION);
		tip.setMessage(bodyMessage);
		Tray tray = display.getSystemTray();

		TrayItem[] itemArray = tray.getItems();
		if(itemArray.length >=1){
			item = itemArray[0];
			ToolTip tmpTip = item.getToolTip();
			if(tmpTip != null){
				tmpTip.setVisible(false);
				tmpTip.removeSelectionListener(listener);
	
//				tmpTip.dispose();
			}
		}else{
		item = new TrayItem(tray, SWT.NONE);
		}

		tip.setText(toolTip);
		tip.setAutoHide(true);
		
		item.setToolTip(tip);

		tip.setVisible(true);
		listener = new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {

				tip.setVisible(true);
			}
		};
		
		
		item.addSelectionListener(listener);

	}


}
