package com.users.dto;


import java.util.Date;
//import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDTO {

	@NotNull(message="{user.id.absent}")
	private String id;

	@NotNull(message="{user.fName.absent}")
	private String fName;

	@NotNull(message="{user.lName.absent}")
	private String lName;

	@NotNull(message="{user.email.absent}")
	private String email;

	@NotNull(message="{user.pincode.absent}")
	@Range(min = 1, message= "{user.pincode.absent}")
	private long pincode;

	@NotNull(message="{user.birthDate.absent}")
	@JsonFormat(pattern="dd-MMM-yyyy")
	@Past(message="DOB Shoulne be future date")
	private Date birthDate;

	@NotNull(message="{user.isActive.absent}")
	private Boolean isActive;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public UserDTO() {
		super();
	}
	public UserDTO(String id, String fName, String lName, String email, int pincode, Date birthDate,
			Boolean isActive) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.pincode = pincode;
		this.birthDate = birthDate;
		this.isActive = isActive;
	}


}
