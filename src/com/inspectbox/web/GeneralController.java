package com.inspectbox.web;
import java.io.Serializable;  
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.databaseLayout.ObjeteamDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.databaseLayout.UtilisateurDatabaseLayout;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.model.Objeteam;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.model.Typereponse;
import com.inspectbox.objetLayout.ObjetNiveauObjet;
import com.inspectbox.objetLayout.ObjetUtilisateur;
import com.inspectbox.objetLayout.ObjetVue;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
import com.inspectbox.utils.Themed;
@ManagedBean(name="generalController")
 @ViewScoped
 @SessionScoped
public class GeneralController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idNiveauChoisi;
	private int objeteamChoisi;
	 public int getObjeteamChoisi() {
		return objeteamChoisi;
	}

	public void setObjeteamChoisi(int objeteamChoisi) {
		this.objeteamChoisi = objeteamChoisi;
	}

	private ArrayList<SelectItem> niveauList = new ArrayList();
	 private DualListModel<ObjetUtilisateur> teams;  
	 public DualListModel<ObjetUtilisateur> getTeams() {
			return teams;
		}

		public void setTeams(DualListModel<ObjetUtilisateur> teams) {
			this.teams = teams;
		}
		
		 private List<ObjetUtilisateur> citiesSource = new ArrayList<ObjetUtilisateur>();  
		  private   List<ObjetUtilisateur> citiesTarget = new ArrayList<ObjetUtilisateur>();
		  
    public List<ObjetUtilisateur> getCitiesSource() {
			return citiesSource;
		}

		public void setCitiesSource(List<ObjetUtilisateur> citiesSource) {
			this.citiesSource = citiesSource;
		}

		public List<ObjetUtilisateur> getCitiesTarget() {
			return citiesTarget;
		}

		public void setCitiesTarget(List<ObjetUtilisateur> citiesTarget) {
			this.citiesTarget = citiesTarget;
		}

	public int getIdNiveauChoisi() {
		return idNiveauChoisi;
	}

	public void setIdNiveauChoisi(int idNiveauChoisi) {
		this.idNiveauChoisi = idNiveauChoisi;
	}

	public ArrayList<SelectItem> getNiveauList() {
		return niveauList;
	}

	public void setNiveauList(ArrayList<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	private List<ObjetVue> listeView = new ArrayList();
    public List<ObjetVue> getListeView() {
		return listeView;
	}

	public void setListeView(List<ObjetVue> listeView) {
		this.listeView = listeView;
	}

	private Integer sitechoisi;
	private Integer batchoisi;
	private Integer locchoisi;
	private Integer obchoisi;
	
	private String sitechoisit;
	private String batchoisit;
	private String locchoisit;
	private String obchoisit;
	
	private String barcodechoisi ;
	private Integer choixchoisi;
	private String choixchoisinv;
	private String objchoisinv;
	private String modeReponse;
	private Boolean nvchoi;
	private Boolean nvob;
	private  String unitmesure;
	 private Double lowlimitchoisi;
	 private Double hightlimitchoisi;
	 private Double lowBorderchoisi;
	 private Double highBorderchoisi;
	 private String lowBorderAlertchoisi;
	 private String highBorderAlertchoisi;
	 private String autrechoisi;
	
	 private boolean lunFSchoisi; 
	 private boolean lunSSchoisi; 
	 private boolean lunNSchoisi;
	 
	 private boolean maFSchoisi; 
	 private boolean maSSchoisi; 
	 private boolean maNSchoisi;
	 
	 private boolean meFSchoisi; 
	 private boolean meSSchoisi; 
	 private boolean meNSchoisi;
	 
	 private boolean jeFSchoisi; 
	 private boolean jeSSchoisi; 
	 private boolean jeNSchoisi;
	 
	 private boolean veFSchoisi; 
	 private boolean veSSchoisi; 
	 private boolean veNSchoisi;
	 
	 private boolean saFSchoisi; 
	 private boolean saSSchoisi; 
	 private boolean saNSchoisi;
	 
	 private boolean diFSchoisi; 
	 private boolean diSSchoisi; 
	 private boolean diNSchoisi;
	 
	private ArrayList<SelectItem> listSite = new ArrayList();
	private ArrayList<SelectItem> listBat = new ArrayList();
	private ArrayList<SelectItem> listLoc = new ArrayList();
	private ArrayList<SelectItem> listOb = new ArrayList();
	 private ArrayList<SelectItem> choixList = new ArrayList();
	public Integer getSitechoisi() {
		return sitechoisi;
	}

	public void setSitechoisi(Integer sitechoisi) {
		this.sitechoisi = sitechoisi;
	}

	public ArrayList<SelectItem> getListSite() {
		return listSite;
	}

	public void setListSite(ArrayList<SelectItem> listSite) {
		this.listSite = listSite;
	}

	public Integer getBatchoisi() {
		return batchoisi;
	}

	public void setBatchoisi(Integer batchoisi) {
		this.batchoisi = batchoisi;
	}

	public ArrayList<SelectItem> getListBat() {
		return listBat;
	}

	public void setListBat(ArrayList<SelectItem> listBat) {
		this.listBat = listBat;
	}

	public Integer getLocchoisi() {
		return locchoisi;
	}

	public void setLocchoisi(Integer locchoisi) {
		this.locchoisi = locchoisi;
	}

	public ArrayList<SelectItem> getListLoc() {
		return listLoc;
	}

	public void setListLoc(ArrayList<SelectItem> listLoc) {
		this.listLoc = listLoc;
	}

	public Integer getObchoisi() {
		return obchoisi;
	}

	public void setObchoisi(Integer obchoisi) {
		this.obchoisi = obchoisi;
	}

	public ArrayList<SelectItem> getListOb() {
		return listOb;
	}

	public void setListOb(ArrayList<SelectItem> listOb) {
		this.listOb = listOb;
	}

	public String getBarcodechoisi() {
		return barcodechoisi;
	}

	public void setBarcodechoisi(String barcodechoisi) {
		this.barcodechoisi = barcodechoisi;
	}

	public ArrayList<SelectItem> getChoixList() {
		return choixList;
	}

	public void setChoixList(ArrayList<SelectItem> choixList) {
		this.choixList = choixList;
	}

	public Integer getChoixchoisi() {
		return choixchoisi;
	}

	public void setChoixchoisi(Integer choixchoisi) {
		this.choixchoisi = choixchoisi;
	}

	public String getChoixchoisinv() {
		return choixchoisinv;
	}

	public void setChoixchoisinv(String choixchoisinv) {
		this.choixchoisinv = choixchoisinv;
	}

	

	public String getModeReponse() {
		return modeReponse;
	}

	public void setModeReponse(String modeReponse) {
		this.modeReponse = modeReponse;
	}

	public Boolean getNvchoi() {
		return nvchoi;
	}

	public void setNvchoi(Boolean nvchoi) {
		this.nvchoi = nvchoi;
	}

	public Double getLowlimitchoisi() {
		return lowlimitchoisi;
	}

	public void setLowlimitchoisi(Double lowlimitchoisi) {
		this.lowlimitchoisi = lowlimitchoisi;
	}

	public Double getHightlimitchoisi() {
		return hightlimitchoisi;
	}

	public void setHightlimitchoisi(Double hightlimitchoisi) {
		this.hightlimitchoisi = hightlimitchoisi;
	}

	public Double getLowBorderchoisi() {
		return lowBorderchoisi;
	}

	public void setLowBorderchoisi(Double lowBorderchoisi) {
		this.lowBorderchoisi = lowBorderchoisi;
	}

	public Double getHighBorderchoisi() {
		return highBorderchoisi;
	}

	public void setHighBorderchoisi(Double highBorderchoisi) {
		this.highBorderchoisi = highBorderchoisi;
	}

	public String getLowBorderAlertchoisi() {
		return lowBorderAlertchoisi;
	}

	public void setLowBorderAlertchoisi(String lowBorderAlertchoisi) {
		this.lowBorderAlertchoisi = lowBorderAlertchoisi;
	}

	public String getHighBorderAlertchoisi() {
		return highBorderAlertchoisi;
	}

	public void setHighBorderAlertchoisi(String highBorderAlertchoisi) {
		this.highBorderAlertchoisi = highBorderAlertchoisi;
	}

	public String getAutrechoisi() {
		return autrechoisi;
	}

	public void setAutrechoisi(String autrechoisi) {
		this.autrechoisi = autrechoisi;
	}

	public boolean isLunFSchoisi() {
		return lunFSchoisi;
	}

	public void setLunFSchoisi(boolean lunFSchoisi) {
		this.lunFSchoisi = lunFSchoisi;
	}

	public boolean isLunSSchoisi() {
		return lunSSchoisi;
	}

	public void setLunSSchoisi(boolean lunSSchoisi) {
		this.lunSSchoisi = lunSSchoisi;
	}

	public boolean isLunNSchoisi() {
		return lunNSchoisi;
	}

	public void setLunNSchoisi(boolean lunNSchoisi) {
		this.lunNSchoisi = lunNSchoisi;
	}

	public boolean isMaFSchoisi() {
		return maFSchoisi;
	}

	public void setMaFSchoisi(boolean maFSchoisi) {
		this.maFSchoisi = maFSchoisi;
	}

	public boolean isMaSSchoisi() {
		return maSSchoisi;
	}

	public void setMaSSchoisi(boolean maSSchoisi) {
		this.maSSchoisi = maSSchoisi;
	}

	public boolean isMaNSchoisi() {
		return maNSchoisi;
	}

	public void setMaNSchoisi(boolean maNSchoisi) {
		this.maNSchoisi = maNSchoisi;
	}

	public boolean isMeFSchoisi() {
		return meFSchoisi;
	}

	public void setMeFSchoisi(boolean meFSchoisi) {
		this.meFSchoisi = meFSchoisi;
	}

	public boolean isMeSSchoisi() {
		return meSSchoisi;
	}

	public void setMeSSchoisi(boolean meSSchoisi) {
		this.meSSchoisi = meSSchoisi;
	}

	public boolean isMeNSchoisi() {
		return meNSchoisi;
	}

	public void setMeNSchoisi(boolean meNSchoisi) {
		this.meNSchoisi = meNSchoisi;
	}

	public boolean isJeFSchoisi() {
		return jeFSchoisi;
	}

	public void setJeFSchoisi(boolean jeFSchoisi) {
		this.jeFSchoisi = jeFSchoisi;
	}

	public boolean isJeSSchoisi() {
		return jeSSchoisi;
	}

	public void setJeSSchoisi(boolean jeSSchoisi) {
		this.jeSSchoisi = jeSSchoisi;
	}

	public boolean isJeNSchoisi() {
		return jeNSchoisi;
	}

	public void setJeNSchoisi(boolean jeNSchoisi) {
		this.jeNSchoisi = jeNSchoisi;
	}

	public boolean isVeFSchoisi() {
		return veFSchoisi;
	}

	public void setVeFSchoisi(boolean veFSchoisi) {
		this.veFSchoisi = veFSchoisi;
	}

	public boolean isVeSSchoisi() {
		return veSSchoisi;
	}

	public void setVeSSchoisi(boolean veSSchoisi) {
		this.veSSchoisi = veSSchoisi;
	}

	public boolean isVeNSchoisi() {
		return veNSchoisi;
	}

	public void setVeNSchoisi(boolean veNSchoisi) {
		this.veNSchoisi = veNSchoisi;
	}

	public boolean isSaFSchoisi() {
		return saFSchoisi;
	}

	public void setSaFSchoisi(boolean saFSchoisi) {
		this.saFSchoisi = saFSchoisi;
	}

	public boolean isSaSSchoisi() {
		return saSSchoisi;
	}

	public void setSaSSchoisi(boolean saSSchoisi) {
		this.saSSchoisi = saSSchoisi;
	}

	public boolean isSaNSchoisi() {
		return saNSchoisi;
	}

	public void setSaNSchoisi(boolean saNSchoisi) {
		this.saNSchoisi = saNSchoisi;
	}

	public boolean isDiFSchoisi() {
		return diFSchoisi;
	}

	public void setDiFSchoisi(boolean diFSchoisi) {
		this.diFSchoisi = diFSchoisi;
	}

	public boolean isDiSSchoisi() {
		return diSSchoisi;
	}

	public void setDiSSchoisi(boolean diSSchoisi) {
		this.diSSchoisi = diSSchoisi;
	}

	public boolean isDiNSchoisi() {
		return diNSchoisi;
	}

	public void setDiNSchoisi(boolean diNSchoisi) {
		this.diNSchoisi = diNSchoisi;
	}

	public Boolean getNvob() {
		return nvob;
	}

	public void setNvob(Boolean nvob) {
		this.nvob = nvob;
	}

	public String getObjchoisinv() {
		return objchoisinv;
	}

	public void setObjchoisinv(String objchoisinv) {
		this.objchoisinv = objchoisinv;
	}

	

	public String getSitechoisit() {
		return sitechoisit;
	}

	public void setSitechoisit(String sitechoisit) {
		this.sitechoisit = sitechoisit;
	}

	public String getBatchoisit() {
		return batchoisit;
	}

	public void setBatchoisit(String batchoisit) {
		this.batchoisit = batchoisit;
	}

	public String getLocchoisit() {
		return locchoisit;
	}

	public void setLocchoisit(String locchoisit) {
		this.locchoisit = locchoisit;
	}

	public String getObchoisit() {
		return obchoisit;
	}

	public void setObchoisit(String obchoisit) {
		this.obchoisit = obchoisit;
	}

	public String getUnitmesure() {
		return unitmesure;
	}

	public void setUnitmesure(String unitmesure) {
		this.unitmesure = unitmesure;
	}

	public void afficher2()
	{
LoginBean bean = LoginUtil.getLoginBean();
    	
    	
    	NiveauObjetDatabaseLayout nvobjDB =null;
    	nvobjDB=new NiveauObjetDatabaseLayout();
        nvobjDB.transactionOpen();
        
        NiveauDatabaseLayout nvdb = null;
        nvdb = new NiveauDatabaseLayout(); 	
        nvdb.transactionOpen();
    	 
        TypeReponseDatabaseLayout typDB=null;
		typDB=new TypeReponseDatabaseLayout();
		typDB.transactionOpen();
        
		
    	
    	ObjeteamDatabaseLayout objteamdb =null;
    	objteamdb=new ObjeteamDatabaseLayout();
    	objteamdb.transactionOpen();
    	
    	ObjeteamDatabaseLayout objteamdbss =null;
    	objteamdbss=new ObjeteamDatabaseLayout();
    	objteamdbss.transactionOpen();
    	
    	ObjeteamDatabaseLayout objteamdbns =null;
    	objteamdbns=new ObjeteamDatabaseLayout();
    	objteamdbns.transactionOpen();
    	
    	UtilisateurDatabaseLayout utilisateur = null;
	    utilisateur = new UtilisateurDatabaseLayout();
	    utilisateur.transactionOpen();
    	
	    Integer idFS;
	    Integer idSS;
	    Integer idNS;
    	try
        {this.listeView.clear();
        
    		this.highBorderchoisi=0.0;
        	this.lowBorderchoisi=0.0;
        	this.lowlimitchoisi=0.0;
        	this.hightlimitchoisi=0.0;
    		utilisateur.afficherByLibel("Spätschicht",bean.getIdClient());
    		idSS=utilisateur.model().getIdUtilisateur();
    		utilisateur.afficherByLibel("Frühschicht",bean.getIdClient());
    		idFS=utilisateur.model().getIdUtilisateur();
    		utilisateur.afficherByLibel("Nachtschicht",bean.getIdClient());
    		idNS=utilisateur.model().getIdUtilisateur();
    		// parcourir les objet ds tables niveau 
    		nvdb.listerequi2(bean.getIdClient());
    		
    		Iterator iteq = nvdb.liste().iterator();
    		   
            while (iteq.hasNext())
            {
            	
           	 Niveau nveq=(Niveau)iteq.next();
           	 
           	// les niveaux 
           	 ObjetVue objetvue = new ObjetVue();
           	objetvue.setEquip(nveq.getLibelle());
            	objetvue.setBat(nveq.getNiveau().getLibelle());
           	objetvue.setSite(nveq.getNiveau().getNiveau().getLibelle());
           	// objet
           	nvobjDB.afficherbyid(bean.getIdClient(), nveq.getNiveauobjet().getIdNiveauObjet());
           	if (nvobjDB.model()!=null)
           	{
           		objetvue.setIdObj(nvobjDB.model().getIdNiveauObjet());
           		objetvue.setControle(nvobjDB.model().getLibelle());
           		
           		objetvue.setBarecode(nvobjDB.model().getCodeBarre());
           		// voir le type 
           		typDB.afficher(bean.getIdClient(), nvobjDB.model().getTypereponse().getIdTypeReponse());
           		if (typDB.model().getMode()==1) //val
           		{ 	objetvue.setUnit(nvobjDB.model().getUnitmeasure());
           			objetvue.setLowlimit(nvobjDB.model().getLowlimit());
           			objetvue.setHightlimit(nvobjDB.model().getHighlimit());
           			objetvue.setLowBorder(nvobjDB.model().getLowborder());
           			objetvue.setHighBorder(nvobjDB.model().getHighborder());
           		}
           		else  objetvue.setUnit(typDB.model().getLibelle());
           		
           		objetvue.setLowBorderAlert(nvobjDB.model().getLowborderAlert());
           		objetvue.setHighBorderAlert(nvobjDB.model().getHighborderAlert());
           		// les equipes
           		//fs
           		objteamdb.listerbyteamObjet(nvobjDB.model().getIdNiveauObjet(), idFS);
           		if (objteamdb.model()!=null)
           		{
           			objetvue.setLunFS(objteamdb.model().isLun());
           			objetvue.setMaFS(objteamdb.model().isMar());
           			objetvue.setMeFS(objteamdb.model().isMer());
           			objetvue.setJeFS(objteamdb.model().isJeu());
           			objetvue.setVeFS(objteamdb.model().isVen());
           			objetvue.setSaFS(objteamdb.model().isSam());
           			objetvue.setDiFS(objteamdb.model().isDim());
           		}
           	//ns
           		objteamdbns.listerbyteamObjet(nvobjDB.model().getIdNiveauObjet(), idNS);
           		if (objteamdbns.model()!=null)
           		{
           			objetvue.setLunNS(objteamdbns.model().isLun());
           			objetvue.setMaNS(objteamdbns.model().isMar());
           			objetvue.setMeNS(objteamdbns.model().isMer());
           			objetvue.setJeNS(objteamdbns.model().isJeu());
           			objetvue.setVeNS(objteamdbns.model().isVen());
           			objetvue.setSaNS(objteamdbns.model().isSam());
           			objetvue.setDiNS(objteamdbns.model().isDim());
           		}
           	 	//Ss
           		objteamdbss.listerbyteamObjet(nvobjDB.model().getIdNiveauObjet(), idSS);
           		if (objteamdbss.model()!=null)
           		{
           			objetvue.setLunSS(objteamdbss.model().isLun());
           			objetvue.setMaSS(objteamdbss.model().isMar());
           			objetvue.setMeSS(objteamdbss.model().isMer());
           			objetvue.setJeSS(objteamdbss.model().isJeu());
           			objetvue.setVeSS(objteamdbss.model().isVen());
           			objetvue.setSaSS(objteamdbss.model().isSam());
           			objetvue.setDiSS(objteamdbss.model().isDim());
           		}
           		
           		
           		}
           	
           	this.listeView.add(objetvue);
           	
           	
            }
    		
        }
    	catch (Exception e) {
            System.out.println("Erreur liste vue");
            e.printStackTrace();
            
           }
    	finally{
    		nvobjDB.close();
    		nvdb.close();
    		typDB.close();
    		
    		objteamdb.close(); objteamdbns.close(); objteamdbss.close();
    		utilisateur.close();
    	}
    	
	}
	

		private Boolean val;
    public Boolean getVal() {
		return val;
	}

	public void setVal(Boolean val) {
		this.val = val;
	}

	public void choixChanged(AjaxBehaviorEvent event)
    {val=false;
    	System.out.println("event"+this.choixchoisi);
    	if (this.choixchoisi==2)
    		val=true;
    }
	 
	
	public void modifierTeamval (int idobj)
	{
		this.objeteamChoisi=idobj;
		System.out.println("**modif team1"+objeteamChoisi);
		
	}
	public void modifierTeam ()
	{
		
		
		
	}
	public void onCancel(RowEditEvent event) {  
	    
	    }  
	
	public void onEdit(RowEditEvent event) {  
		 //       FacesMessage msg = new FacesMessage("Car Edited", ((UsineDTO) event.getObject()).get);
		ObjetVue objetVue=((ObjetVue) event.getObject());
		        //System.out.println("coming in edit method: "+intervenant.getAnnuaireDTO().getNom());
		LoginBean bean = LoginUtil.getLoginBean();
				if (objetVue.getEquip().indexOf("/")<0)
				{
					return;
				}
				else
				{
		NiveauDatabaseLayout nvdbs = new NiveauDatabaseLayout();
		NiveauDatabaseLayout nvdbb = new NiveauDatabaseLayout();
		NiveauDatabaseLayout nvdbe = new NiveauDatabaseLayout();
		NiveauDatabaseLayout nvdbe1 = new NiveauDatabaseLayout();
		NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
		TypeReponseDatabaseLayout typDB= new TypeReponseDatabaseLayout();
		
		try {
			nvdbs.transactionOpen();
		
		nvdbs.afficherbyId(bean.getIdClient(), objetVue.getIdNivS());
		nvdbs.model().setLibelle(objetVue.getSite());
		nvdbs.save();
		nvdbs.transactionCommit();
		//
		nvdbb.transactionOpen();
		nvdbb.afficherbyId(bean.getIdClient(), objetVue.getIdNivB());
		nvdbb.model().setLibelle(objetVue.getBat());
		nvdbb.save();
		nvdbb.transactionCommit();
		//
		String eq1="";
		String eq="";
		String[] parts= objetVue.getEquip().split("/");
		eq=parts[0];
		eq1=parts[1];
		
		nvdbe.transactionOpen();
		nvdbe.afficherbyId(bean.getIdClient(), objetVue.getIdNivE());
		nvdbe.model().setLibelle(eq);
		nvdbe.save();
		nvdbe.transactionCommit();
		
		nvdbe1.transactionOpen();
		nvdbe1.afficherbyId(bean.getIdClient(), objetVue.getIdNivE1());
		nvdbe1.model().setLibelle(eq1);
		nvdbe1.save();
		nvdbe1.transactionCommit();
		
		//
		
        nvobjDB.transactionOpen();
        nvobjDB.afficherbyid(bean.getIdClient(), objetVue.getIdObj());
       
		nvobjDB.model().setLibelle(objetVue.getControle());
		nvobjDB.model().setCodeBarre(objetVue.getBarecode());
		
		
		
		//
		if (objetVue.getUnit().equals("List"))
		{
			typDB.transactionOpen();
    		typDB.afficher(bean.getIdClient(), objetVue.getIdtypreponse());
    		
    			typDB.model().setLibelle(objetVue.getAutre());
        	
    		typDB.save();
    		typDB.transactionCommit();
		}
		else
		{
			nvobjDB.model().setUnitmeasure(objetVue.getUnit());
			nvobjDB.model().setHighborder(objetVue.getHighBorder());
			nvobjDB.model().setLowborder(objetVue.getLowBorder());
			nvobjDB.model().setHighlimit(objetVue.getHightlimit());
			nvobjDB.model().setLowlimit(objetVue.getLowlimit());
		}
		nvobjDB.save();
		nvobjDB.transactionCommit();
		//

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
		
		}
		catch (Exception ex){
			 ex.printStackTrace();
		 }
		 finally {
			 if (nvdbs!=null){	 nvdbs.close();}
			 if (nvdbb!=null){	 nvdbb.close();}
			 if (nvdbe!=null){	 nvdbe.close();}
			 if (nvdbe1!=null){	 nvdbe1.close();}
			 if (nvobjDB !=null) {	 nvobjDB.close();}
			 if (typDB !=null) {	 typDB.close();}
			 ObjetVue group=((ObjetVue) event.getObject());
		 }
				}
		
		    }  
		      
	public String supprimer2(int idObj)
	{
		NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
		
		try {
			LoginBean bean = LoginUtil.getLoginBean();
			nvobjDB.transactionOpen();
			nvobjDB.afficherbyid(bean.getIdClient(), idObj);
			nvobjDB.model().setMasque(true);
			nvobjDB.update();
			nvobjDB.transactionCommit();
			

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
		}
		catch (Exception ex){
			 ex.printStackTrace();
		 }
		 finally {
			 
			 if (nvobjDB !=null) {	 nvobjDB.close();}
			
		 }
		return "general_vue?faces-redirect=true";	
		
	}
	
	 public void onEditLine(RowEditEvent event) { 
		   System.out.println("here am eit line");
		   ObjetVue objetVue=((ObjetVue)  event.getObject());
		   LoginBean bean = LoginUtil.getLoginBean();
		   NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
		   nvobjDB.transactionOpen();
		    
		    
		   ObjeteamDatabaseLayout objteamdb =null;
	    	objteamdb=new ObjeteamDatabaseLayout();
	    	objteamdb.transactionOpen();
	    	
	    	UtilisateurDatabaseLayout utilisateur = null;
		    utilisateur = new UtilisateurDatabaseLayout();
		    utilisateur.transactionOpen();
	    	
		    
		   try
		   {
			   
			   //change libelle controle
			   if (! objetVue.getControle().equals(""))
			   {
				   nvobjDB.afficherbyid(bean.getIdClient(), objetVue.getIdObj());
				   nvobjDB.model().setLibelle(objetVue.getControle());
				   nvobjDB.model().setCodeBarre(objetVue.getBarecode());
				   nvobjDB.model().setUnitmeasure(objetVue.getUnit());
				   if (nvobjDB.model().getTypereponse().getMode()==1)
				   {
					   nvobjDB.model().setLowlimit(objetVue.getLowlimit());
					   nvobjDB.model().setHighlimit(objetVue.getHightlimit());
					   nvobjDB.model().setLowborder(objetVue.getLowBorder());
					   nvobjDB.model().setHighborder(objetVue.getHighBorder());
					   nvobjDB.model().setLowborderAlert(objetVue.getLowBorderAlert());
					   nvobjDB.model().setHighborderAlert(objetVue.getHighBorderAlert());
					   
				   }
				   nvobjDB.save();
				   nvobjDB.transactionCommit();
			   }
			   //typereponse 
			   
			   //valuereponse
			  
			   //Equipes
			   
			   Integer idFS;
			    Integer idSS;
			    Integer idNS;
		    	
		    	utilisateur.afficherByLibel("Spätschicht",bean.getIdClient());
		    	idSS=utilisateur.model().getIdUtilisateur();
		    	utilisateur.afficherByLibel("Frühschicht",bean.getIdClient());
		    	idFS=utilisateur.model().getIdUtilisateur();
		    	utilisateur.afficherByLibel("Nachtschicht",bean.getIdClient());
		    	idNS=utilisateur.model().getIdUtilisateur();
		    		
		    	//FS
		    	
			   objteamdb.listerbyteamObjet(objetVue.getIdObj(), idFS);
			   if (objteamdb.model()!=null) //exist modif
			   {
				  
				   objteamdb.model().setLun(objetVue.isLunFS());
				   objteamdb.model().setMar(objetVue.isMaFS());
				   objteamdb.model().setMer(objetVue.isMeFS());
				   objteamdb.model().setJeu(objetVue.isJeFS());
				   objteamdb.model().setVen(objetVue.isVeFS());
				   objteamdb.model().setSam(objetVue.isSaFS());
				   objteamdb.model().setDim(objetVue.isDiFS());
				   objteamdb.save();
				   //objteamdb.transactionCommit();
				   
			   }
			   else //dont exist create
			   {
				   ObjeteamDatabaseLayout objteamdb1 =null;
			    	objteamdb1=new ObjeteamDatabaseLayout();
			    	objteamdb1.transactionOpen();
				   objteamdb1.model().setIdObjet(objetVue.getIdObj());
				   objteamdb1.setUtilisateur(idFS);
				   objteamdb1.setClient(bean.getIdClient());
				   objteamdb1.model().setMasque(false);
				   objteamdb1.model().setLun(objetVue.isLunFS());
				   objteamdb1.model().setMar(objetVue.isMaFS());
				   objteamdb1.model().setMer(objetVue.isMeFS());
				   objteamdb1.model().setJeu(objetVue.isJeFS());
				   objteamdb1.model().setVen(objetVue.isVeFS());
				   objteamdb1.model().setSam(objetVue.isSaFS());
				   objteamdb1.model().setDim(objetVue.isDiFS());
				   objteamdb1.save();
				   objteamdb1.transactionCommit();
				   objteamdb1.close();
			   }
			   //SS
			   objteamdb.listerbyteamObjet(objetVue.getIdObj(), idSS);
			   if (objteamdb.model()!=null) //exist modif
			   {
				   objteamdb.model().setLun(objetVue.isLunSS());
				   objteamdb.model().setMar(objetVue.isMaSS());
				   objteamdb.model().setMer(objetVue.isMeSS());
				   objteamdb.model().setJeu(objetVue.isJeSS());
				   objteamdb.model().setVen(objetVue.isVeSS());
				   objteamdb.model().setSam(objetVue.isSaSS());
				   objteamdb.model().setDim(objetVue.isDiSS());
				   objteamdb.save();
				   //objteamdb.transactionCommit();
				   
			   }
			   else //dont exist create
			   {
				   ObjeteamDatabaseLayout objteamdb1 =null;
			    	objteamdb1=new ObjeteamDatabaseLayout();
			    	objteamdb1.transactionOpen();
				   objteamdb1.model().setIdObjet(objetVue.getIdObj());
				   objteamdb1.setUtilisateur(idSS);
				   objteamdb1.setClient(bean.getIdClient());
				   objteamdb1.model().setMasque(false);
				   objteamdb1.model().setLun(objetVue.isLunSS());
				   objteamdb1.model().setMar(objetVue.isMaSS());
				   objteamdb1.model().setMer(objetVue.isMeSS());
				   objteamdb1.model().setJeu(objetVue.isJeSS());
				   objteamdb1.model().setVen(objetVue.isVeSS());
				   objteamdb1.model().setSam(objetVue.isSaSS());
				   objteamdb1.model().setDim(objetVue.isDiSS());
				   objteamdb1.save();
				   objteamdb1.transactionCommit();
				   objteamdb1.close();
			   }
			   //NS
			   objteamdb.listerbyteamObjet(objetVue.getIdObj(), idNS);
			   if (objteamdb.model()!=null) //exist modif
			   {
				   objteamdb.model().setLun(objetVue.isLunNS());
				   objteamdb.model().setMar(objetVue.isMaNS());
				   objteamdb.model().setMer(objetVue.isMeNS());
				   objteamdb.model().setJeu(objetVue.isJeNS());
				   objteamdb.model().setVen(objetVue.isVeNS());
				   objteamdb.model().setSam(objetVue.isSaNS());
				   objteamdb.model().setDim(objetVue.isDiNS());
				   objteamdb.save();
				   objteamdb.transactionCommit();
				   
			   }
			   else //dont exist create
			   {
				   ObjeteamDatabaseLayout objteamdb1 =null;
			    	objteamdb1=new ObjeteamDatabaseLayout();
			    	objteamdb1.transactionOpen();
				   objteamdb1.model().setIdObjet(objetVue.getIdObj());
				   objteamdb1.setUtilisateur(idNS);
				   objteamdb1.setClient(bean.getIdClient());
				   objteamdb1.model().setMasque(false);
				   objteamdb1.model().setLun(objetVue.isLunNS());
				   objteamdb1.model().setMar(objetVue.isMaNS());
				   objteamdb1.model().setMer(objetVue.isMeNS());
				   objteamdb1.model().setJeu(objetVue.isJeNS());
				   objteamdb1.model().setVen(objetVue.isVeNS());
				   objteamdb1.model().setSam(objetVue.isSaNS());
				   objteamdb1.model().setDim(objetVue.isDiNS());
				   objteamdb1.save();
				   objteamdb1.transactionCommit();
				   objteamdb1.close();
			   }

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
		   }
		   catch (Exception ex){
				 ex.printStackTrace();
			 }
			 finally {
				 
					 nvobjDB.close();
				 	 objteamdb.close();
			 }
		   
	 }
public String supprimerInspect(int idObjet)
{
	 LoginBean bean = LoginUtil.getLoginBean();
	   NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
	   nvobjDB.transactionOpen();
	  
	   TypeReponseDatabaseLayout typDB= new TypeReponseDatabaseLayout();
	   typDB.transactionOpen();
	   
	   NiveauDatabaseLayout nvdb = null;
       nvdb = new NiveauDatabaseLayout(); 	
       nvdb.transactionOpen(); 
	   Integer idtyp;
	   try{
		   
		   nvobjDB.afficher(bean.getIdClient(), idObjet);
		   idtyp=nvobjDB.model().getTypereponse().getIdTypeReponse();
		   nvobjDB.model().setExiste(false);
		   nvobjDB.update();
		   nvobjDB.transactionCommit();
		   
		   typDB.afficher(bean.getIdClient(),idtyp);
		  
		   typDB.update();
		   typDB.transactionCommit();
	  
		   nvdb.afficherunik(bean.getIdClient(), idObjet);
		   nvdb.setNiveauObjet(null);
		   nvdb.update();
		   nvdb.transactionCommit();
		

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
	   }

catch (Exception ex){
		 ex.printStackTrace();
	 }
	   finally {
	   nvobjDB.close();
	   typDB.close();
	   nvdb.close();
}
	   return "general_vue?faces-redirect=true";  
}


/*public ArrayList<SelectItem> listTypeReponse()
{
	this.choixList.clear();
	this.choixList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_unite")));

  LoginBean bean = LoginUtil.getLoginBean();
  TypeReponseDatabaseLayout typeReponse = null;
  try
  {
   typeReponse = new TypeReponseDatabaseLayout();

    typeReponse.listerExisting(bean.getIdClient());
  ArrayList list = (ArrayList)typeReponse.liste();

    for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
    {
    

     this.choixList.add(new SelectItem(((Typereponse)typeReponse.liste().get(i.intValue())).getIdTypeReponse(), ((Typereponse)typeReponse.liste().get(i.intValue())).getLibelle()));
    }

   
  }
  catch (Exception e) {
   e.printStackTrace();
   
   typeReponse.close();
  }

 return this.choixList;
}

*/

public void valueChanged(ValueChangeEvent event) {
   
    if (null != event.getNewValue()) {
      
    	this.modeReponse="Valeur";
    	this.highBorderchoisi=0.0;
    	this.lowBorderchoisi=0.0;
    	this.lowlimitchoisi=0.0;
    	this.hightlimitchoisi=0.0;
        if (((Integer) event.getNewValue())==0) {
          
           this.nvchoi=true;
           
        }
        else
        	this.nvchoi=false;
        
    }
}

public void valueChanged1(ValueChangeEvent event) {
	   
    if (null != event.getNewValue()) {
      
    	this.modeReponse="Valeur";
    	this.highBorderchoisi=0.0;
    	this.lowBorderchoisi=0.0;
    	this.lowlimitchoisi=0.0;
    	this.hightlimitchoisi=0.0;
        if (((Integer) event.getNewValue())==0) {
          
           this.nvob=true;
           
        }
        else
        	this.nvob=false;
        
    }
}

public void updateniveauOnerow(Integer idobj, Integer idsite, Integer triA)
{
	System.out.println("triA"+triA);
	Integer idb=0;
	LoginBean bean = LoginUtil.getLoginBean();
	
	   NiveauDatabaseLayout nvdb = null;
       nvdb = new NiveauDatabaseLayout(); 	
       nvdb.transactionOpen();
       //verifier si ya bat
       nvdb.afficherbyLabelbat(bean.getIdClient(), this.batchoisit, idsite);
       if (nvdb.model()==null)
       {
    	   NiveauDatabaseLayout nvdbb = null;
           nvdbb = new NiveauDatabaseLayout(); 	
           nvdbb.transactionOpen();
           //nvdbb.model().setClefTimestamp(new Date());
           nvdbb.setClient(bean.getIdClient());
           nvdbb.model().setLibelle(this.batchoisit);
           nvdbb.model().setLevel(2);
           nvdbb.model().setMasque(false);
           nvdbb.setNiveauParent(idsite);
           
           // chercher le tri 
           NiveauDatabaseLayout nvdbtribat = null;
  			nvdbtribat = new NiveauDatabaseLayout(); 	
  			nvdbtribat.transactionOpen();
  			nvdbtribat.listerBat(bean.getIdClient(), idsite);
  			if (nvdbtribat.model()!=null)
  				nvdbb.model().setTri(nvdbtribat.liste().size()+1);
  			else
  				nvdbb.model().setTri(1);
           idb=nvdbb.save2();
           nvdbb.close();
           // inserer eq 
           NiveauDatabaseLayout nvdbc = null;
           nvdbc = new NiveauDatabaseLayout(); 	
           nvdbc.transactionOpen();
         //  nvdbc.model().setClefTimestamp(new Date());
           nvdbc.setClient(bean.getIdClient());
           nvdbc.model().setLibelle(this.locchoisit);
           nvdbc.model().setLevel(3);
           nvdbc.model().setMasque(false);
           nvdbc.setNiveauParent(idb);
           nvdbc.setNiveauObjet(idobj);
           // chercher tri 
           NiveauDatabaseLayout nvdbtrieq = null;
			nvdbtrieq = new NiveauDatabaseLayout(); 	
			nvdbtrieq.transactionOpen();
			nvdbtrieq.listerBat(bean.getIdClient(), idb);
			if (nvdbtrieq.model()!=null)
				nvdbc.model().setTri(nvdbtrieq.liste().size()+1);
			else nvdbc.model().setTri(1);
           nvdbc.save2();
           nvdbc.close();
           
       }
       else // bat existe 
       {
    	   //verifier eq
    	   Integer idbat=nvdb.model().getIdNiveau();
    	   Integer idc=0;
    	   nvdb.afficherbyLabelbat(bean.getIdClient(), this.locchoisit, idbat);
    	   if (nvdb.model()==null)
    	   {
    		   NiveauDatabaseLayout nvdbc = null;
               nvdbc = new NiveauDatabaseLayout(); 	
               nvdbc.transactionOpen();
              // nvdbc.model().setClefTimestamp(new Date());
               nvdbc.setClient(bean.getIdClient());
               nvdbc.model().setLibelle(this.locchoisit);
               nvdbc.model().setLevel(3);
               nvdbc.model().setMasque(false);
               nvdbc.setNiveauParent(idbat);
               nvdbc.setNiveauObjet(idobj);
               
            // corriger le reste des tri de la table
               NiveauDatabaseLayout nvaddtri = null;
               nvaddtri = new NiveauDatabaseLayout(); 	
               nvaddtri.transactionOpen();
               nvaddtri.listeTriup2(bean.getIdClient(), triA, idbat);
               Iterator itTri = nvaddtri.liste().iterator();
     	         while (itTri.hasNext())
     	          {
     	        	 Niveau niv = (Niveau)itTri.next();
     	        	 NiveauDatabaseLayout niveauTriup = null;
     		    	  niveauTriup = new NiveauDatabaseLayout();
     		    	  niveauTriup.transactionOpen();
     	        	 niveauTriup.afficherbyId(bean.getIdClient(), niv.getIdNiveau());
     	        	
     	        	 niveauTriup.model().setTri(niv.getTri()+1);
     	        	 
     	        	 niveauTriup.save();
     	        	 niveauTriup.transactionCommit();
     	        	 niveauTriup.close();
     	          }
               //mettre tri before ou after 
               
              nvdbc.model().setTri(triA);
               idc=nvdbc.save2();
               nvdbc.close();
               
    	   }
    	   else
    	   {
    		   NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
    		   nvobjDB.transactionOpen();
    		   nvobjDB.afficherbyid(bean.getIdClient(),nvdb.model().getNiveauobjet().getIdNiveauObjet() );
    		    nvobjDB.model().setExiste(false);
    		  
    		   nvobjDB.update();
    		   nvobjDB.transactionCommit();
    		   nvobjDB.close();
    		   nvdb.setNiveauObjet(idobj);
    		   nvdb.update();
    		   nvdb.transactionCommit();
    		  
    	   }
       }
    
       nvdb.close();   
       

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
}
public void updateniveau(Integer idobj, Integer idsite)
{
	Integer idb=0;
	LoginBean bean = LoginUtil.getLoginBean();
	
	   NiveauDatabaseLayout nvdb = null;
       nvdb = new NiveauDatabaseLayout(); 	
       nvdb.transactionOpen();
       //verifier si ya bat
       nvdb.afficherbyLabelbat(bean.getIdClient(), this.batchoisit, idsite);
       if (nvdb.model()==null)
       {
    	   NiveauDatabaseLayout nvdbb = null;
           nvdbb = new NiveauDatabaseLayout(); 	
           nvdbb.transactionOpen();
           //nvdbb.model().setClefTimestamp(new Date());
           nvdbb.setClient(bean.getIdClient());
           nvdbb.model().setLibelle(this.batchoisit);
           nvdbb.model().setLevel(2);
           nvdbb.model().setMasque(false);
           nvdbb.setNiveauParent(idsite);
           
           // chercher le tri 
           NiveauDatabaseLayout nvdbtribat = null;
  			nvdbtribat = new NiveauDatabaseLayout(); 	
  			nvdbtribat.transactionOpen();
  			nvdbtribat.listerBat(bean.getIdClient(), idsite);
  			if (nvdbtribat.model()!=null)
  				nvdbb.model().setTri(nvdbtribat.liste().size()+1);
  			else
  				nvdbb.model().setTri(1);
           idb=nvdbb.save2();
           nvdbb.close();
           // inserer eq 
           NiveauDatabaseLayout nvdbc = null;
           nvdbc = new NiveauDatabaseLayout(); 	
           nvdbc.transactionOpen();
         //  nvdbc.model().setClefTimestamp(new Date());
           nvdbc.setClient(bean.getIdClient());
           nvdbc.model().setLibelle(this.locchoisit);
           nvdbc.model().setLevel(3);
           nvdbc.model().setMasque(false);
           nvdbc.setNiveauParent(idb);
           nvdbc.setNiveauObjet(idobj);
           // chercher tri 
           NiveauDatabaseLayout nvdbtrieq = null;
			nvdbtrieq = new NiveauDatabaseLayout(); 	
			nvdbtrieq.transactionOpen();
			nvdbtrieq.listerBat(bean.getIdClient(), idb);
			if (nvdbtrieq.model()!=null)
				nvdbc.model().setTri(nvdbtrieq.liste().size()+1);
			else nvdbc.model().setTri(1);
           nvdbc.save2();
           nvdbc.close();
           
       }
       else // bat existe 
       {
    	   //verifier eq
    	   Integer idbat=nvdb.model().getIdNiveau();
    	   Integer idc=0;
    	   nvdb.afficherbyLabelbat(bean.getIdClient(), this.locchoisit, idbat);
    	   if (nvdb.model()==null)
    	   {
    		   NiveauDatabaseLayout nvdbc = null;
               nvdbc = new NiveauDatabaseLayout(); 	
               nvdbc.transactionOpen();
              // nvdbc.model().setClefTimestamp(new Date());
               nvdbc.setClient(bean.getIdClient());
               nvdbc.model().setLibelle(this.locchoisit);
               nvdbc.model().setLevel(3);
               nvdbc.model().setMasque(false);
               nvdbc.setNiveauParent(idbat);
               nvdbc.setNiveauObjet(idobj);
               //chercher tri 
               NiveauDatabaseLayout nvdbtrieq = null;
   			nvdbtrieq = new NiveauDatabaseLayout(); 	
   			nvdbtrieq.transactionOpen();
   			nvdbtrieq.listerBat(bean.getIdClient(), idbat);
   			if (nvdbtrieq.model()!=null)
   				nvdbc.model().setTri(nvdbtrieq.liste().size()+1);
   			else nvdbc.model().setTri(1);
               idc=nvdbc.save2();
               nvdbc.close();
    	   }
    	   else
    	   {
    		   NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
    		   nvobjDB.transactionOpen();
    		   nvobjDB.afficherbyid(bean.getIdClient(),nvdb.model().getNiveauobjet().getIdNiveauObjet() );
    		    nvobjDB.model().setExiste(false);
    		  
    		   nvobjDB.update();
    		   nvobjDB.transactionCommit();
    		   nvobjDB.close();
    		   nvdb.setNiveauObjet(idobj);
    		   nvdb.update();
    		   nvdb.transactionCommit();
    		  
    	   }
       }
    
       nvdb.close();   
       

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
       
}
public String creerInspectionv2 ()
{
	LoginBean bean = LoginUtil.getLoginBean();
	NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
	 TypeReponseDatabaseLayout typeReponse = null;
	 typeReponse=new TypeReponseDatabaseLayout();
	 typeReponse.transactionOpen();
	   nvobjDB.transactionOpen();
	   NiveauDatabaseLayout nvdb = null;
       nvdb = new NiveauDatabaseLayout(); 	
       nvdb.transactionOpen();
       ObjeteamDatabaseLayout objteamdb =null;
      	objteamdb=new ObjeteamDatabaseLayout();
      	objteamdb.transactionOpen();
      	
      	UtilisateurDatabaseLayout utilisateur = null;
   	    utilisateur = new UtilisateurDatabaseLayout();
   	    utilisateur.transactionOpen();
   	   	    
       try
       {
    	   if ((this.unitmesure!=null)&&(this.sitechoisit!=null)&&(this.batchoisit!=null)&&(this.obchoisit !=null)&& (this.choixchoisi!=null) && (this.locchoisit!=null))
    	   {
    	   if (this.onerow)
    	   {
    		   System.out.println("one rox ici"+this.triChoisi+"**"+idTagChoisi);
    		   Integer triTag=0;
    		   NiveauDatabaseLayout nvdbTri = null;
    		   nvdbTri = new NiveauDatabaseLayout(); 	
    		   nvdbTri.transactionOpen();
    		   nvdbTri.afficherbyId(bean.getIdClient(), idTagChoisi);
    		   triTag=nvdbTri.model().getTri();
    		   System.out.println("one rox ici 2**"+triTag);
    		   if (triChoisi==1)//before
    		   {
    			   if (triTag==1) triTag=1;
    			   else triTag=triTag-1;
    		   }
    		   else triTag=triTag+1;
    		 
    			  
    			   
    		   
    		 //chercher le typ
    		   //enregistrer l objet 
    		   nvobjDB.setClient(bean.getIdClient());
    		   nvobjDB.model().setCodeBarre(this.barcodechoisi);
    		   nvobjDB.model().setTri(1);
    		  
    		   nvobjDB.model().setExiste(true);
    		   nvobjDB.model().setMasque(false);
    		   
    		   nvobjDB.model().setLibelle(this.obchoisit);
    		   System.out.println("choixchoisi****"+choixchoisi);
    		   if (this.choixchoisi==0)
    		   {
    			   System.out.println("ici valeur stp1****"+choixchoisi);
    			   typeReponse.afficherByMode(bean.getIdClient(), 1);
    			   nvobjDB.setTypeReponse(typeReponse.model().getIdTypeReponse());
    			   //this.unitmesure
    			   nvobjDB.model().setUnitmeasure(this.unitmesure);
    		   nvobjDB.model().setHighborder(this.highBorderchoisi);
    		   nvobjDB.model().setHighborderAlert(this.highBorderAlertchoisi);
    		   
    		   nvobjDB.model().setLowborder(this.lowBorderchoisi);
    		   nvobjDB.model().setLowborderAlert(this.lowBorderAlertchoisi);
    		  
    		   nvobjDB.model().setHighlimit(this.hightlimitchoisi);
    		   nvobjDB.model().setLowlimit(this.lowlimitchoisi);
    		   }
    		   else
    		   {
    			   typeReponse.afficher(bean.getIdClient(), choixchoisi);
    			  
    			   nvobjDB.setTypeReponse(typeReponse.model().getIdTypeReponse());
    			   nvobjDB.model().setUnitmeasure(typeReponse.model().getLibelle());  
    		   }
    		   Integer ob=nvobjDB.save();
    		   
    		   nvdb.afficherbyLabel(bean.getIdClient(), this.sitechoisit);
    		   if (nvdb.model()!=null)
    		   {
    			   System.out.println("trouve");
    			   updateniveauOnerow(ob,nvdb.model().getIdNiveau(),triTag);
    		   }
    		   else // nouveau site
    		   {
    			   NiveauDatabaseLayout nvdb2 = null;
    		       nvdb2 = new NiveauDatabaseLayout(); 	
    		       nvdb2.transactionOpen();
    			   nvdb2.model().setLibelle(this.sitechoisit);
    			   nvdb2.model().setLevel(1);
    			   nvdb2.setClient(bean.getIdClient());
    			   nvdb2.model().setMasque(false);
    			   //nvdb2.model().setClefTimestamp(new Date());
    			   //chercher le tri:
    			   NiveauDatabaseLayout nvsittri = null;
    			   nvsittri = new NiveauDatabaseLayout(); 	
    			   nvsittri.transactionOpen();
    			   
    			   nvsittri.listerSite(bean.getIdClient());
    			   if (nvsittri.model()!=null)
    				   nvdb2.model().setTri(nvsittri.liste().size()+1);
    			   else
    				   nvdb2.model().setTri(1); 
    			   int par=nvdb2.save2();
    			   System.out.println(par);
    			   nvdb2.transactionCommit();
    			   nvdb2.close();
    			  if (par>0)
    			  {
    				  NiveauDatabaseLayout nvdb3 = null;
       		       nvdb3 = new NiveauDatabaseLayout(); 	
       		       nvdb3.transactionOpen();
    				  nvdb3.model().setLibelle(this.batchoisit);
    				  nvdb3.model().setLevel(2);
       			   nvdb3.setNiveauParent(par);
       			   nvdb3.setClient(bean.getIdClient());
       			   nvdb3.model().setMasque(false);
       			//nvdb3.model().setClefTimestamp(new Date());
       			   //chercher tri 
       			NiveauDatabaseLayout nvdbtribat = null;
       			nvdbtribat = new NiveauDatabaseLayout(); 	
       			nvdbtribat.transactionOpen();
       			nvdbtribat.listerBat(bean.getIdClient(), par);
       			if (nvdbtribat.model()!=null)
       			nvdb3.model().setTri(nvdbtribat.liste().size()+1);
       			else
       				nvdb3.model().setTri(1);
       			   int par2=nvdb3.save2();
       			System.out.println(par2);
       			    nvdb3.transactionCommit();
       			 nvdb3.close();
       			    if (par2>0)
       			    {NiveauDatabaseLayout nvdb4 = null;
        		       nvdb4 = new NiveauDatabaseLayout(); 	
           		       nvdb4.transactionOpen();
       			    	nvdb4.model().setLibelle(this.locchoisit);
       			    	nvdb4.model().setLevel(3);
           			    nvdb4.setNiveauParent(par2);
           			    nvdb4.setClient(bean.getIdClient());
            			   nvdb4.model().setMasque(false);
            			   //nvdb4.model().setClefTimestamp(new Date());
           			    nvdb4.setNiveauObjet(ob);
           			    
           			    // chercher tri 
           			 NiveauDatabaseLayout nvdbtrieq = null;
            			nvdbtrieq = new NiveauDatabaseLayout(); 	
            			nvdbtrieq.transactionOpen();
            			nvdbtrieq.listerBat(bean.getIdClient(), par2);
            			if (nvdbtrieq.model()!=null)
            			nvdb4.model().setTri(nvdbtrieq.liste().size()+1);
            			else nvdb4.model().setTri(1);
           			    nvdb4.save2();
           			    nvdb4.transactionCommit();
           			 nvdb4.close();
       			    }
       			    
       			     
    			  }
    			   
    		   }
    		   
    		  // enregistrer les equioes 
    		   
    		   Integer idFS;
   		    Integer idSS;
   		    Integer idNS;
   	    	
   	    		utilisateur.afficherByLibel("Spätschicht",bean.getIdClient());
   	    		idSS=utilisateur.model().getIdUtilisateur();
   	    		utilisateur.afficherByLibel("Frühschicht",bean.getIdClient());
   	    		idFS=utilisateur.model().getIdUtilisateur();
   	    		utilisateur.afficherByLibel("Nachtschicht",bean.getIdClient());
   	    		idNS=utilisateur.model().getIdUtilisateur();
   	    		
   					   ObjeteamDatabaseLayout objteamdb1 =null;
   				    	objteamdb1=new ObjeteamDatabaseLayout();
   				    	objteamdb1.transactionOpen();
   					   objteamdb1.model().setIdObjet(ob);
   					   objteamdb1.setUtilisateur(idFS);
   					   objteamdb1.setClient(bean.getIdClient());
   					   objteamdb1.model().setMasque(false);
   					   objteamdb1.model().setLun(this.lunFSchoisi);
   					   objteamdb1.model().setMar(this.maFSchoisi);
   					   objteamdb1.model().setMer(this.meFSchoisi);
   					   objteamdb1.model().setJeu(this.jeFSchoisi);
   					   objteamdb1.model().setVen(this.veFSchoisi);
   					   objteamdb1.model().setSam(this.saFSchoisi);
   					   objteamdb1.model().setDim(this.diFSchoisi);
   					   objteamdb1.save();
   					   objteamdb1.transactionCommit();
   					   objteamdb1.close();
   					   
   					ObjeteamDatabaseLayout objteamdb2 =null;
			    	objteamdb2=new ObjeteamDatabaseLayout();
			    	objteamdb2.transactionOpen();
				   objteamdb2.model().setIdObjet(ob);
				   objteamdb2.setUtilisateur(idSS);
				   objteamdb2.setClient(bean.getIdClient());
				   objteamdb2.model().setMasque(false);
				   objteamdb2.model().setLun(this.lunSSchoisi);
				   objteamdb2.model().setMar(this.maSSchoisi);
				   objteamdb2.model().setMer(this.meSSchoisi);
				   objteamdb2.model().setJeu(this.jeSSchoisi);
				   objteamdb2.model().setVen(this.veSSchoisi);
				   objteamdb2.model().setSam(this.saSSchoisi);
				   objteamdb2.model().setDim(this.diSSchoisi);
				   objteamdb2.save();
				   objteamdb2.transactionCommit();
				   objteamdb2.close();
					   
					   ObjeteamDatabaseLayout objteamdb3 =null;
				    	objteamdb3=new ObjeteamDatabaseLayout();
				    	objteamdb3.transactionOpen();
					   objteamdb3.model().setIdObjet(ob);
					   objteamdb3.setUtilisateur(idNS);
					   objteamdb3.setClient(bean.getIdClient());
					   objteamdb3.model().setMasque(false);
					   objteamdb3.model().setLun(this.lunNSchoisi);
					   objteamdb3.model().setMar(this.maNSchoisi);
					   objteamdb3.model().setMer(this.meNSchoisi);
					   objteamdb3.model().setJeu(this.jeNSchoisi);
					   objteamdb3.model().setVen(this.veNSchoisi);
					   objteamdb3.model().setSam(this.saNSchoisi);
					   objteamdb3.model().setDim(this.diNSchoisi);
					   objteamdb3.save();
					   objteamdb3.transactionCommit();
					   objteamdb3.close();
    		   //reset
					   this.listeView.clear();
						this.tagChoisi=null;
						putFiltre();
						this.onerow=false;
						this.idTagChoisi=0;
    		   
    	   }// fin if one row
    	   else
    	   {
    		   
        		  //chercher le typ
        		   //enregistrer l objet 
        		   nvobjDB.setClient(bean.getIdClient());
        		   nvobjDB.model().setCodeBarre(this.barcodechoisi);
        		   nvobjDB.model().setTri(1);
        		  
        		   nvobjDB.model().setExiste(true);
        		   nvobjDB.model().setMasque(false);
        		   
        		   nvobjDB.model().setLibelle(this.obchoisit);
        		   System.out.println("choixchoisi****"+choixchoisi);
        		   if (this.choixchoisi==0)
        		   {
        			   System.out.println("ici valeur stp1****"+choixchoisi);
        			   typeReponse.afficherByMode(bean.getIdClient(), 1);
        			   nvobjDB.setTypeReponse(typeReponse.model().getIdTypeReponse());
        			   //this.unitmesure
        			   nvobjDB.model().setUnitmeasure(this.unitmesure);
        		   nvobjDB.model().setHighborder(this.highBorderchoisi);
        		   nvobjDB.model().setHighborderAlert(this.highBorderAlertchoisi);
        		   
        		   nvobjDB.model().setLowborder(this.lowBorderchoisi);
        		   nvobjDB.model().setLowborderAlert(this.lowBorderAlertchoisi);
        		  
        		   nvobjDB.model().setHighlimit(this.hightlimitchoisi);
        		   nvobjDB.model().setLowlimit(this.lowlimitchoisi);
        		   }
        		   else
        		   {
        			   typeReponse.afficher(bean.getIdClient(), choixchoisi);
        			  
        			   nvobjDB.setTypeReponse(typeReponse.model().getIdTypeReponse());
        			   nvobjDB.model().setUnitmeasure(typeReponse.model().getLibelle());  
        		   }
        		   Integer ob=nvobjDB.save();
        		   
        		   //rechercher doublon site
        		   
        		   
        		   nvdb.afficherbyLabel(bean.getIdClient(), this.sitechoisit);
        		   if (nvdb.model()!=null)
        		   {
        			   System.out.println("trouve");
        			   updateniveau(ob,nvdb.model().getIdNiveau());
        		   }
        		   else // nouveau site
        		   {
        			   NiveauDatabaseLayout nvdb2 = null;
        		       nvdb2 = new NiveauDatabaseLayout(); 	
        		       nvdb2.transactionOpen();
        			   nvdb2.model().setLibelle(this.sitechoisit);
        			   nvdb2.model().setLevel(1);
        			   nvdb2.setClient(bean.getIdClient());
        			   nvdb2.model().setMasque(false);
        			   //nvdb2.model().setClefTimestamp(new Date());
        			   //chercher le tri:
        			   NiveauDatabaseLayout nvsittri = null;
        			   nvsittri = new NiveauDatabaseLayout(); 	
        			   nvsittri.transactionOpen();
        			   
        			   nvsittri.listerSite(bean.getIdClient());
        			   if (nvsittri.model()!=null)
        				   nvdb2.model().setTri(nvsittri.liste().size()+1);
        			   else
        				   nvdb2.model().setTri(1); 
        			   int par=nvdb2.save2();
        			   System.out.println(par);
        			   nvdb2.transactionCommit();
        			   nvdb2.close();
        			  if (par>0)
        			  {
        				  NiveauDatabaseLayout nvdb3 = null;
           		       nvdb3 = new NiveauDatabaseLayout(); 	
           		       nvdb3.transactionOpen();
        				  nvdb3.model().setLibelle(this.batchoisit);
        				  nvdb3.model().setLevel(2);
           			   nvdb3.setNiveauParent(par);
           			   nvdb3.setClient(bean.getIdClient());
           			   nvdb3.model().setMasque(false);
           			//nvdb3.model().setClefTimestamp(new Date());
           			   //chercher tri 
           			NiveauDatabaseLayout nvdbtribat = null;
           			nvdbtribat = new NiveauDatabaseLayout(); 	
           			nvdbtribat.transactionOpen();
           			nvdbtribat.listerBat(bean.getIdClient(), par);
           			if (nvdbtribat.model()!=null)
           			nvdb3.model().setTri(nvdbtribat.liste().size()+1);
           			else
           				nvdb3.model().setTri(1);
           			   int par2=nvdb3.save2();
           			System.out.println(par2);
           			    nvdb3.transactionCommit();
           			 nvdb3.close();
           			    if (par2>0)
           			    {NiveauDatabaseLayout nvdb4 = null;
            		       nvdb4 = new NiveauDatabaseLayout(); 	
               		       nvdb4.transactionOpen();
           			    	nvdb4.model().setLibelle(this.locchoisit);
           			    	nvdb4.model().setLevel(3);
               			    nvdb4.setNiveauParent(par2);
               			    nvdb4.setClient(bean.getIdClient());
                			   nvdb4.model().setMasque(false);
                			   //nvdb4.model().setClefTimestamp(new Date());
               			    nvdb4.setNiveauObjet(ob);
               			    
               			    // chercher tri 
               			 NiveauDatabaseLayout nvdbtrieq = null;
                			nvdbtrieq = new NiveauDatabaseLayout(); 	
                			nvdbtrieq.transactionOpen();
                			nvdbtrieq.listerBat(bean.getIdClient(), par2);
                			if (nvdbtrieq.model()!=null)
                			nvdb4.model().setTri(nvdbtrieq.liste().size()+1);
                			else nvdb4.model().setTri(1);
               			    nvdb4.save2();
               			    nvdb4.transactionCommit();
               			 nvdb4.close();
           			    }
           			    
           			     
        			  }
        			   
        		   }
        		   
        		  // enregistrer les equioes 
        		   
        		   Integer idFS;
       		    Integer idSS;
       		    Integer idNS;
       	    	
       	    		utilisateur.afficherByLibel("Spätschicht",bean.getIdClient());
       	    		idSS=utilisateur.model().getIdUtilisateur();
       	    		utilisateur.afficherByLibel("Frühschicht",bean.getIdClient());
       	    		idFS=utilisateur.model().getIdUtilisateur();
       	    		utilisateur.afficherByLibel("Nachtschicht",bean.getIdClient());
       	    		idNS=utilisateur.model().getIdUtilisateur();
       	    		
       					   ObjeteamDatabaseLayout objteamdb1 =null;
       				    	objteamdb1=new ObjeteamDatabaseLayout();
       				    	objteamdb1.transactionOpen();
       					   objteamdb1.model().setIdObjet(ob);
       					   objteamdb1.setUtilisateur(idFS);
       					   objteamdb1.setClient(bean.getIdClient());
       					   objteamdb1.model().setMasque(false);
       					   objteamdb1.model().setLun(this.lunFSchoisi);
       					   objteamdb1.model().setMar(this.maFSchoisi);
       					   objteamdb1.model().setMer(this.meFSchoisi);
       					   objteamdb1.model().setJeu(this.jeFSchoisi);
       					   objteamdb1.model().setVen(this.veFSchoisi);
       					   objteamdb1.model().setSam(this.saFSchoisi);
       					   objteamdb1.model().setDim(this.diFSchoisi);
       					   objteamdb1.save();
       					   objteamdb1.transactionCommit();
       					   objteamdb1.close();
       					   
       					ObjeteamDatabaseLayout objteamdb2 =null;
    			    	objteamdb2=new ObjeteamDatabaseLayout();
    			    	objteamdb2.transactionOpen();
    				   objteamdb2.model().setIdObjet(ob);
    				   objteamdb2.setUtilisateur(idSS);
    				   objteamdb2.setClient(bean.getIdClient());
    				   objteamdb2.model().setMasque(false);
    				   objteamdb2.model().setLun(this.lunSSchoisi);
    				   objteamdb2.model().setMar(this.maSSchoisi);
    				   objteamdb2.model().setMer(this.meSSchoisi);
    				   objteamdb2.model().setJeu(this.jeSSchoisi);
    				   objteamdb2.model().setVen(this.veSSchoisi);
    				   objteamdb2.model().setSam(this.saSSchoisi);
    				   objteamdb2.model().setDim(this.diSSchoisi);
    				   objteamdb2.save();
    				   objteamdb2.transactionCommit();
    				   objteamdb2.close();
    					   
    					   ObjeteamDatabaseLayout objteamdb3 =null;
    				    	objteamdb3=new ObjeteamDatabaseLayout();
    				    	objteamdb3.transactionOpen();
    					   objteamdb3.model().setIdObjet(ob);
    					   objteamdb3.setUtilisateur(idNS);
    					   objteamdb3.setClient(bean.getIdClient());
    					   objteamdb3.model().setMasque(false);
    					   objteamdb3.model().setLun(this.lunNSchoisi);
    					   objteamdb3.model().setMar(this.maNSchoisi);
    					   objteamdb3.model().setMer(this.meNSchoisi);
    					   objteamdb3.model().setJeu(this.jeNSchoisi);
    					   objteamdb3.model().setVen(this.veNSchoisi);
    					   objteamdb3.model().setSam(this.saNSchoisi);
    					   objteamdb3.model().setDim(this.diNSchoisi);
    					   objteamdb3.save();
    					   objteamdb3.transactionCommit();
    					   objteamdb3.close();
        	   
        	    
    	   }
    	   }

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
       }
       catch (Exception e) {
		   e.printStackTrace();
	   }
       finally
       {
    	   nvdb.close();
    	   nvobjDB.close();
    	   typeReponse.close();
    	   // effacer les champs
    	   this.sitechoisit="";
    	   this.batchoisit="";
    	   this.locchoisit="";
    	   this.obchoisit="";
    	   this.choixchoisi=0;
    	   this.barcodechoisi="";
    	   this.unitmesure="";
    	   this.lowlimitchoisi=null;
    	   this.hightlimitchoisi=null;
    	   this.lowBorderchoisi=null;
    	   this.highBorderchoisi=null;
    	   this. lowBorderAlertchoisi="";
    	   this. highBorderAlertchoisi="";
    	   this.lunFSchoisi=false; this.lunSSchoisi=false; this.lunNSchoisi=false;
    	   this.maFSchoisi=false; this.maSSchoisi=false; this.maNSchoisi=false;
    	   this.meFSchoisi=false; this.meSSchoisi=false; this.meNSchoisi=false;
    	   this.jeFSchoisi=false; this.jeSSchoisi=false; this.jeNSchoisi=false;
    	   this.veFSchoisi=false; this.veSSchoisi=false; this.veNSchoisi=false;
    	   this.saFSchoisi=false; this.saSSchoisi=false; this.saNSchoisi=false;
    	   this.diFSchoisi=false; this.diSSchoisi=false; this.diNSchoisi=false;
    	   
       }
	return "general_vue2?faces-redirect=true";
}
	 


private ArrayList<SelectItem> objetsInitialList = new ArrayList();
public ArrayList<SelectItem> getObjetsInitialList() {
	return objetsInitialList;
}

public void setObjetsInitialList(ArrayList<SelectItem> objetsInitialList) {
	this.objetsInitialList = objetsInitialList;
}

public ArrayList<SelectItem> listchoix()
{
	   if (this.objetsInitialList.size()>0)
		   this.objetsInitialList.clear();
 LoginBean bean = LoginUtil.getLoginBean();
 TypeReponseDatabaseLayout listObjetsInitial = null;
 
  try
  {
  listObjetsInitial = new TypeReponseDatabaseLayout();
  
	 
	  
   listObjetsInitial.listerListe(bean.getIdClient(), "Liste");
   
   
   

    ArrayList list = (ArrayList)listObjetsInitial.liste();
    this.objetsInitialList.add(new SelectItem(0,"Value"));
   for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
    { 
 	  
 		
			this.objetsInitialList.add(new SelectItem(((Typereponse)listObjetsInitial.liste().get(i.intValue())).getIdTypeReponse(), ((Typereponse)listObjetsInitial.liste().get(i.intValue())).getLibelle()));
		
		
  
  }}
  catch (Exception e)
  {
    
    
e.printStackTrace();
  } finally {
   listObjetsInitial.close();
  
   
  }

  return this.objetsInitialList;
}


/*** recherche tag
 * 
 */
public String tagChoisi;

public List<Themed> filteredThemes = new ArrayList<Themed>();
public List<Themed> themes =new ArrayList<Themed>();
public List<Themed> getThemes() {
	return themes;
}

public void setThemes(List<Themed> themes) {
	this.themes = themes;
}
public String getTagChoisi() {
	return tagChoisi;
}

public void setTagChoisi(String tagChoisi) {
	this.tagChoisi = tagChoisi;
}



public List<Themed> completeTheme(String query) {
	filteredThemes.clear();
 for(Themed item : themes){
    	 if(item.getName().toLowerCase().startsWith(query)) {
             filteredThemes.add(item);
         }
     }
	  
    return filteredThemes;
}
private Integer idTagChoisi;
public Integer getIdTagChoisi() {
	return idTagChoisi;
}

public void setIdTagChoisi(Integer idTagChoisi) {
	this.idTagChoisi = idTagChoisi;
}

public void afficherRow()
{
	LoginBean bean = LoginUtil.getLoginBean();
	
	
	NiveauObjetDatabaseLayout nvobjDB =null;
	nvobjDB=new NiveauObjetDatabaseLayout();
    nvobjDB.transactionOpen();
    
    NiveauDatabaseLayout nvdb = null;
    nvdb = new NiveauDatabaseLayout(); 	
    nvdb.transactionOpen();
	 
    TypeReponseDatabaseLayout typDB=null;
	typDB=new TypeReponseDatabaseLayout();
	typDB.transactionOpen();
    
	
	
	ObjeteamDatabaseLayout objteamdb =null;
	objteamdb=new ObjeteamDatabaseLayout();
	objteamdb.transactionOpen();
	
	ObjeteamDatabaseLayout objteamdbss =null;
	objteamdbss=new ObjeteamDatabaseLayout();
	objteamdbss.transactionOpen();
	
	ObjeteamDatabaseLayout objteamdbns =null;
	objteamdbns=new ObjeteamDatabaseLayout();
	objteamdbns.transactionOpen();
	
	UtilisateurDatabaseLayout utilisateur = null;
    utilisateur = new UtilisateurDatabaseLayout();
    utilisateur.transactionOpen();
	
    Integer idFS;
    Integer idSS;
    Integer idNS;
	try
    { this.listeView.clear();
   
		this.highBorderchoisi=0.0;
    	this.lowBorderchoisi=0.0;
    	this.lowlimitchoisi=0.0;
    	this.hightlimitchoisi=0.0;
		utilisateur.afficherByLibel("Spätschicht",bean.getIdClient());
		idSS=utilisateur.model().getIdUtilisateur();
		utilisateur.afficherByLibel("Frühschicht",bean.getIdClient());
		idFS=utilisateur.model().getIdUtilisateur();
		utilisateur.afficherByLibel("Nachtschicht",bean.getIdClient());
		idNS=utilisateur.model().getIdUtilisateur();
		// chercher LE objet ds tables niveau 
		
		System.out.println("tagChoisi.getId()"+tagChoisi);
		nvdb.afficherbyLabel(bean.getIdClient(), tagChoisi);
		ObjetVue objetvue = new ObjetVue();
		objetvue.setEquip(nvdb.model().getLibelle());
		idTagChoisi=nvdb.model().getIdNiveau();
       	objetvue.setBat(nvdb.model().getNiveau().getLibelle());
       	objetvue.setSite(nvdb.model().getNiveau().getNiveau().getLibelle());
       
       // objet
       	nvobjDB.afficherbyid(bean.getIdClient(), nvdb.model().getNiveauobjet().getIdNiveauObjet());
       	if (nvobjDB.model()!=null)
       	{
       		objetvue.setIdObj(nvobjDB.model().getIdNiveauObjet());
       		objetvue.setControle(nvobjDB.model().getLibelle());
       		
       		objetvue.setBarecode(nvobjDB.model().getCodeBarre());
       		// voir le type 
       		typDB.afficher(bean.getIdClient(), nvobjDB.model().getTypereponse().getIdTypeReponse());
       		if (typDB.model().getMode()==1) //val
       		{ 	objetvue.setUnit(nvobjDB.model().getUnitmeasure());
       			objetvue.setLowlimit(nvobjDB.model().getLowlimit());
       			objetvue.setHightlimit(nvobjDB.model().getHighlimit());
       			objetvue.setLowBorder(nvobjDB.model().getLowborder());
       			objetvue.setHighBorder(nvobjDB.model().getHighborder());
       		}
       		else  objetvue.setUnit(typDB.model().getLibelle());
       		
       		objetvue.setLowBorderAlert(nvobjDB.model().getLowborderAlert());
       		objetvue.setHighBorderAlert(nvobjDB.model().getHighborderAlert());
       		// les equipes
       		//fs
       		objteamdb.listerbyteamObjet(nvobjDB.model().getIdNiveauObjet(), idFS);
       		if (objteamdb.model()!=null)
       		{
       			objetvue.setLunFS(objteamdb.model().isLun());
       			objetvue.setMaFS(objteamdb.model().isMar());
       			objetvue.setMeFS(objteamdb.model().isMer());
       			objetvue.setJeFS(objteamdb.model().isJeu());
       			objetvue.setVeFS(objteamdb.model().isVen());
       			objetvue.setSaFS(objteamdb.model().isSam());
       			objetvue.setDiFS(objteamdb.model().isDim());
       		}
       	//ns
       		objteamdbns.listerbyteamObjet(nvobjDB.model().getIdNiveauObjet(), idNS);
       		if (objteamdbns.model()!=null)
       		{
       			objetvue.setLunNS(objteamdbns.model().isLun());
       			objetvue.setMaNS(objteamdbns.model().isMar());
       			objetvue.setMeNS(objteamdbns.model().isMer());
       			objetvue.setJeNS(objteamdbns.model().isJeu());
       			objetvue.setVeNS(objteamdbns.model().isVen());
       			objetvue.setSaNS(objteamdbns.model().isSam());
       			objetvue.setDiNS(objteamdbns.model().isDim());
       		}
       	 	//Ss
       		objteamdbss.listerbyteamObjet(nvobjDB.model().getIdNiveauObjet(), idSS);
       		if (objteamdbss.model()!=null)
       		{
       			objetvue.setLunSS(objteamdbss.model().isLun());
       			objetvue.setMaSS(objteamdbss.model().isMar());
       			objetvue.setMeSS(objteamdbss.model().isMer());
       			objetvue.setJeSS(objteamdbss.model().isJeu());
       			objetvue.setVeSS(objteamdbss.model().isVen());
       			objetvue.setSaSS(objteamdbss.model().isSam());
       			objetvue.setDiSS(objteamdbss.model().isDim());
       		}
       		
       		
       		}
       	
       	this.listeView.add(objetvue);
       	
       	
        
		
    }
	catch (Exception e) {
        System.out.println("Erreur liste vue");
        e.printStackTrace();
        
       }
	finally{
		nvobjDB.close();
		nvdb.close();
		typDB.close();
		
		objteamdb.close(); objteamdbns.close(); objteamdbss.close();
		utilisateur.close();
	}
	
	
}






Boolean onerow;
public Boolean getOnerow() {
	return onerow;
}

public void setOnerow(Boolean onerow) {
	this.onerow = onerow;
}

public void show()
{
	putFiltre();
	if (this.tagChoisi==null)
	{
		afficher2(); onerow=false;
		
	}
	else
		{afficherRow(); onerow=true;}
}

public List<ObjetVue> getLister()
{
 return this.listeView;
}


public void putFiltre()
{this.themes.clear();
	LoginBean bean = LoginUtil.getLoginBean();
	
	
	NiveauDatabaseLayout nvdb = null;
    nvdb = new NiveauDatabaseLayout(); 	
    nvdb.transactionOpen();
    nvdb.listerequi2(bean.getIdClient());
	
	Iterator iteq = nvdb.liste().iterator();
	   
    while (iteq.hasNext())
    {
    	
   	 Niveau nveq=(Niveau)iteq.next();
   	 
   	// les niveaux 
   
    this.themes.add(new Themed(nveq.getIdNiveau(), nveq.getLibelle(), nveq.getLibelle().toLowerCase())); 
}
}


public void reset()
{
	this.listeView.clear();
	this.tagChoisi=null;
	putFiltre();
	this.onerow=false;
	this.idTagChoisi=0;
	 // effacer les champs
	   this.sitechoisit="";
	   this.batchoisit="";
	   this.locchoisit="";
	   this.obchoisit="";
	   this.choixchoisi=0;
	   this.barcodechoisi="";
	   this.unitmesure="";
	   this.lowlimitchoisi=null;
	   this.hightlimitchoisi=null;
	   this.lowBorderchoisi=null;
	   this.highBorderchoisi=null;
	   this. lowBorderAlertchoisi="";
	   this. highBorderAlertchoisi="";
	   this.lunFSchoisi=false; this.lunSSchoisi=false; this.lunNSchoisi=false;
	   this.maFSchoisi=false; this.maSSchoisi=false; this.maNSchoisi=false;
	   this.meFSchoisi=false; this.meSSchoisi=false; this.meNSchoisi=false;
	   this.jeFSchoisi=false; this.jeSSchoisi=false; this.jeNSchoisi=false;
	   this.veFSchoisi=false; this.veSSchoisi=false; this.veNSchoisi=false;
	   this.saFSchoisi=false; this.saSSchoisi=false; this.saNSchoisi=false;
	   this.diFSchoisi=false; this.diSSchoisi=false; this.diNSchoisi=false;
}

private Integer triChoisi;
public Integer getTriChoisi() {
	return triChoisi;
}

public void setTriChoisi(Integer triChoisi) {
	this.triChoisi = triChoisi;
}

}
