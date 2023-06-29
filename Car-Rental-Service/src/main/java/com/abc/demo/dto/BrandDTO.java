package com.abc.demo.dto;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "c_brand")
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO extends BaseDTO {
	
	private String carBrand;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "brand")
	private Set<VehicleDTO> vehicle;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
