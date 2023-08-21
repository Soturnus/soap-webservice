package com.example.webservice.service;

import com.example.webservice.entities.User;

public interface IUserService {

	void AddUser(User user);
	
	User getUserById(long user);
	
	void updateUser(User user);
	
	void deleteUser(long userId);
}
