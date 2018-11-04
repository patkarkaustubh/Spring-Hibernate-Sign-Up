package com.appsdeveloperblog.app.ws.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.UserRepository;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;	
	
	public UserDto createUser(UserDto user)
	{
		
		
		if(userRepository.findUserByEmail(user.getEmail())!=null)throw new RuntimeException("Records Exists");
	
		
		String publicuserID= utils.generateUserId(30); 
		UserEntity userEntity = new UserEntity();	
		BeanUtils.copyProperties(user,userEntity);
		
		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicuserID);
		
		UserEntity 	storedUserDetails = userRepository.save(userEntity);
		UserDto returnvalue = new UserDto();
		
		
		
		BeanUtils.copyProperties(storedUserDetails, returnvalue);
		return returnvalue;
		
	}

}
