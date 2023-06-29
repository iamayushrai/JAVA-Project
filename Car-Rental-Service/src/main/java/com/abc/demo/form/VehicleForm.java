package com.abc.demo.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.abc.demo.dto.BaseDTO;
import com.abc.demo.dto.VehicleDTO;
import com.abc.demo.utility.DataUtility;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class VehicleForm extends BaseForm {

	@NotEmpty(message = "Vehicle Name is required")
	private String vehicleName;
	@NotEmpty(message = "Description is required")
	private String vehicleDescription;
	@NotEmpty(message = "Price is required")
	private String price;
	@NotEmpty(message = "Model Name is required")
	private String modelName;
	@NotEmpty(message = "Select Seater")
	private String seater;
	//@NotEmpty(message = "Image is required")
	private MultipartFile vehicleImage;
	@Min(value = 1,message = "Select Brand Id")
	private long brandId;
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		VehicleDTO bean = new VehicleDTO();
		bean.setId(id);
		bean.setVehicleName(vehicleName);
		bean.setVehicleDescription(vehicleDescription);
		bean.setPrice(DataUtility.getLong(price));
		bean.setModelName(modelName);
		bean.setSeater(seater);
		//bean.setVehicleImage(vehicleImage);
		bean.setBrandId(brandId);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		VehicleDTO bean = (VehicleDTO)bDto;
		id = bean.getId();
		vehicleName = bean.getVehicleName();
		vehicleDescription = bean.getVehicleDescription();
		price = String.valueOf(bean.getPrice());
		modelName = bean.getModelName();
		seater = bean.getSeater();
		//vehicleImage = bean.getVehicleImage();
		brandId = bean.getBrandId();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
	}

}
