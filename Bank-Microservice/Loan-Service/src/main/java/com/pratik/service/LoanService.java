package com.pratik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pratik.dao.LoanDao;
import com.pratik.model.UserData;
import com.pratik.model.UserLoan;

import jakarta.transaction.Transactional;

@Service
public class LoanService 
{
	@Autowired
	LoanDao loanRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public int createUser(int bankAccountNo, String loanPassword) 
	{	
		UserLoan ul = new UserLoan();
		ul.setBankAccountNo(bankAccountNo);
		ul.setLoanPassword(loanPassword);
		loanRepo.save(ul);
		return ul.getLoanAccountNo();
	}
	
	@Transactional
	public String checkLoan(int amount, int accountNo, String password)
	{
		UserLoan ul = loanRepo.findByAccountNo(accountNo);
		if(password.equals(ul.getLoanPassword()))
		{
			int bankAccountNo = loanRepo.fetchBankAccNo(accountNo);
			int bankBalance = restTemplate.getForObject("http://USER-SERVICE/bank/userdata/"+bankAccountNo, UserData.class).getBalance();
			if((bankBalance*5 >= amount ) &&(ul.getCurrentLoan() == 0))
			{
				getLoan(amount, accountNo);
				ResponseEntity<String> response = restTemplate.postForEntity("http://USER-SERVICE/bank/deposit?amount=" + amount + "&accountNo=" + bankAccountNo, null, String.class);		
				System.out.println(response);
				return "Loan Granted and Amount Successfully Deposited in your bank account";
			}
			else
			{
				if(ul.getCurrentLoan() == 0) 
					return "Loan Rejacted \nYou can get maximum Rs. "+5 * bankBalance+" as a total loan.\n(This Bank can offer loan upto 5x times your current balance)";
				else 
					return "Clear the Previous Loan First";
			}
		} 
		else 
			return "Loan Password Incorrect";
	}

	@Transactional
	public void getLoan(int amount, int accountNo)
	{
		UserLoan ul = loanRepo.findByAccountNo(accountNo);
		int newLoan = ul.getLoanTaken()+amount;
		loanRepo.updateLoanTaken(accountNo, newLoan);
		loanRepo.updateCurrentLoan(accountNo, newLoan);
		
	}

	@Transactional
	public String repay(int amount, int accountNo, String password)
	{
		UserLoan ul = loanRepo.findByAccountNo(accountNo);
		if(password.equals(ul.getLoanPassword()))
		{
			int bankAccountNo = loanRepo.fetchBankAccNo(accountNo);
			UserData ud = restTemplate.getForObject("http://USER-SERVICE/bank/userdata/"+bankAccountNo, UserData.class);
			if((amount <= ul.getLoanTaken()) && (amount <= ud.getBalance()))
			{
				ResponseEntity<String> response = restTemplate.postForEntity("http://USER-SERVICE/bank/withdraw?amount=" + amount + "&accountNo=" + bankAccountNo + "&password=" +ud.getPassword(), null, String.class);
				System.out.println(response);
				int newLoan = ul.getCurrentLoan() - amount;
				loanRepo.updateCurrentLoan(accountNo, newLoan);
				loanRepo.updateLoanRepayed(accountNo, ul.getLoanRepayed()+amount);
				return "Transaction Successful";
			}
			else if(amount > ul.getCurrentLoan()) {
				return "Excess Amount!! Remaining Loan is Just Rs. "+ ul.getCurrentLoan();
			} else {
				return "Insufficient Balance";
			}
		} else {
			return "Password Incorrect";
		}
	}

	
}
