package com.shopdao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.shopddto.Admin;
import com.shopddto.Cart;
import com.shopddto.Item;
import com.shopddto.Product;
import com.shopddto.User;

public class ShopDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sri");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	static Scanner sc = new Scanner(System.in);

	public Item saveItem() {
		Item item = new Item();

		System.out.println("enter item name");
		String itemName = sc.nextLine();
		item.setItemName(itemName);

		System.out.println("enter item decription");
		String itemDecription = sc.nextLine();
		item.setItemDescription(itemDecription);

		System.out.println("enter item price");
		double itemPrice = sc.nextDouble();
		item.setItemPrice(itemPrice);
		sc.nextLine();

		System.out.println("enter item size");
		String itemSize = sc.nextLine();
		item.setItemSize(itemSize);

		System.out.println("enter item quantity");
		int itemQuantity = sc.nextInt();
		item.setItemQuantity(itemQuantity);

		et.begin();
		em.persist(item);
		et.commit();
		return item;
	}

	public void saveAdmin() {
		Admin admin = new Admin();

		System.out.println("enter admin name");
		String adminName = sc.nextLine();
		admin.setAdminName(adminName);

		System.out.println("enter admin email");
		String adminEmail = sc.nextLine();
		admin.setAdminEmail(adminEmail);

		System.out.println("enter admin password");
		String adminPassword = sc.nextLine();
		admin.setAdminPassword(adminPassword);

		System.out.println("enter admin contact");
		String adminContact = sc.nextLine();
		admin.setAdminContact(adminContact);

		et.begin();
		em.persist(admin);
		et.commit();

	}

	public void addItemToAdmin() {
		System.out.println("enter the admin id");
		int adminId = sc.nextInt();
		sc.nextLine();
		Admin admin = em.find(Admin.class, adminId);
		List<Item> items = admin.getAdminItem();

		Item item = saveItem();
		items.add(item);

		admin.setAdminItem(items);
		et.begin();
		em.merge(admin);
		et.commit();

	}

	public Admin updateAdmin(Admin admin, int id) {
		Admin exadmin = em.find(Admin.class, id);
		if (exadmin != null) {
			admin.setAdminId(id);
			et.begin();
			em.merge(admin);
			et.commit();
			return admin;
		} else {
			System.out.println("please check your admin id");
			return null;
		}
	}

	public Item removeItemFromAdmin() {
		System.out.println("enter admin id");
		int adminId = sc.nextInt();
		Admin admin = em.find(Admin.class, adminId);

		System.out.println("enter item id");
		int itemId = sc.nextInt();
		Item removeItem = em.find(Item.class, itemId);

		if (admin != null) {
			if (admin.getAdminItem().contains(removeItem)) {
				List<Item> items = admin.getAdminItem();
				items.remove(removeItem);
				admin.setAdminItem(items);

				updateAdmin(admin, adminId);
				et.begin();
				em.remove(removeItem);
				et.commit();

				return removeItem;
			} else {
				System.out.println("item is not present");
				return null;
			}
		} else {
			System.out.println("admin is not present");
			return null;

		}
	}

	public Admin findAdmin() {
		System.out.println("enter Admin id");
		int adminId = sc.nextInt();
		Admin exadmin = em.find(Admin.class, adminId);
		return exadmin;
	}

	public Item findItem() {
		System.out.println("enter Item id");
		int itemId = sc.nextInt();
		Item exItem = em.find(Item.class, itemId);
		return exItem;
	}

	public Item findItemByName() {
		System.out.println("enter item name");
		String itemName = sc.nextLine();
		Query q1 = em.createQuery("select item from Item item where itemName=?1");
		q1.setParameter(1, itemName);
		Item item = null;
		try {
			item = (Item) q1.getSingleResult();
		} catch (Exception e) {
			System.out.println("item is not present");
		}
		return item;
	}

	public Item removItemFromAdminUsinItemName() {
		Admin admin = findAdmin();
		sc.nextLine();
		Item removeItem = findItemByName();
		if (admin.getAdminItem().contains(removeItem)) {
			List<Item> items = admin.getAdminItem();
			items.remove(removeItem);
			admin.setAdminItem(items);

			updateAdmin(admin, admin.getAdminId());
			et.begin();
			em.remove(removeItem);
			et.commit();

			return removeItem;
		} else {
			System.out.println("item is not present");
			return null;
		}

	}

	public User saveUser() {
		User user = new User();

		System.out.println("enter user name");
		String userName = sc.nextLine();
		user.setUserName(userName);

		System.out.println("enter user email");
		String userEmail = sc.nextLine();
		user.setUserEmail(userEmail);

		System.out.println("enter user password");
		String userPassword = sc.nextLine();
		user.setUserPassward(userPassword);

		System.out.println("enter user contact");
		String userContact = sc.nextLine();
		user.setUserContact(userContact);

		Cart cart = new Cart();
		user.setUserCart(cart);

		et.begin();
		em.persist(user);
		et.commit();
		return user;

	}

	public void addProductToCart() {
		System.out.println("enter user/cart id");
		int cartId = sc.nextInt();
		Cart cart = em.find(Cart.class, cartId);
		System.out.println("enter the item id");
		int itemId = sc.nextInt();
		Item item = em.find(Item.class, itemId);
		System.out.println("enter the product quantity");
		int productQuantity = sc.nextInt();

		// save product
		Product product = new Product();
		product.setProductDescription(item.getItemDescription());
		product.setProductName(item.getItemName());
		product.setProductPrice(item.getItemPrice());
		product.setProductSize(item.getItemSize());
		product.setProductQuantity(productQuantity);
		saveProduct(product);

		List<Product> products = cart.getCartProduct();
		products.add(product);
		cart.setCartProduct(products);

		double total = 0;
		for (Product pr : products) {
			total = total + (pr.getProductPrice() * productQuantity);
		}
		cart.setTotalPrice(total);

		item.setItemQuantity(item.getItemQuantity() - productQuantity);

		// update cart
		updateCart(cart, cart.getCartId());
		updateItem(itemId, item);
		// update items

	}

	public void updateItem(int itemid, Item item) {
		et.begin();
		em.merge(item);
		et.commit();
	}

	public Cart updateCart(Cart cart, int cartid) {

		Cart excart = em.find(Cart.class, cartid);
		if (excart != null) {
			cart.setCartId(cartid);
			et.begin();
			em.merge(cart);
			et.commit();
			return cart;
		} else {
			System.out.println("please check your cart id");
			return null;
		}

	}

	public Product saveProduct(Product product) {

		et.begin();
		em.persist(product);
		et.commit();
		return product;
	}

	public Product removeProductFromCart() {
		System.out.println("enter the cartid");
		int cartid = sc.nextInt();
		System.out.println("enter the productid");
		int productid = sc.nextInt();
		System.out.println("enter the itemid");
		int itemid = sc.nextInt();
		Cart cart = em.find(Cart.class, cartid);

		if (cart != null) {
			List<Product> products = cart.getCartProduct();
			Product product = em.find(Product.class, productid);
			if (product != null) {
				products.remove(product);
				cart.setCartProduct(products);

				double total = 0;
				for (Product pr : products) {
					total = total - (pr.getProductPrice() * pr.getProductQuantity());
				}
				cart.setTotalPrice(total);
				updateCart(cart, cart.getCartId());

//				Query q = em.createQuery("select product where Product prroduct where productName=1?");
//				q.setParameter(1, product.getProductName());
//				List<Product> exiproducts = q.getResultList();
				Item item = em.find(Item.class, itemid);
				item.setItemQuantity(item.getItemQuantity() + product.getProductQuantity());
				updateItem(cartid, item);

				et.begin();
				em.remove(product);
				et.commit();

				return product;
			} else {
				System.out.println("product is not present");
				return null;
			}

		} else {
			System.out.println("cart is not present");
			return null;
		}
	}

	public void cartCheckOut() {
		System.out.println("enter the cart id");
		int cartId = sc.nextInt();
		Cart cart = em.find(Cart.class, cartId);

		boolean flag = true;
		while (flag) {
			System.out.println("total amt is " + cart.getTotalPrice());
			System.out.println("enter the amt");
			double money = sc.nextInt();
			if (money > cart.getTotalPrice()) {
				System.out.println("thank you please keep your chage " + (money - cart.getTotalPrice()));
				flag = false;
			} else if (money == cart.getTotalPrice()) {
				System.out.println("thak you please come again");
				flag = false;
			} else {
				System.out.println("please give more or equal money to proceed");
			}
		}
	}

	public User findUser() {
		System.out.println("ENTER THE USER ID");
		int userId = sc.nextInt();
		User user = em.find(User.class, userId);
		return user;
		
	}

	public Product findProduct() {
		System.out.println("ENTER THE PRODUCT ID");
		int productId = sc.nextInt();
		Product product = em.find(Product.class, productId);
		if (product!=null) {
			System.out.println(product);
		} else {
           System.out.println("PRODUCT IS NOT PRESENT");
		}
		return product;
	}

	public boolean checkAdmin() {
		Admin admin = findAdmin();
		String exipassword = admin.getAdminPassword();
		System.out.println("ENTER THE PASSWORD");sc.nextLine();
		String adminpassword = sc.nextLine();
		if (exipassword.equals(adminpassword)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkUser() {
		User user = findUser();
		String exipassword = user.getUserPassward();
		System.out.println("ENTER THE PASSWORD");sc.nextLine();
		String userpassword = sc.nextLine();
		if (exipassword.equals(userpassword)) {
			return true;
		} else {
			return false;
		}
	}
}
