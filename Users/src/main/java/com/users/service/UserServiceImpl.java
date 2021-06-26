package com.users.service;

import java.util.Date;
//import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.dto.UserDTO;
import com.users.entity.User;
import com.users.exception.UserException;
import com.users.repository.UserRepository;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	//Adding user data
	@Override
	public String addUser(UserDTO userDTO) throws UserException {
		Optional<User> optional = userRepository.findById(userDTO.getId());
		if(!optional.isEmpty()) {
			throw new UserException("Service.USER_PRESENT", userDTO.getId());
		}
		User user = new User();
		user.setId(userDTO.getId());
		user.setfName(userDTO.getfName());
		user.setlName(userDTO.getlName());
		user.setEmail(userDTO.getEmail());
		user.setBirthDate(userDTO.getBirthDate());
		user.setPincode(userDTO.getPincode());
		user.setIsActive(userDTO.getIsActive());
		userRepository.save(user);
		return user.getId();
	}

	//Update user pincode and birthdate
	@Override
	public String updateUser(String id, long pincode, Date birthdate) throws UserException {
		if(birthdate.after(new Date())) {
			throw new UserException("Service.DATE_INVALID", id);
		}
		Optional<User> optional = userRepository.findById(id);
		User user = optional.orElseThrow(()-> new UserException("Service.NO_USER_DATA", id));
		user.setPincode(pincode);
		user.setBirthDate(birthdate);
		return user.getId();
	}

	//Delete user - deactivating user
	@Override
	public String DeleteUser(String id) throws UserException {
		Optional<User> optional = userRepository.findById(id);
		User user = optional.orElseThrow(()-> new UserException("Service.NO_USER_DATA", id));
		user.setIsActive(false);
		return user.getId();
	}

}
