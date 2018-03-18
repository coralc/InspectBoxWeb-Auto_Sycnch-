 package com.inspectbox.web;
 
 import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.inspectbox.databaseLayout.InspectionDatabaseLayout;
import com.inspectbox.databaseLayout.LibelleNiveauDatabaseLayout;
import com.inspectbox.model.Inspection;
import com.inspectbox.model.Libelleniveau;
import com.inspectbox.model.Utilisateur;
import com.inspectbox.objetLayout.ObjetInspection;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="inspectionBean")
 @ViewScoped
 public class InspectionBean
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idNiveau;
   private Integer idUtilisateur;
   private String clefDate;
   private String date;
   private String utilisateur;
   private String niveauInitial;
   private List<Libelleniveau> listeLibelleNiveau;
   private List<Libelleniveau> listeLibelleObjet;
   private StreamedContent fileCsv;
   private StringBuffer inspectionCSVtext;
  private List<ObjetInspection> listeMapInspection = new ArrayList();
  private Integer idNiveauBatiment;
  private Integer idNiveauLocalisation;
  private Integer idNiveauObjet;
	private String nomcsv	;
 
   public String getNomcsv() {
		return nomcsv;
	}

	public void setNomcsv(String nomcsv) {
		this.nomcsv = nomcsv;
	}

