package com.abc.demo.form;

import javax.validation.constraints.NotEmpty;

import com.abc.demo.dto.BaseDTO;
import com.abc.demo.dto.UserDTO;
import com.abc.demo.form.BaseForm;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginForm extends BaseForm {

	@NotEmpty(message = "Login is required")
	private String login;
	@NotEmpty(message = "Password is required")
	private String password;

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setLogin(login);
		bean.setPassword(password);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean = (UserDTO) bDto;
		id = bean.getId();
		login = bean.getLogin();
		password = bean.getPassword();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
	}

	

}
