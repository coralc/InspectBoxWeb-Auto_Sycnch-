package com.inspectbox.objetLayout;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

public class ObjetVue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idNivS;
	private Integer idNivB;
	private Integer idNivE;
	private Integer idNivE1;
	private Integer idvalreponse;
	private Integer idtypreponse;
	public Integer getIdtypreponse() {
		return idtypreponse;
	}
	public void setIdtypreponse(Integer idtypreponse) {
		this.idtypreponse = idtypreponse;
	}
	public Integer getIdvalreponse() {
		return idvalreponse;
	}
	public void setIdvalreponse(Integer idvalreponse) {
		this.idvalreponse = idvalreponse;
	}
	public Integer getIdNivE1() {
		return idNivE1;
	}
	public void setIdNivE1(Integer idNivE1) {
		this.idNivE1 = idNivE1;
	}

	private Integer idObj;
	 public Integer getIdNivS() {
		return idNivS;
	}
	public void setIdNivS(Integer idNivS) {
		this.idNivS = idNivS;
	}
	public Integer getIdNivB() {
		return idNivB;
	}
	public void setIdNivB(Integer idNivB) {
		this.idNivB = idNivB;
	}
	public Integer getIdNivE() {
		return idNivE;
	}
	public void setIdNivE(Integer idNivE) {
		this.idNivE = idNivE;
	}
	public Integer getIdObj() {
		return idObj;
	}
	public void setIdObj(Integer idObj) {
		this.idObj = idObj;
	}
	
	private String site;
	 private String bat;
	 private String equip;
	 private String controle;
	 private String unit;
	 private Double lowlimit;
	 private Double hightlimit;
	 private Double lowBorder;
	 private Double highBorder;
	 private String lowBorderAlert;
	 private String highBorderAlert;
	 private String barecode;
	 private String autre;
	 private String team;
	 public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}

	
	public String getAutre() {
		return autre;
	}
	public void setAutre(String autre) {
		this.autre = autre;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getBat() {
		return bat;
	}
	public void setBat(String bat) {
		this.bat = bat;
	}
	public String getEquip() {
		return equip;
	}
	public void setEquip(String equip) {
		this.equip = equip;
	}
	public String getControle() {
		return controle;
	}
	public void setControle(String controle) {
		this.controle = controle;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getLowlimit() {
		return lowlimit;
	}
	public void setLowlimit(Double lowlimit) {
		this.lowlimit = lowlimit;
	}
	public Double getHightlimit() {
		return hightlimit;
	}
	public void setHightlimit(Double hightlimit) {
		this.hightlimit = hightlimit;
	}
	public Double getLowBorder() {
		return lowBorder;
	}
	public void setLowBorder(Double lowBorder) {
		this.lowBorder = lowBorder;
	}
	public Double getHighBorder() {
		return highBorder;
	}
	public void setHighBorder(Double highBorder) {
		this.highBorder = highBorder;
	}
	public String getLowBorderAlert() {
		return lowBorderAlert;
	}
	public void setLowBorderAlert(String lowBorderAlert) {
		this.lowBorderAlert = lowBorderAlert;
	}
	public String getHighBorderAlert() {
		return highBorderAlert;
	}
	public void setHighBorderAlert(String highBorderAlert) {
		this.highBorderAlert = highBorderAlert;
	}
	public String getBarecode() {
		return barecode;
	}
	public void setBarecode(String barecode) {
		this.barecode = barecode;
	}
	 private boolean lunFS; 
	 private boolean lunSS; 
	 private boolean lunNS;
	public boolean isLunFS() {
		return lunFS;
	}
	public void setLunFS(boolean lunFS) {
		this.lunFS = lunFS;
	}
	public boolean isLunSS() {
		return lunSS;
	}
	public void setLunSS(boolean lunSS) {
		this.lunSS = lunSS;
	}
	public boolean isLunNS() {
		return lunNS;
	}
	public void setLunNS(boolean lunNS) {
		this.lunNS = lunNS;
	} 
	private boolean maFS; 
	 private boolean maSS; 
	 private boolean maNS;
	 
	 private boolean meFS; 
	 private boolean meSS; 
	 private boolean meNS;
	 
	 private boolean jeFS; 
	 private boolean jeSS; 
	 private boolean jeNS;
	 
	 private boolean veFS; 
	 private boolean veSS; 
	 private boolean veNS;
	 
	 private boolean saFS; 
	 private boolean saSS; 
	 private boolean saNS;
	 
	 private boolean diFS; 
	 private boolean diSS; 
	 private boolean diNS;
	public boolean isMaFS() {
		return maFS;
	}
	public void setMaFS(boolean maFS) {
		this.maFS = maFS;
	}
	public boolean isMaSS() {
		return maSS;
	}
	public void setMaSS(boolean maSS) {
		this.maSS = maSS;
	}
	public boolean isMaNS() {
		return maNS;
	}
	public void setMaNS(boolean maNS) {
		this.maNS = maNS;
	}
	public boolean isMeFS() {
		return meFS;
	}
	public void setMeFS(boolean meFS) {
		this.meFS = meFS;
	}
	public boolean isMeSS() {
		return meSS;
	}
	public void setMeSS(boolean meSS) {
		this.meSS = meSS;
	}
	public boolean isMeNS() {
		return meNS;
	}
	public void setMeNS(boolean meNS) {
		this.meNS = meNS;
	}
	public boolean isJeFS() {
		return jeFS;
	}
	public void setJeFS(boolean jeFS) {
		this.jeFS = jeFS;
	}
	public boolean isJeSS() {
		return jeSS;
	}
	public void setJeSS(boolean jeSS) {
		this.jeSS = jeSS;
	}
	public boolean isJeNS() {
		return jeNS;
	}
	public void setJeNS(boolean jeNS) {
		this.jeNS = jeNS;
	}
	public boolean isVeFS() {
		return veFS;
	}
	public void setVeFS(boolean veFS) {
		this.veFS = veFS;
	}
	public boolean isVeSS() {
		return veSS;
	}
	public void setVeSS(boolean veSS) {
		this.veSS = veSS;
	}
	public boolean isVeNS() {
		return veNS;
	}
	public void setVeNS(boolean veNS) {
		this.veNS = veNS;
	}
	public boolean isSaFS() {
		return saFS;
	}
	public void setSaFS(boolean saFS) {
		this.saFS = saFS;
	}
	public boolean isSaSS() {
		return saSS;
	}
	public void setSaSS(boolean saSS) {
		this.saSS = saSS;
	}
	public boolean isSaNS() {
		return saNS;
	}
	public void setSaNS(boolean saNS) {
		this.saNS = saNS;
	}
	public boolean isDiFS() {
		return diFS;
	}
	public void setDiFS(boolean diFS) {
		this.diFS = diFS;
	}
	public boolean isDiSS() {
		return diSS;
	}
	public void setDiSS(boolean diSS) {
		this.diSS = diSS;
	}
	public boolean isDiNS() {
		return diNS;
	}
	public void setDiNS(boolean diNS) {
		this.diNS = diNS;
	}
	 
	 
}
