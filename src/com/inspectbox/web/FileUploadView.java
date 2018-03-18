package com.inspectbox.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import com.inspectbox.databaseLayout.ImportDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;

@ManagedBean(name="fileUploadView")

public class FileUploadView implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String destination="C:\\InspectBox\\Input\\";
	private String destination="/usr/share/InspectBox/Input/";
	
    public void handleFileUpload(FileUploadEvent event) throws IOException
    {
    	// copy the file in usr/share
    	
    	ImportDatabaseLayout impDB = new ImportDatabaseLayout();
        impDB.transactionOpen();
        try{
        	 if(event != null) {
        		 
        		 
        		    copyFile(FilenameUtils.getName(event.getFile().getFileName()), event.getFile().getInputstream());
                 impDB.model().setFilename(FilenameUtils.getName(event.getFile().getFileName()));
                 impDB.model().setTimestamp(new Date());
                 impDB.model().setStatus(0);
                 impDB.save();
                 impDB.transactionCommit();
                 
                 LoginBean bean = LoginUtil.getLoginBean();

              // save synchro 
              TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
              	          termDB.transactionOpen();
              	           

              	          
              	          termDB.listerAll();
              	          Iterator it = termDB.liste().iterator();
              	          while (it.hasNext())
              		        {
              		          Terminal term = (Terminal)it.next();
              		           SynchDatabaseLayout synchdb = null;
              			 		synchdb = new SynchDatabaseLayout();
              			 		synchdb.transactionOpen();
              		          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
              		          if (synchdb.model()!=null)//MAJ
              		          { if (synchdb.model().getEtat()==0)
              		          {
              		        	  synchdb.model().setEtat(1);
              		        	  synchdb.model().setDateInformation(new Date());
              		        	  synchdb.update();
              		        	  synchdb.transactionCommit();
              		          }
              		        	  
              		          }
              		          else // creation
              		          {
              		        	  SynchDatabaseLayout synchdbnw = null;
              		        	  synchdbnw = new SynchDatabaseLayout();
              		        	  synchdbnw.transactionOpen();
              		        	  SynchPK synchpk=new SynchPK();
              		        	  synchpk.setIdClient(bean.getIdClient());
              		        	  synchpk.setIdTerminal(term.getIdTerminal());
              		        	  synchdbnw.model().setEtat(1);
              		        	  synchdbnw.model().setDateInformation(new Date());
              		        	  synchdbnw.model().setSynchPK(synchpk);
              		        	  synchdbnw.save();
              		        	  synchdbnw.transactionCommit();
              		          }
              		          
              		        } 
              	     termDB.close();     
                 FacesMessage message = new FacesMessage(I18nUtil.get("Succesful"), event.getFile().getFileName() +I18nUtil.get("isuploaded") );
                 FacesContext.getCurrentInstance().addMessage(null, message);
                 
                 
             }
        	 else
        	 {
        		 FacesMessage message = new FacesMessage(I18nUtil.get("notSuccesful"), event.getFile().getFileName()  );
                 FacesContext.getCurrentInstance().addMessage(null, message);
        	 }
        }
        catch (IOException e) {
            System.out.println("Erreur d'ajout");
       e.printStackTrace();
        
            
            } finally {
            	impDB.close();
             
            }
        
        
       
    }
    public void copyFile(String fileName, InputStream in) {
        try {
           
           
        	
             // write the inputStream to a FileOutputStream
             OutputStream out = new FileOutputStream(new File(destination + fileName));
           
             int read = 0;
             byte[] bytes = new byte[1024];
           
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
           
             in.close();
             out.flush();
             out.close();
           
             System.out.println("New file created!");
             } catch (IOException e) {
             e.printStackTrace();
             }
 }
}