public List<ObjetInspection> getListeMapInspection()
   {
     return this.listeMapInspection; }
 
   public void setListeMapInspection(List<ObjetInspection> listeMapInspection) {
     this.listeMapInspection = listeMapInspection; }
 
   public Integer getIdNiveau() {
    return this.idNiveau; }
 
   public void setIdNiveau(Integer idNiveau) {
    this.idNiveau = idNiveau; }
 
   public Integer getIdUtilisateur() {
     return this.idUtilisateur; }
 
   public void setIdUtilisateur(Integer idUtilisateur) {
     this.idUtilisateur = idUtilisateur; }
 
   public String getClefDate() {
     return this.clefDate; }
 
   public void setClefDate(String clefDate) {
     this.clefDate = clefDate; }
 
   public String getDate() {
     return this.date; }
 
   public void setDate(String date) {
     this.date = date; }
 
   public String getUtilisateur() {
     return this.utilisateur; }
 
   public void setUtilisateur(String utilisateur) {
     this.utilisateur = utilisateur; }
 
   public String getNiveauInitial() {
     return this.niveauInitial; }
 
   public void setNiveauInitial(String niveauInitial) {
     this.niveauInitial = niveauInitial; }
 
   public List<Libelleniveau> getListeLibelleNiveau() {
     return this.listeLibelleNiveau; }
 
   public void setListeLibelleNiveau(List<Libelleniveau> listeLibelleNiveau) {
     this.listeLibelleNiveau = listeLibelleNiveau; }
 
   public List<Libelleniveau> getListeLibelleObjet() {
     return this.listeLibelleObjet; }
 
   public void setListeLibelleObjet(List<Libelleniveau> listeLibelleObjet) {
     this.listeLibelleObjet = listeLibelleObjet;
   }
 
   public StreamedContent getFileCsv() {
     _creerCSV();
     return this.fileCsv; }
 
   public void setFileCsv(StreamedContent fileCsv) {
     this.fileCsv = fileCsv;
   }


			public Integer getIdNiveauBatiment() {
				return idNiveauBatiment;
			}
			public void setIdNiveauBatiment(Integer idNiveauBatiment) {
				this.idNiveauBatiment = idNiveauBatiment;
			}
			public Integer getIdNiveauLocalisation() {
				return idNiveauLocalisation;
			}
			public void setIdNiveauLocalisation(Integer idNiveauLocalisation) {
				this.idNiveauLocalisation = idNiveauLocalisation;
			}						
			public Integer getIdNiveauObjet() {
				return idNiveauObjet;
			}
			public void setIdNiveauObjet(Integer idNiveauObjet) {
				this.idNiveauObjet = idNiveauObjet;
			}
 
   public String consulter(Integer idNiveau, Integer idUtilisateur, String clefDate)
   {
     this.idNiveau = idNiveau;
    this.idUtilisateur = idUtilisateur;
     this.clefDate = clefDate;
				String[] str = clefDate.split("-");
     this.date = str[2] + "/" + str[1] + "/" + str[0];
 
  return "consulter_inspection?faces-redirect=true&idNiveau=" + this.idNiveau + "&date=" + this.clefDate;
  }

   public String consulter(Integer idNiveau, Integer idUtilisateur, String clefDate, Integer idbatiment, Integer idLocalisation, Integer idObjet)
   {
			     this.idNiveau = idNiveau;
     this.idUtilisateur = idUtilisateur;
     this.clefDate = clefDate;
			  this.idNiveauBatiment=idbatiment;
			  this.idNiveauLocalisation=idLocalisation;
			  this.idNiveauObjet=idObjet;
 
     String[] str = clefDate.split("-");
     this.date = str[2] + "/" + str[1] + "/" + str[0];
 			System.out.println(" ***************************exiting from consulter Step 2");

    return "consulter_inspection?faces-redirect=true&idNiveau=" + this.idNiveau + "&date=" + this.clefDate + "&idBatiment=" + this.idNiveauBatiment + "&idLocalisation=" + this.idNiveauLocalisation+ "&idObjet=" + this.idNiveauObjet; 
}

   public String consulter(Integer idNiveau, String clefDate) {
     return consulter(idNiveau, Integer.valueOf(0), clefDate);
   }
 
   

   public void afficher()
   		{
				try {
					
				
     LoginBean bean = LoginUtil.getLoginBean();
 
   if (this.clefDate.split("-").length == 3)
     {
      String[] str = this.clefDate.split("-");
       this.date = str[2] + "/" + str[1] + "/" + str[0];
     }
 
    LibelleNiveauDatabaseLayout libelleNiveau = null;
     try {
       libelleNiveau = new LibelleNiveauDatabaseLayout();
       libelleNiveau.choisirTypeNiveau();
       libelleNiveau.lister(bean.getIdClient());
       this.listeLibelleNiveau = libelleNiveau.liste();
     } catch (Exception e) {
    	 e.printStackTrace();
     }
     finally
     {
       libelleNiveau.close();
     }
     try
     {
       libelleNiveau = new LibelleNiveauDatabaseLayout();
       libelleNiveau.choisirTypeObjet();
       libelleNiveau.lister(bean.getIdClient());
       this.listeLibelleObjet = libelleNiveau.liste();
     } catch (Exception e) {
    	 e.printStackTrace();
     }
     finally
     {
       libelleNiveau.close();
     }
 
     InspectionDatabaseLayout inspection = null;
    this.listeMapInspection.clear();
				if ( this.idNiveauBatiment >0 || this.idNiveauLocalisation>0){
					prepareInspectionList(bean.getIdClient());
					return;
				}
     try
     {SimpleDateFormat formater = null;
    	 formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
      inspection = new InspectionDatabaseLayout();
 
       inspection.lister(bean.getIdClient(), this.clefDate);
 
      Iterator it = inspection.liste().iterator();
 		
				
       List listeNiveauInitialDejaAffiche = new ArrayList();
       while (it.hasNext())
       {
         Inspection inspect = (Inspection)it.next();
          if (!(inspect.getNiveau().getNiveau().getNiveau().getIdNiveau().equals(this.idNiveau)))
           continue;
        if (this.utilisateur == null) {
           this.utilisateur = inspect.getUtilisateur().getPrenom() + " " + inspect.getUtilisateur().getNom();
         }
 
         if (this.niveauInitial == null) {
          this.niveauInitial = inspect.getNiveau().getNiveau().getNiveau().getLibelle();
         }
 
         ObjetInspection objetInepection = new ObjetInspection();
        objetInepection.setIdInspection(inspect.getIdInspection());
        objetInepection.setIdiniformation(inspect.getIdInformation());
         objetInepection.setNiveau2Libelle(inspect.getNiveau().getNiveau().getNiveau().getLibelle());
         objetInepection.setNiveau3Libelle(inspect.getNiveau().getNiveau().getLibelle());
         objetInepection.setNiveau4Libelle(inspect.getNiveau().getLibelle());
         objetInepection.setObjet2Libelle(inspect.getNiveauobjet().getLibelle());
         objetInepection.setDefect(inspect.isDefekt());
 			if(inspect.getChoix()!=null)
         		objetInepection.setChoix(inspect.getChoix().getValeur());
						else
						objetInepection.setChoix(inspect.getReponse());
        
         
 		  Utilisateur operateur = inspect.getUtilisateur();
 		  if ((operateur.getPrenom()==null) && (operateur.getNom()==null))
 			 objetInepection.setUtilisateur(operateur.getCodeAcces());
 		  else
 			   objetInepection.setUtilisateur(operateur.getPrenom()+" "+operateur.getNom());
				  objetInepection.setInspectioneDate(inspect.getDateInformation());
				  objetInepection.setIdTerminal(inspect.getIdTerminal());
				  objetInepection.setDateins(formater.format(inspect.getDateInformation()));
         this.listeMapInspection.add(objetInepection);
	  			 
       }
     }
     catch (Exception e) {
    	 	e.printStackTrace();
     }
     finally
     {
       inspection.close();
       this.nomcsv=this.niveauInitial + "_" + this.clefDate ;
     }
				} catch (Exception ex){
					ex.printStackTrace();
				}
				

   }

		private void prepareInspectionList(Integer idClient){
			InspectionDatabaseLayout inspection = null;
		     this.listeMapInspection.clear();
		     try
		     {
		       inspection = new InspectionDatabaseLayout();
		     if ( this.idNiveauBatiment >0 || this.idNiveauLocalisation>0){
		    	 
					if (this.idNiveauLocalisation>0){
						if (this.idNiveauObjet>0)
							inspection.listerFilter(idClient,  this.clefDate, idNiveauLocalisation, false, this.idNiveauObjet);
						else
							inspection.listerFilter(idClient,  this.clefDate, idNiveauLocalisation, false, -99);
					}						
					else
						inspection.listerFilter(idClient,  this.clefDate, idNiveauBatiment, true, -99);
					
		       Iterator it = inspection.liste().iterator();
		       while (it.hasNext())
		       {
		         Inspection inspect = (Inspection)it.next();
		        if (this.utilisateur == null) {
		           this.utilisateur = inspect.getUtilisateur().getPrenom() + " " + inspect.getUtilisateur().getNom();
		        	}
		         if (this.niveauInitial == null) {
		          this.niveauInitial = inspect.getNiveau().getNiveau().getNiveau().getNiveau().getLibelle();
		         	}		 
		        ObjetInspection objetInepection = new ObjetInspection();
		         objetInepection.setIdInspection(inspect.getIdInspection());
		         objetInepection.setNiveau2Libelle(inspect.getNiveau().getNiveau().getNiveau().getLibelle());
		         objetInepection.setNiveau3Libelle(inspect.getNiveau().getNiveau().getLibelle());
		         objetInepection.setNiveau4Libelle(inspect.getNiveau().getLibelle());
		 
		         objetInepection.setObjet1Libelle(inspect.getNiveauobjet().getNiveauobjet().getLibelle());
		         objetInepection.setObjet2Libelle(inspect.getNiveauobjet().getLibelle());
		         objetInepection.setDefect(inspect.isDefekt());
		 			if(inspect.getChoix()!=null)
		         		objetInepection.setChoix(inspect.getChoix().getValeur());
						else
						objetInepection.setChoix(inspect.getReponse());
		        
		         objetInepection.setIdTerminal(inspect.getIdTerminal());
		         
		        
		         Utilisateur operateur = inspect.getUtilisateur();
		         if ((operateur.getPrenom()==null) && (operateur.getNom()==null))
		 			 objetInepection.setUtilisateur(operateur.getCodeAcces());
		         else objetInepection.setUtilisateur(operateur.getPrenom()+" "+operateur.getNom());
		         objetInepection.setInspectioneDate(inspect.getDateInformation());
		         
		         this.listeMapInspection.add(objetInepection);
			  			  }
		     }
		     }
		    catch (Exception e) {
		       System.out.println("Erreur liste inspection");
		       e.printStackTrace();
		     }
		     finally
		     {
		    	 if (inspection!=null)
		    		 inspection.close();
		     }				
			}
		

			private void _creerCSV()
   {
	
	
     String sautLigne = "\n";
    String separateur = ";";
     String separateurMot = "\"";
     this.inspectionCSVtext = new StringBuffer();
 
    this.inspectionCSVtext.append(separateurMot + I18nUtil.get("page_consulter_inspection_date_csv") + separateurMot + separateur);
     this.inspectionCSVtext.append(separateurMot + I18nUtil.get("page_consulter_inspection_hour_csv") + separateurMot + separateur);
     this.inspectionCSVtext.append(separateurMot + I18nUtil.get("page_consulter_inspection_operateur_csv") + separateurMot + separateur);
     this.inspectionCSVtext.append(separateurMot + ((Libelleniveau)this.listeLibelleNiveau.get(0)).getLibelle() + separateurMot + separateur);
     this.inspectionCSVtext.append(separateurMot + ((Libelleniveau)this.listeLibelleNiveau.get(1)).getLibelle() + separateurMot + separateur);
     this.inspectionCSVtext.append(separateurMot + ((Libelleniveau)this.listeLibelleNiveau.get(2)).getLibelle() + separateurMot + separateur);
     this.inspectionCSVtext.append(separateurMot + ((Libelleniveau)this.listeLibelleObjet.get(0)).getLibelle() + separateurMot + separateur);
    
    this.inspectionCSVtext.append(separateurMot + I18nUtil.get("page_consulter_inspection_resultat") + separateurMot );

  this.inspectionCSVtext.append(sautLigne);
 
     Iterator it = this.listeMapInspection.iterator();
     while (it.hasNext()) {
       ObjetInspection objet = (ObjetInspection)it.next();
				SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
				String hour = fmt.format(objet.getInspectioneDate());
       this.inspectionCSVtext.append(separateurMot + this.date + separateurMot + separateur);
       this.inspectionCSVtext.append(separateurMot + hour + separateurMot + separateur);
       this.inspectionCSVtext.append(separateurMot + objet.getUtilisateur() + separateurMot + separateur);
       this.inspectionCSVtext.append(separateurMot + this.niveauInitial + separateurMot + separateur);
 
       this.inspectionCSVtext.append(separateurMot + objet.getNiveau2Libelle() + separateurMot + separateur);
       this.inspectionCSVtext.append(separateurMot + objet.getNiveau3Libelle() + separateurMot + separateur);
       this.inspectionCSVtext.append(separateurMot + objet.getNiveau4Libelle() + separateurMot + separateur);
       this.inspectionCSVtext.append(separateurMot + objet.getObjet2Libelle() + separateurMot + separateur);
 this.inspectionCSVtext.append(separateurMot + objet.getChoix() + separateurMot );
  this.inspectionCSVtext.append(sautLigne);
     }
 
     try
     {
       InputStream stream = new ByteArrayInputStream(this.inspectionCSVtext.toString().getBytes("ISO-8859-1"));
      this.fileCsv = new DefaultStreamedContent(stream, "application/csv", this.niveauInitial + "_" + this.clefDate + ".csv");
     } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
     }
   }
 
   
 }

