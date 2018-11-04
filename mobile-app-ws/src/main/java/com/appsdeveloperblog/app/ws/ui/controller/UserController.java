package com.appsdeveloperblog.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

@RestController 
@RequestMapping("users") 	//http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userservice;
	
	@GetMapping
	public String getUser()
	{
		return "Get The user";
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userdDetails)
	{
		UserRest returnValue = new UserRest();
		UserDto userdto = new UserDto();
		BeanUtils.copyProperties(userdDetails, userdto);
		
		UserDto createdUser = userservice.createUser(userdto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue; 
	}
	@PutMapping
	public String updateUser() 
	{
		return "Update user";
	}
	@DeleteMapping
	public String deleteUser()
	{
		return "Delete user";
	}
	
	
}

