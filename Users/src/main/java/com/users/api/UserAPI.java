package com.users.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.dto.UserDTO;
import com.users.exception.UserException;
import com.users.service.UserService;

@RestController
@RequestMapping(value="/api")
@Validated
public class UserAPI {

	@Autowired
	private UserService userService;

	//Adding user data
	@PostMapping(value = "/user")
	public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO userDTO) throws UserException{
		String uid = userService.addUser(userDTO);
		String successMessage = "User added successfully : " + uid;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	//Update user pincode and birthdate
	@PutMapping(value = "/user/{id}")
	public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) throws UserException{
		String uid = userService.updateUser(id, userDTO.getPincode(), userDTO.getBirthDate());
		String successMessage = "User updated successfully : " + uid;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

	//Delete user - deactivating user
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) throws UserException{
		String uid = userService.DeleteUser(id);
		String successMessage = "User deactivated successfully : " + uid;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

}
