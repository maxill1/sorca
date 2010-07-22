package com.cblformatter.views;

import javax.swing.table.TableColumn;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;


public class PreView {
	
	public static void createPreview(){
//		Display display = new Display ();

		Shell shell = new Shell();
		
		Table table = new Table (shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;

		String[] titles = {" ", "C", "!", "Description", "Resource", "In Folder", "Location"};
		for (int i=0; i<titles.length; i++) {
			org.eclipse.swt.widgets.TableColumn column = new org.eclipse.swt.widgets.TableColumn(table, SWT.NONE) ;
			column.setText (titles [i]);
		}	

		int count = 128;
		for (int i=0; i<count; i++) {
			TableItem item = new TableItem (table, SWT.NONE);
			item.setText (0, "x");
			item.setText (1, "y");
			item.setText (2, "!");
			item.setText (3, "this stuff behaves the way I expect");
			item.setText (4, "almost everywhere");
			item.setText (5, "some.folder");
			item.setText (6, "line " + i + " in nowhere");
		}
		for (int i=0; i<titles.length; i++) {
			table.getColumn (i).pack ();
		}	
		
		table.setLayoutData(data);
		//shell.pack ();
		shell.open ();
//		while (!shell.isDisposed ()) {
//			if (!display.readAndDispatch ()) display.sleep ();
//		}
//		display.dispose ();

	}

}
