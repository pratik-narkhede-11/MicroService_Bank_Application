package com.pratik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratik.model.UserData;
import com.pratik.model.UserWrapper;
import com.pratik.service.UserService;

@RestController
@RequestMapping("bank")
public class UserController 
{
	@Autowired
	UserService userObj;
	
	@PostMapping("create")
	public String createUser(@RequestParam("name") String name, @RequestParam("phno") String phno,
            				@RequestParam("email") String email, @RequestParam("password") String password)
	{
		return "Your New Account Number is : "+userObj.createUser(name, phno, email, password);

	}
	
	@GetMapping(value="user/{account}", produces={"application/json"})
	public ResponseEntity<UserWrapper> fetchUser(@PathVariable("account") int accountNo)
	{
		return userObj.getUserByAccountNo(accountNo);
	}
	
	@PostMapping("deposit")
	public String deposit(@RequestParam(name = "amount") int amount, @RequestParam(name = "accountNo") int accountNo)
	{
		userObj.deposit(amount, accountNo);
		return "Amount Successfully Deposited ";
	}

	@PostMapping("withdraw")
	public String withdraw(@RequestParam(name = "amount") int amount, @RequestParam(name = "accountNo") int accountNo,
							@RequestParam(name = "password") String password)
	{
		return userObj.withdraw(amount, accountNo, password);
	}

	@GetMapping(value="balance/{account}", produces={"application/json"})
	public ResponseEntity<Integer> fetchBalance(@PathVariable("account") int accountNo)
	{
		return userObj.getBalance(accountNo);
	}
	
	@GetMapping(value="userdata/{account}", produces={"application/json"})
	public ResponseEntity<UserData> fetchUserData(@PathVariable("account") int accountNo)
	{
		return userObj.getUserDataByAccountNo(accountNo);
	}
}
