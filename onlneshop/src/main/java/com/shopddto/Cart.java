package com.shopddto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private double totalPrice;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> cartProduct;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<Product> getCartProduct() {
		return cartProduct;
	}
	public void setCartProduct(List<Product> cartProduct) {
		this.cartProduct = cartProduct;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", totalPrice=" + totalPrice + ", cartProduct=" + cartProduct + "]";
	}
	
	

}
