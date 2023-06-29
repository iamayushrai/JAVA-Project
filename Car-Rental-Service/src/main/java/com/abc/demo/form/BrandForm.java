package com.abc.demo.form;

import javax.validation.constraints.NotEmpty;

import com.abc.demo.dto.BaseDTO;
import com.abc.demo.dto.BrandDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BrandForm extends BaseForm{

	@NotEmpty(message = "Brand Name is required")
	private String carBrand;
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		BrandDTO bean = new BrandDTO();
		bean.setId(id);
		bean.setCarBrand(carBrand);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		BrandDTO bean = (BrandDTO)bDto;
		id = bean.getId();
		carBrand = bean.getCarBrand();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
	}

}
