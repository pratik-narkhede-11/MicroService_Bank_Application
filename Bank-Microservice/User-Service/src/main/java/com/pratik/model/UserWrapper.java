package com.pratik.model;

import lombok.Data;

@Data
public class UserWrapper
{
	private int accountNo;
	private String name;
	private String phno;
	private String email;

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

	@Override
	public String toString() {
		return "UserWrapper [accountNo=" + accountNo + ", name=" + name + ", phno=" + phno + ", email=" + email + "]";
	}

}
