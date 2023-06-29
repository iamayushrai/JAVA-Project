package com.abc.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abc.demo.dto.BookingDTO;
import com.abc.demo.dto.VehicleDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;

public interface BookingService {

	BookingDTO register(BookingDTO dto) throws DuplicateRecordException;
	
	BookingDTO update(BookingDTO entity,long id) throws RecordNotFoundException;

	Page<BookingDTO> findByPage(int pageNo, int pageSize);
	

	List<BookingDTO> getAllBookingById(long id);
	
	  BookingDTO findByPk(long id);
	
}
