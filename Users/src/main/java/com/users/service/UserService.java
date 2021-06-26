package com.users.service;

import java.util.Date;
//import java.sql.Date;

import com.users.dto.UserDTO;
import com.users.exception.UserException;

public interface UserService {

	public String addUser(UserDTO usersDTO) throws UserException;
	public String updateUser(String id, long pincode, Date birthdate) throws UserException;
	public String DeleteUser(String id) throws UserException;
}
