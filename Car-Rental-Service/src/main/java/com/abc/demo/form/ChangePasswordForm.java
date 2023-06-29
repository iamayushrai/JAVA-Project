package com.abc.demo.form;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.abc.demo.dto.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordForm extends BaseForm  {
	

	@NotEmpty(message = "Old Password is required")
	private String oldPassword;
	
	@NotEmpty(message = "Confirm Password is required")
	private String confirmPassword;
	
	@NotEmpty(message = "New Passeword is required")
	private String newPassword;

	

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		
	}

	
}
