package com.cblformatter.views.utils;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import com.cblformatter.model.beans.ModelObject;
import com.cblformatter.views.EditView;

public class GuiUtils {

	
	public static ViewPart getView(String ID){
		ViewPart view = null;
		try{
			view = (ViewPart) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ID);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	public static Device getDisplay() {
		return PlatformUI.getWorkbench().getDisplay();
	}

	public static void showInfo(String message, int style) {
		MessageBox msg = new MessageBox(new Shell(),style);
		msg.setMessage(message);
		msg.open();
		
	}

//	public static TableViewer getManagerTableViewer() {
//		// TODO Auto-generated method stub
//		return 	((ManagerView)GuiUtils.getView(ManagerView.ID)).getTv();
//	}

	public static boolean showConfirmDialog(String message) {
		boolean ret =  MessageDialog.openConfirm(new Shell(), "Confirm", message);

		return ret;
	}

	public static IHandlerService getHandlerService(String viewID) {
		try {
		IHandlerService handlerService = (IHandlerService) GuiUtils.getView(viewID).getSite()
			.getService(IHandlerService.class);

		return handlerService;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static void addBindingContext(Control widget, ModelObject bean, String valueToBind) {
		
		if(widget instanceof Button ){
			
			DataBindingContext dbc = new DataBindingContext();
			dbc.bindValue(SWTObservables.observeSelection(widget),
					BeansObservables.observeValue(bean,valueToBind),null,null);
			 
			}
		
		if(widget instanceof Combo){
		
		DataBindingContext dbc = new DataBindingContext();
		 dbc.bindValue(SWTObservables.observeSelection(widget),
			 BeansObservables.observeValue(bean,valueToBind),null,null);
		 
		}
		
		if(widget instanceof Label ){
			
			DataBindingContext dbc = new DataBindingContext();
			dbc.bindValue(SWTObservables.observeText(widget),
					BeansObservables.observeValue(bean,valueToBind),null,null);
			 
			}
		
		
		if(widget instanceof Text ){
			
			DataBindingContext dbc = new DataBindingContext();
			dbc.bindValue(SWTObservables.observeText(widget,SWT.Modify),
					BeansObservables.observeValue(bean,valueToBind),null,null);
			 
			}
		
		
	}

	public static void showError(String string) {
		MessageDialog.openError(new Shell(), "Confirm", string);
	}

	public static void showInfo(String message, String string) {
		showInfo(message,SWT.NONE);
	}

	public static  TreeViewer getEditViewTableViewer() {
		// TODO Auto-generated method stub
		return ((EditView)GuiUtils.getView(EditView.ID)).getTv();
	}
	
}
