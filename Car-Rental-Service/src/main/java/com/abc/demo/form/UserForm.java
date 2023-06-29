package com.abc.demo.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.abc.demo.dto.BaseDTO;
import com.abc.demo.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	@NotEmpty(message = "Last Name is required")
	private String lastName;
	@DateTimeFormat(pattern = "MM/DD/YYYY")
	@NotEmpty(message = "DOB is required")
	private String dob;
	@NotEmpty(message = "Email is required")
	@Email(message = "Incorrect Email address")
	private String login;
	@NotEmpty(message = "Password is required")
	private String password;
	@NotEmpty(message = "Contact No. is required")
	@Pattern(regexp="(^[7-9][0-9]{9})*$",message = "Mobile number is Invalid")
	private String contactNo;
	@NotEmpty(message = "Gender is required")
	private String gender;
	//@NotEmpty(message = "Role Name is required")
	private String roleName;
	//@NotEmpty(message = "Roleid is required")
	private String roleId;

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setDob(dob);
		bean.setLogin(login);
		bean.setPassword(password);
		bean.setContactNo(contactNo);
		bean.setGender(gender);
		bean.setRoleName(roleName);
		bean.setRoleId(roleId);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean = (UserDTO)bDto;
		id = bean.getId();
		firstName = bean.getFirstName();
		lastName = bean.getLastName();
		dob = bean.getDob();
		login = bean.getLogin();
		password = bean.getPassword();
		contactNo = bean.getContactNo();
		gender = bean.getGender();
		roleName = bean.getRoleName();
		roleId = bean.getRoleId();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
	}

}
