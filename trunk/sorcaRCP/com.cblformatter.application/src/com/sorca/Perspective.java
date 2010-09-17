package com.sorca;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		 String editorArea = layout.getEditorArea();
		 layout.setEditorAreaVisible(false);
		 layout.setFixed(true);
		 
		 
//		    layout.addStandaloneView("CBLFormatter.ProcessView", false, 
//		            IPageLayout.BOTTOM, 0.8f, editorArea);
//		    
//		    layout.addStandaloneView("CBLFormatter.FilesView", false, 
//		            IPageLayout.BOTTOM, 0.8f, editorArea);
			
		    layout.addStandaloneView("CBLFormatter.OptionView", false, 
		            IPageLayout.RIGHT, 0.7f, editorArea);

		    layout.addStandaloneView("CBLFormatter.EditView", false, 
		            IPageLayout.LEFT, 0.7f, editorArea);
		   
		   

		}
}
