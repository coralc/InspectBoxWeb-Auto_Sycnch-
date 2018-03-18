package com.itirion.bean;

public class IndexBean {
	

	public IndexBean() {
		super();
	}
	public IndexBean(String pageText, String pageIndex) {
		super();
		this.pageText = pageText;
		this.pageIndex = pageIndex;
	}
	private String pageText;
	private String pageIndex;
	public String getPageText() {
		return pageText;
	}
	public void setPageText(String pageText) {
		this.pageText = pageText;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	
}
