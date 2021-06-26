package com.users;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.users.dto.UserDTO;
import com.users.exception.UserException;
import com.users.service.UserServiceImpl;

@SpringBootTest
class UsersApplicationTests {

	@Autowired
	UserServiceImpl userServiceImpl;

	//Add user successful test
	@Test
	public void addUserTest() throws UserException{
		UserDTO user = new UserDTO();
		user.setId("651hb");
		user.setfName("XYZ");
		user.setlName("ABC");
		user.setEmail("abc@gmail.com");
		user.setBirthDate(new Date("12-MAY-2003"));
		user.setPincode(621546);
		user.setIsActive(true);
		String id = userServiceImpl.addUser(user);
		Assertions.assertEquals("651hb", id);
	}

	//Update user invalid birthdate test
	@Test
	public void updateUserInvalidTest() throws UserException{
		UserException Exception = Assertions.assertThrows( UserException.class,
				()->userServiceImpl.updateUser("651hb", 621123, new Date("12-MAY-2030")));
		Assertions.assertEquals("Service.DATE_INVALID", Exception.getMessage());
	}
}
