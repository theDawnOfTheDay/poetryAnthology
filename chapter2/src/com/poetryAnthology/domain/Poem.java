package com.poetryAnthology.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Poem implements Serializable{
	public String getWorksAuthor() {
		return worksAuthor;
	}
	public void setWorksAuthor(String worksAuthor) {
		this.worksAuthor = worksAuthor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<String> getAppreciate() {
		return appreciate;
	}
	public void setAppreciate(ArrayList<String> appreciate) {
		this.appreciate = appreciate;
	}
	public ArrayList<String> getKeyword() {
		return keyword;
	}
	public void setKeyword(ArrayList<String> keyword) {
		this.keyword = keyword;
	}
	private String worksAuthor;
	private String title;
	private String text;
	private ArrayList<String> appreciate;
	private ArrayList<String> keyword;
}
