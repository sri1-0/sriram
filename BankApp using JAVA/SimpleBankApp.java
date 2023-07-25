package com.sri.bankapplication;

import java.util.Scanner;

public class SimpleBankApp {

	public static void main(String[] args) {
		Bank bank = new Bank();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("Welcome to Bank Application");
			System.out.println("\n 1.Create Account "
					+ "\n 2.Deposit Amount "
					+ "\n 3.Withdraw Amt" 
			        + "\n 4.Check Balance" 
					+ "\n 5.Exit \n");
			System.out.println("Enter Your Choice");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Enter 10 Digit Account Number");
				String acno = sc.nextLine();
				bank.createAccount(acno);
				break;
			case 2:
				System.out.println("Enter 10 Digit Account Number");
				acno = sc.nextLine();
				System.out.println("Enter The Amount To Deposit");
				double amt = sc.nextDouble();
				bank.deposit(acno, amt);
				break;
			case 3:
				System.out.println("Enter 10 Digit Account Number");
				acno = sc.nextLine();
				System.out.println("Enter The Amount To Deposit");
				amt = sc.nextDouble();
				bank.withdraw(acno, amt);
				break;
			case 4:
				System.out.println("Enter 10 Digit Account Number");
				acno = sc.nextLine();
				bank.checkBalance(acno);
				break;
			case 5:
				System.out.println("Thank you...!!");
				sc.close();
				System.exit(0);

			default:
				System.out.println("Enter Valid Choice");
				break;
			}
		}
	}

}
