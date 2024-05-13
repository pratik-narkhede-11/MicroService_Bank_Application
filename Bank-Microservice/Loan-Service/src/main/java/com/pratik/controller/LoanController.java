package com.pratik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pratik.model.UserData;
import com.pratik.service.LoanService;

@RestController
@RequestMapping("bankloan")
public class LoanController 
{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	LoanService loan;
	
	@PostMapping("create")
	public String createUser(@RequestParam("bankAccountNo") int bankAccountNo, @RequestParam("bankPassword") String password, 
							@RequestParam("loanPassword") String loanPassword)
	{
		String pass = restTemplate.getForObject("http://USER-SERVICE/bank/userdata/"+bankAccountNo, UserData.class).getPassword();
		if(pass.equals(password))
			return "Your New Loan Account Number is : "+loan.createUser(bankAccountNo, loanPassword);
		else
			return "Bank Password Incorrect";
	}
	
	@PostMapping("getloan")
	public String getLoan(@RequestParam(name = "amount") int amount, @RequestParam(name = "accountNo") int loanAccountNo,
						@RequestParam(name = "password") String loanPassword)
	{
		return loan.checkLoan(amount, loanAccountNo, loanPassword);
	}
	

	@PostMapping("repayloan")
	public String repay(@RequestParam(name = "amount") int amount, @RequestParam(name = "accountNo") int loanAccountNo,
				@RequestParam(name = "password") String password)
	{
		return loan.repay(amount, loanAccountNo, password);
	}
}
