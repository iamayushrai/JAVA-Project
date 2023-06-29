package com.abc.demo.dto;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "c_book")
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO extends BaseDTO {
	
	private long bookingNo;
	
	private long userId;
	
	private long vehicleId;
	
	
	private String bookingDate;
	
	
	private String bookingPickupTime;
	
	
	@ManyToOne
	@JoinColumn(name="vehicle",nullable = false)
	private VehicleDTO vehicle;
	
	
	@ManyToOne
	@JoinColumn(name="user",nullable = false)
	private UserDTO user;

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
