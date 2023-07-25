package com.sri.bankapplication;

import java.util.HashMap;

public class Bank {
	private HashMap<String, Double> data;

	public Bank() {
		data = new HashMap<>();
		data.put("1234567890", 10.0);
		data.put("1111111111", 20.2);
		data.put("22222222222", 30.0);
	}

	public void createAccount(String acno) {
		if (acno.length() == 10) {
			if (!data.containsKey(acno)) {
				data.put(acno, 0.0);
				System.err.println("Account Created Sucessfully");
				System.err.println("Account  Number is " + acno + " Avaliable Balance is " + data.get(acno));
			} else {
				System.err.println("Account " + acno + "  Already There");

			}
		} else {
			System.err.println("Enter 10 Digit Account Number");
		}
	}

	public void deposit(String acno, double amt) {
		if (data.containsKey(acno)) {
			double bal = data.get(acno);
			data.put(acno, (bal + amt));
			System.err.println("Available Balance " + data.get(acno));

		} else {
			System.err.println("Account " + acno + "  Number is invalid");

		}
	}

	public void withdraw(String acno, double amt) {
		if (data.containsKey(acno)) {
			if (data.get(acno) >= amt) {
				double bal = data.get(acno);
				data.put(acno, (bal - amt));
				System.err.println("Available Balance " + data.get(acno));
			} else {
				System.err.println("Insufficient Balance");
			}

		} else {
			System.err.println("Account" + acno + " Number is invalid");

		}
	}

	public void checkBalance(String acno) {
		if (data.containsKey(acno)) {
			double bal = data.get(acno);
			System.err.println("Available Balance " + bal);

		} else {
			System.err.println("Account " + acno + "  Number is invalid");

		}

	}

}
