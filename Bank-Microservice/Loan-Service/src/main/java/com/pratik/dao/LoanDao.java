package com.pratik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pratik.model.UserLoan;

public interface LoanDao extends JpaRepository<UserLoan, Integer>
{
	@Query(value = "SELECT * FROM user_loan WHERE account_no = :loanAccountNo", nativeQuery = true)
	UserLoan findByAccountNo(int loanAccountNo);
	
	@Modifying
	@Query(value = "UPDATE user_loan SET loan_taken = :newLoan WHERE account_no = :accountNo", nativeQuery = true)
	void updateLoanTaken(int accountNo, int newLoan);
	
	@Modifying
	@Query(value = "UPDATE user_loan SET loan_repayed = :newLoan WHERE account_no = :accountNo", nativeQuery = true)
	void updateLoanRepayed(int accountNo, int newLoan);
	
	@Modifying
	@Query(value = "UPDATE user_loan SET current_loan = :newLoan WHERE account_no = :accountNo", nativeQuery = true)
	void updateCurrentLoan(int accountNo, int newLoan);

	@Query(value = "SELECT bank_account_no FROM user_loan WHERE account_no = :loanAccountNo", nativeQuery = true)
	int fetchBankAccNo(int loanAccountNo);
	
}
