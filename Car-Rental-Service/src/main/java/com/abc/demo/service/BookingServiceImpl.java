package com.abc.demo.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abc.demo.dao.BookingRepository;
import com.abc.demo.dto.BookingDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;
import com.abc.demo.utility.DataUtility;
@Service
public class BookingServiceImpl implements BookingService {

	private static AtomicLong numberGenerator = new AtomicLong(810000000L);
	@Autowired
	private BookingRepository bookingRepo;
	@Override
	public BookingDTO register(BookingDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		BookingDTO bookingDTO = bookingRepo.findByBookingDate(dto.getBookingDate());
		if(bookingDTO!=null) {
			throw new DuplicateRecordException("OOPs This Car is Already Booked for this Date");
		}
		BookingDTO bookingDTO2 = bookingRepo.findByBookingNo(numberGenerator.getAndIncrement());
		if(bookingDTO2!=null) {
			dto.setBookingNo(numberGenerator.getAndIncrement()+1);
		}
		dto.setBookingNo(numberGenerator.getAndIncrement());
		
		return bookingRepo.save(dto);
	}

	@Override
	public BookingDTO update(BookingDTO entity, long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BookingDTO> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return bookingRepo.findAll(pageable);
	}

	@Override
	public List<BookingDTO> getAllBookingById(long id) {
		// TODO Auto-generated method stub
		return bookingRepo.getAllBookingById(id);
	}

	@Override
	public BookingDTO findByPk(long id) {
		// TODO Auto-generated method stub
		return bookingRepo.findById(id).get();
	}

	

	

}
