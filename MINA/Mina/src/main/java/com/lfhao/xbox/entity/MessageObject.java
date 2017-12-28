package com.lfhao.xbox.entity;

import java.io.Serializable;

public class MessageObject implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 8474181485445960418L;

	public MessageObject(int id, String name, String content){
		 this.id=id;
		 this.name=name;
		 this.setContent(content);
	 }
	
	 private int id;
	 private String name;
	 private String content;
	 
	 public int getId() {
		 return id;
	 }
	 
	 public void setId(int id) {
		 this.id = id;
	 }
	 
	 public String getName() {
		 return name;
	 }
	
	 public void setName(String name) {
		 this.name = name;
	 }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
