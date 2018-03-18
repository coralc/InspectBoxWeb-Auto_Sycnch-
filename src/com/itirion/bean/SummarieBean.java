package com.itirion.bean;

public class SummarieBean {
	
	private String serialno;
	private String niveaulibell;
	private String niveauid;
	private String pageindex;
	
	
	public SummarieBean() {
		
	}
	public SummarieBean(String serialno, String niveaulibell, String niveauid,
			String pageindex) {
		super();
		this.serialno = serialno;
		this.niveaulibell = niveaulibell;
		this.niveauid = niveauid;
		this.pageindex = pageindex;
	}

	
	public String getNiveauid() {
		return niveauid;
	}
	public void setNiveauid(String niveauid) {
		this.niveauid = niveauid;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}


	public String getNiveaulibell() {
		return niveaulibell;
	}
	public void setNiveaulibell(String niveaulibell) {
		this.niveaulibell = niveaulibell;
	}
	public String getPageindex() {
		return pageindex;
	}
	public void setPageindex(String pageindex) {
		this.pageindex = pageindex;
	}
	
	@Override
	public String toString() {
		return "SummarieBean [serialno=" + serialno + ", niveaulibell="
				+ niveaulibell + ", niveauid=" + niveauid + ", pageindex="
				+ pageindex + "]";
	}
	
	
}
