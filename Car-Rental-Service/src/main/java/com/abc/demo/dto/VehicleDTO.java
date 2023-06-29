package com.abc.demo.dto;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "c_vehicle")
public class VehicleDTO extends BaseDTO {
	
	
	private String vehicleName;
	
	private String vehicleDescription;
	
	private long price;
	
	private String modelName;
	
	private String seater;
	
	private byte[] vehicleImage;
	
	private long brandId;
	@ManyToOne
	@JoinColumn(name = "brand",nullable = false)
	private BrandDTO brand;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vehicle")
	private Set<BookingDTO> bookings;

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
