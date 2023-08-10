package com.shopdrivers;

import java.util.Scanner;

import com.shopdao.ShopDao2;
import com.shopddto.Admin;
import com.shopddto.Product;
import com.shopddto.User;

public class ShopDriver {
	public static void main(String[] args) {
		ShopDao2 sdao = new ShopDao2();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome To Shopping App");
		boolean mainflag = true;
		while (mainflag) {
			System.out.println("Select What Action Do You Need!!");
			System.out.println("\n 1.LOGIN ADMIN \n 2.LOGIN USER \n 3.NEW ADMIN \n 4.NEW USER \n 5.EXIT");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("enter admin id");
				int adminId = sc.nextInt();
                Admin admin=sdao.findAdmin(adminId);
				if (sdao.checkAdmin(admin) == true) {
					boolean flag1 = true;
					while (flag1) {
						System.out.println("\n 1.NEW ITEM TO ADMIN  \n 2.SEARCH ITEM \n 3.DELETE ITEM  \n 4.LOGOUT \n");
						int input = sc.nextInt();
						switch (input) {
						case 1:
							sdao.addItemToAdmin(admin);
							break;
						case 2:
							sdao.findItem();
							break;
						case 3:
							sdao.removeItemFromAdmin(admin);
							break;
						case 4:
							flag1 = false;
							break;
						default:
							System.out.println("Please Check Your Actions");
							break;
						}
					}
				} 
				else {
					System.out.println("ADMIN IS NOT PRESENT OR DEATAILS IS WRONG");
				}
				break;
			case 2:
				User user=sdao.findUser();
				if (sdao.checkUser(user) == true) {
					boolean flag1 = true;
					while (flag1) {
						System.out.println(
								"\n 1.SEARCH PRODUCT \n 2.NEW PRODUCT TO CART \n 3.DELETE PRODUCT \n 4.PAYMENT \n 5.LOGOUT \n");
						int input = sc.nextInt();
						switch (input) {
						case 1:
							sdao.findProduct();
							break;
						case 2:
							sdao.addProductToCart();
							break;
						case 3:
							Product reproduct = sdao.removeProductFromCart();
							if (reproduct != null) {
								System.out.println(reproduct.getProductName() + "IS REMOVED");
							} else {
								System.out.println("PRODUCT IS NOT PRESENT");
							}
							break;
						case 4:
							sdao.cartCheckOut();
							break;
						case 5:
							flag1 = false;
							break;
						default:
							System.out.println("Please Check Your Actions");
							break;
						}
					}
				} 
				else {
					System.out.println("USER IS NOT PRESENT OR DEATAILS IS WRONG");
				}

				break;
			case 3:
				sdao.saveAdmin();
				break;
			case 4:
				sdao.saveUser();
				break;
			case 5:
				System.out.println("THANK YOU!!!");
				mainflag = false;
				break;
			default:
				System.out.println("PLEASE SELECT THE GIVEN CHOICE");
				break;
			}

		}
	}

}
