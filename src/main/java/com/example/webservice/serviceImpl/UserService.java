package com.example.webservice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webservice.entities.User;
import com.example.webservice.repositories.UserRepository;
import com.example.webservice.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void AddUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User getUserById(long userId) {			
		return userRepository.findByUserId(userId);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	} 
	
	//TODO: melhorar implementação de alguns metodos, adicionar validações
	
}
