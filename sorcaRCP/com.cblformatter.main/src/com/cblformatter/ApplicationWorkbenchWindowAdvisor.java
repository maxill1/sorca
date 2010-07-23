package com.cblformatter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.update.configuration.IConfiguredSite;
import org.eclipse.update.configuration.ILocalSite;
import org.eclipse.update.core.IFeatureReference;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.SiteManager;
import org.eclipse.update.operations.IInstallFeatureOperation;
import org.eclipse.update.operations.IUninstallFeatureOperation;
import org.eclipse.update.operations.OperationsManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

import com.cblformatter.model.beans.Model;
import com.cblformatter.views.utils.GuiUtils;

@SuppressWarnings("deprecation")
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    @Override
	public void postWindowOpen() {
		super.postWindowOpen();
		
		//TODO UPDATE SITE!
		
		
		/*
		 * caricamento delle proprieta' dal file sanzioni.ini
		 */
		String nomeFileProps = "sorca.ini";
		String updateServer  = null;
		try {

			Properties props = new Properties();
			
			Bundle bundle = Platform.getBundle("com.cblformatter.application");
			
			props.load(bundle.getEntry("/" + nomeFileProps).openStream());
			
			//local or remote to switch update server
			String key = "sorca.env";
			System.setProperty(key, props.getProperty(key));
			
			// flag per l'update
			boolean flagUpdate = props.getProperty("sorca.update").equals("true");
			
			if (flagUpdate) {
				
				if(GuiUtils.showConfirmDialog("Cercare aggiornamenti?")){
					
					updateServer = props.getProperty("sorca.update.server");
					
					update(updateServer);
				}
				
			}
			
		} catch (IOException e) {
			GuiUtils.showError("Errore durante il caricamento del file \n" + nomeFileProps);
			PlatformUI.getWorkbench().close();
		}
		
	}

	
	
	private void update(String updateServer) {
		
		//GuiManagementUtil.showInfo("Verrà ora controllata la presenza di aggiornamenti.");
		
		//TODO	
		final String site = updateServer;
		
    	try {
			
			IRunnableWithProgress op = new IRunnableWithProgress (){
			
				 
				public void run(IProgressMonitor monitor) throws 
			
			    	InvocationTargetException, InterruptedException {
					
									
			    	try {
			    		//Sito remoto
			    		ISite remotes = SiteManager.getSite(
			    				new URL(site)
			    		, 
			    		monitor);
			    		
			    		//feature remote
			    		IFeatureReference [] remoteFeatures 
			    			= remotes.getFeatureReferences();
			    		
			    		//sito Locale
			    		ILocalSite ls = SiteManager.getLocalSite();
			    		
			    		IConfiguredSite localSite = 
			    			ls.getCurrentConfiguration().getConfiguredSites()[0];
			    		
			    		//feature Locali
			    		IFeatureReference [] 
			    		     localFeatures = localSite.getConfiguredFeatures();
			    		//Istanzio liste oggetti da installare e disinstallare 
			    		List<IInstallFeatureOperation> installOps 	= new ArrayList<IInstallFeatureOperation> ();
			    		List<IUninstallFeatureOperation> disinstallOps 	= new ArrayList<IUninstallFeatureOperation> ();

			    		for(int i = 0; i < remoteFeatures.length; i++) {			    		
			    			System.out.println("REMOTE " + remoteFeatures[i].getVersionedIdentifier());			    		
			    		}
			    		
			    		for(int i = 0; i < localFeatures.length; i++) {			    		
			    			System.out.println("LOCAL " + localFeatures[i].getVersionedIdentifier());			    		
			    		}
			    			
			    		//Scorre ogni feature remota
			    		for(int i = 0; i < remoteFeatures.length; i++) {
			    			
			    			//Recupera versione feature remota
			    			Version rv = 
			    				new Version(
			    					remoteFeatures[i].
			    					getVersionedIdentifier().
			    					getVersion().
			    					toString()
			    				);
			    			
			    			boolean found = false;
			    			
			    			//Scorre ogni feature locale
			    			for (int j = 0; j < localFeatures.length; j++) {
			    				
			    				//Recupera versione feature locale
				    			Version lv = 
				    				new Version(
				    					localFeatures[j].
				    					getVersionedIdentifier().getVersion().
				    					toString()
				    				);
				    			
				    			//controlla se stessa versone feature Remoto e Locale
			    				if (remoteFeatures[i].getVersionedIdentifier().getIdentifier().equals(
			    						localFeatures[j].getVersionedIdentifier().getIdentifier())){
			    					
			    					//trovata nuova versione 
			    					found = true;
			    					
			    					
			    					System.out.println("found  "  + remoteFeatures[i].getVersionedIdentifier());
			    					System.out.println("local  "  + lv);
			    					System.out.println("remote "  + rv);
			    					System.out.println("compare " + rv.compareTo(lv));
			    					
			    					//Se versione remota è più recente di locale
			    					if (rv.compareTo(lv)>0){
			    						
			    						
			    						GuiUtils.showInfo("Nuova Versione Feature : " +
			    								remoteFeatures[i].getVersionedIdentifier(), "Nuova Feature");
			    						
			    						//Aggiungi a Lista installazione nuova versione
			    						installOps.add(			    								
			    							OperationsManager.getOperationFactory()
			    							.createInstallOperation(
			    								localSite,
			    								remoteFeatures[i].getFeature(
			    								monitor
			    								), null, null, null)
			    						);
			    						
			    						//Aggiungi a Lista disinstallazione vecchia versione
			    						disinstallOps.add(			    								
				    							OperationsManager.getOperationFactory()
				    							.createUninstallOperation(				    								
				    								localSite,
				    								localFeatures[j].getFeature(
				    								monitor
				    								))
				    					);
			    						
			    					}
			    				}
			    			}
			    			
			    			//Se non trova una nuova versione allora è nuova Feature
			    			if (!found){
			    				
			    				GuiUtils.showInfo("Nuova Feature : " +remoteFeatures[i].getVersionedIdentifier(),"Nuova Feature");
			    				
			    				//Aggiungi a Lista installazione
	    						installOps.add(			    								
		    							OperationsManager.getOperationFactory()
		    							.createInstallOperation(
		    								localSite,
		    								remoteFeatures[i].getFeature(
		    								monitor
		    								), null, null, null)
		    						);			    				
			    				
			    			}
			    		}
			    		
			    		/////////////////
			    		
			    		//Scorro le features locali
			    		for(int i = 0; i < localFeatures.length; i++) {
	
		    			
			    			
			    			boolean found = false;
			    			
			    			//scorro feature remote
			    			for (int j = 0; j < remoteFeatures.length; j++) {
			    				

				    			
			    				//controlla se stessa versone feature Remoto e Locale
			    				if (localFeatures[i].getVersionedIdentifier().getIdentifier().equals(
			    						remoteFeatures[j].getVersionedIdentifier().getIdentifier())){
			    					//Stessa feature
			    					found = true;
			    								    					
			    		
			    				}
			    			}
			    			
			    			//Se non è stessa feature
			    			if (!found){
			    				
			    				GuiUtils.showInfo("Feature da rimuovere: " +localFeatures[i].getVersionedIdentifier(),"Feature da rimuovere");
			    				
	    						/*installOps.add(			    								
		    							OperationsManager.getOperationFactory()
		    							.createInstallOperation(
		    								localSite,
		    								remoteFeatures[i].getFeature(
		    								monitor
		    								), null, null, null)
		    						);			    				
			    				*/
			    				
			    				//Aggiungo a lista da disinstallare
	    						disinstallOps.add(			    								
		    							OperationsManager.getOperationFactory()
		    							.createUninstallOperation(				    								
		    								localSite,
		    								localFeatures[i].getFeature(
		    								monitor
		    								))
		    					);
	    						
			    			}
			    		
			    		}

			    		
			    		////////////////
			    		
			    		
			    		//Se la lista delle installazioni  o delle disinstallazioni presenta degli oggetti
			    		if (installOps.size()> 0 ||disinstallOps.size() > 0) {
			    		
			    			//Scorri la lista installazioni
			    			for (Iterator iter = installOps.iterator(); iter.hasNext();) {
			    				
			    				//Installa oggetto
			    				IInstallFeatureOperation op = (IInstallFeatureOperation) 
			    				
			    				iter.next();
			    				
			    				System.out.println("INSTALL " 
			    						+ op.getFeature().getVersionedIdentifier().getIdentifier());
			    				
			    				op.execute(monitor, null);
			    				
			    				
			    				
			    			}
			    			
			    			//Scorri la lista disinstallazioni
			    			for (Iterator iter = disinstallOps.iterator(); iter.hasNext();) {
			    				
			    				//Disinstalla oggetto
			    				IUninstallFeatureOperation op = 
			    					(IUninstallFeatureOperation) 
			    				iter.next();
			    				
			    				System.out.println("DISINSTALL " 
			    						+ op.getFeature().getVersionedIdentifier().getIdentifier());
			    				
			    				
			    				op.execute(monitor, null);
			    			}
			    			
			    			
			    			//Salva il LOCALSITE
			    			ls.save();
			    			
			    			GuiUtils.showInfo("Aggiornamento effettuato","Info");
			    			
			    			PlatformUI.getWorkbench().restart();
			    			
			    		} else {
			  
    						GuiUtils.showInfo("Non sono presenti aggiornamenti","Info");
    						
			    			monitor.setTaskName("Non sono presenti aggiornamenti");
			    			
			    			//GuiUtils.showInfo(MessagesAcceUnif.getString("noUpdates"));
			    			
			    			//Thread.sleep(5000);
			    		
			    		}
			    		
			    	} catch (MalformedURLException e) {
			    	
//			    		e.printStackTrace();
			    		GuiUtils.showError("Errore nell'aggiornamento del software : \n"+e.getMessage());
//			    		PlatformUI.getWorkbench().close();
			    		
			    		
			    	} catch (CoreException e) {
			    	
//			    		e.printStackTrace();
			    		GuiUtils.showError("Non è disponibile il server per la verifica degli aggiornamenti.");
//			    		PlatformUI.getWorkbench().close();
			    		
			    	}
			    	 catch (Exception e) {
				    	
//			    		e.printStackTrace();
			    		GuiUtils.showError("Errore nell'aggiornamento del software : \n"+e.getMessage());
//			    		PlatformUI.getWorkbench().close();
			    		
			    	}
			    }
			  };
			  
			  Display display =  Display.getCurrent();
			  
			  ProgressMonitorDialog pmd = 
				  new ProgressMonitorDialog(display.getActiveShell());			  
			  
			  pmd.run(true, false, op);
			  
    	} catch (InvocationTargetException e) {
    		
    		e.printStackTrace();
    		
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}    	
		
	
		
	}

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        
        //INIT DEL MODEL
        new Model();
        
        configurer.setInitialSize(new Point(800, 600));
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(false);
        configurer.setTitle("SORCA - Simple Opensource formatter for Raw Cobol Area");
        
    }
}
