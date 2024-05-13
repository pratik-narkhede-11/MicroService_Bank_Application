package com.pratik.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserLoan 
{
	@Id
	@Column (name="account_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanAccountNo;
	
	private int bankAccountNo;
	private String loanPassword;
	private int loanTaken = 0;
	private int loanRepayed = 0;
	private int currentLoan = 0;
	
	public UserLoan() {}
	
	public UserLoan(int loanAccountNo, int bankAccountNo, String password, int loanTaken, int loanRepayed,
			int currentLoan) {
		super();
		this.loanAccountNo = loanAccountNo;
		this.bankAccountNo = bankAccountNo;
		this.loanPassword = password;
		this.loanTaken = loanTaken;
		this.loanRepayed = loanRepayed;
		this.currentLoan = currentLoan;
	}
	
	
	public int getLoanAccountNo() {
		return loanAccountNo;
	}
	public void setLoanAccountNo(int loanAccountNo) {
		this.loanAccountNo = loanAccountNo;
	}
	public int getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(int bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	
	public String getLoanPassword() {
		return loanPassword;
	}

	public void setLoanPassword(String loanPassword) {
		this.loanPassword = loanPassword;
	}

	public int getLoanTaken() {
		return loanTaken;
	}
	public void setLoanTaken(int loanTaken) {
		this.loanTaken = loanTaken;
	}
	public int getLoanRepayed() {
		return loanRepayed;
	}
	public void setLoanRepayed(int loanRepayed) {
		this.loanRepayed = loanRepayed;
	}
	public int getCurrentLoan() {
		return currentLoan;
	}
	public void setCurrentLoan(int currentLoan) {
		this.currentLoan = currentLoan;
	}

	@Override
	public String toString() {
		return "UserLoan [loanAccountNo=" + loanAccountNo + ", BankAccountNo=" + bankAccountNo + ", loanPassword="
				+ loanPassword + ", loanTaken=" + loanTaken + ", loanRepayed=" + loanRepayed + ", currentLoan="
				+ currentLoan + "]";
	}
	

	
	
	
}
