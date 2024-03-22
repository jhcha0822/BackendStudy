package com.sds.newsapp.comments;


// DTO

public class Comments {
	private int comments_idx;
	private String msg;
	private String cwriter;
	private String cregdate;
	private int news_idx;
	
	public int getComments_idx() {
		return comments_idx;
	}
	public void setComments_idx(int comments_idx) {
		this.comments_idx = comments_idx;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCwriter() {
		return cwriter;
	}
	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}
	public String getCregdate() {
		return cregdate;
	}
	public void setCregdate(String cregdate) {
		this.cregdate = cregdate;
	}
	public int getNews_idx() {
		return news_idx;
	}
	public void setNews_idx(int news_idx) {
		this.news_idx = news_idx;
	}
	
	
}
