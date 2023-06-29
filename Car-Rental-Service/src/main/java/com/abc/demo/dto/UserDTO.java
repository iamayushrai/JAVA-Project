package com.abc.demo.dto;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "c_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO{

	
	private String firstName;
	
	private String lastName;
	
	
	private String dob;
	
	private String login;
	
	private String password;
	
	private String contactNo;
	
	private String gender;
	
	private String roleName;
	
	private String roleId;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private Set<BookingDTO> bookings;
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id +"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return firstName + lastName;
	}

}
