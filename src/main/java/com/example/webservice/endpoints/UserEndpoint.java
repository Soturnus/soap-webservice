package com.example.webservice.endpoints;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.webservice.entities.User;
import com.example.webservice.serviceImpl.UserService;
import com.soturno.webservice.users.AddUserRequest;
import com.soturno.webservice.users.AddUserResponse;
import com.soturno.webservice.users.DeleteUserRequest;
import com.soturno.webservice.users.DeleteUserResponse;
import com.soturno.webservice.users.GetUserByIdRequest;
import com.soturno.webservice.users.GetUserResponse;
import com.soturno.webservice.users.ServiceStatus;
import com.soturno.webservice.users.UpdateUserRequest;
import com.soturno.webservice.users.UpdateUserResponse;
import com.soturno.webservice.users.UserInfo;

@Endpoint
public class UserEndpoint {

	private static final String NAMESPACE_URI = "http://rivaldo.dev.br/user-spring-soap";
	private static final String SUCESS = "SUCCESS";
	
	@Autowired
	private UserService userService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
	@ResponsePayload
	public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
		AddUserResponse response = new AddUserResponse();
		ServiceStatus serviceStatus = new ServiceStatus();

		User user = new User();
		BeanUtils.copyProperties(request.getUserInfo(), user);
		userService.AddUser(user);
		serviceStatus.setStatus(SUCESS);
 	    serviceStatus.setMessage("Content Added Successfully");
		response.setServiceStatus(serviceStatus);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserByIdRequest request) {
		GetUserResponse response = new GetUserResponse();
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(userService.getUserById(request.getUserId()), userInfo);
		response.setUserInfo(userInfo);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
	@ResponsePayload
	public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
		User user = new User();
		BeanUtils.copyProperties(request.getUserInfo(), user);
		userService.updateUser(user);
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus(SUCESS);
		serviceStatus.setMessage("Content Updated Successfully");
		UpdateUserResponse response = new UpdateUserResponse();
		response.setServiceStatus(serviceStatus);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
	@ResponsePayload
	public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
		userService.deleteUser(request.getUserId());
		ServiceStatus serviceStatus = new ServiceStatus();

		serviceStatus.setStatus(SUCESS);
		serviceStatus.setMessage("Content Deleted Successfully");
		DeleteUserResponse response = new DeleteUserResponse();
		response.setServiceStatus(serviceStatus);
		return response;
	}
	
}
