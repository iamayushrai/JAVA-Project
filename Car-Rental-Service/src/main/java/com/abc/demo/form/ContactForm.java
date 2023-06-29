package com.abc.demo.form;

import javax.validation.constraints.NotEmpty;

import com.abc.demo.dto.BaseDTO;
import com.abc.demo.dto.ContactDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ContactForm extends BaseForm {

	@NotEmpty(message = "Email is required")
	private String email;
	@NotEmpty(message = "Message is required")
	private String message;
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		ContactDTO bean = new ContactDTO();
		bean.setEmail(email);
		bean.setMessage(message);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		return bean;
	}
	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		ContactDTO bean = (ContactDTO)bDto;
		email = bean.getEmail();
		message = bean.getMessage();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
	}
}
