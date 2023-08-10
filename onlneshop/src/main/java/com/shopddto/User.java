package com.shopddto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String userContact;
	private String userEmail;
	private String userPassward;
	@OneToOne(cascade = CascadeType.ALL)
	private Cart userCart;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserContact() {
		return userContact;
	}
	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassward() {
		return userPassward;
	}
	public void setUserPassward(String userPassward) {
		this.userPassward = userPassward;
	}
	public Cart getUserCart() {
		return userCart;
	}
	public void setUserCart(Cart userCart) {
		this.userCart = userCart;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userContact=" + userContact + ", userEmail="
				+ userEmail + ", userPassward=" + userPassward + ", userCart=" + userCart + "]";
	}
	

}
