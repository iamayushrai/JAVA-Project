package com.abc.demo.form;

import java.sql.Time;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.abc.demo.dto.BaseDTO;
import com.abc.demo.dto.BookingDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BookingForm extends BaseForm {

	
	private long bookingNo;
	
	private long userId;
//	@Min(value = 1,message = "Select Vehicle Id is required")
	private long vehicleId;
	@JsonFormat(pattern = "DD/MM/YYYY")
	@NotEmpty(message = "Booking Date is required")
	private String bookingDate;
	@DateTimeFormat(pattern = "HH:MM")
	@NotEmpty(message = "Pick Up Time is required")
	private String bookingPickupTime;
	
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		BookingDTO bean = new BookingDTO();
		bean.setId(id);
		bean.setUserId(userId);
		bean.setBookingNo(bookingNo);
		bean.setVehicleId(vehicleId);
		bean.setBookingDate(bookingDate);
		bean.setBookingPickupTime(bookingPickupTime);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		BookingDTO bean = (BookingDTO)bDto;
		id = bean.getId();
		userId = bean.getUserId();
		bookingNo = bean.getBookingNo();
		vehicleId = bean.getVehicleId();
		bookingDate = bean.getBookingDate();
		bookingPickupTime = bean.getBookingPickupTime();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
	}

}
