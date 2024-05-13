package com.pratik.model;

public class UserData
{
	private int accountNo;
	private String name;
	private String phno;
	private String email;
	private String password;
	private int balance = 0;

	public UserData() {}
	
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "User [accountNo=" + accountNo + ", name=" + name + ", phno=" + phno + ", email=" + email + ", password="
				+ password + "]";
	}

}
