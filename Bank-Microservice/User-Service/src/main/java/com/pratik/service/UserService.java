package com.pratik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pratik.dao.UserDao;
import com.pratik.model.UserData;
import com.pratik.model.UserWrapper;

import jakarta.transaction.Transactional;

@Service
public class UserService 
{
	@Autowired
	UserDao userRepo;
	
	public int createUser(String name, String phno, String email, String password)
	{
		UserData ud = new UserData();
		ud.setEmail(email);
		ud.setName(name);
		ud.setPassword(password);
		ud.setPhno(phno);
		userRepo.save(ud);
		return ud.getAccountNo();
	}

	public ResponseEntity<UserWrapper> getUserByAccountNo(int accountNo)
	{
		UserData ud =  userRepo.findByAccountNo(accountNo);
		UserWrapper uw = new UserWrapper();
		uw.setAccountNo(ud.getAccountNo());
		uw.setEmail(ud.getEmail());
		uw.setName(ud.getName());
		uw.setPhno(ud.getPhno());

		return new ResponseEntity<>(uw,HttpStatus.OK);
	}
	
	@Transactional
	public void deposit(int amount, int accountNo)
	{
		UserData ud = userRepo.findByAccountNo(accountNo);
		int newBalance = ud.getBalance() + amount;
		userRepo.updateBalance(accountNo, newBalance);
	}

	@Transactional
	public String withdraw(int amount, int accountNo, String password)
	{
		UserData ud = userRepo.findByAccountNo(accountNo);
		if(password.equals(ud.getPassword()))
		{
			UserData ac = userRepo.findByAccountNo(accountNo);
			if(ac.getBalance() >= amount)
			{
				int newBalance = ac.getBalance() - amount;
				userRepo.updateBalance(accountNo, newBalance);
				return "Amount Successfully Withdrawn ";
			} else {
				return "Insufficient Balance";
			}
		} else {
			return "Password Incorrect";
		}
	}

	public ResponseEntity<Integer> getBalance(int accountNo)
	{
		UserData ac = userRepo.findByAccountNo(accountNo);
		return new ResponseEntity<>(ac.getBalance(),HttpStatus.OK);
	}

	public ResponseEntity<UserData> getUserDataByAccountNo(int accountNo) 
	{
		UserData ud =  userRepo.findByAccountNo(accountNo);
		return new ResponseEntity<>(ud,HttpStatus.OK);
	}
}
