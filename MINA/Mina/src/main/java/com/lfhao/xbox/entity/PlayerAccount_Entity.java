package com.lfhao.xbox.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
/**
 * 创建一个传递的对象类
 * @author Daryl
 *
 */
@Entity
@Table(name = "playerAccount")
public class PlayerAccount_Entity {
	private int id;
	private String name;
	private String emailAdress;
	private int sex;
	
	@Id
	@Column(name = "playerAccountID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	public int getId() {
			return id;
		 }

		 public void setId(int id) {
			 this.id = id;
		 }
		 
		 @Index(name="nameIndex")
		 public String getName() {
			 return name;
		 }

		 public void setName(String name) {
			 this.name = name;
		 }

		 public String getEmailAdress() {
			 return emailAdress;
		 }

		 public void setEmailAdress(String emailAdress) {
			 this.emailAdress = emailAdress;
		 }

		 public int getSex() {
			 return sex;
		 }

		 public void setSex(int sex) {
			 this.sex = sex;
		 }
	
	
}
