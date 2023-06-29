package com.abc.demo.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abc.demo.dto.BookingDTO;
@Repository
public interface BookingRepository extends JpaRepository<BookingDTO, Long> {

	BookingDTO findByBookingDate(String string);
	@Query("select b from BookingDTO b where b.userId=?1")
	List<BookingDTO> getAllBookingById(long id);
	BookingDTO findByBookingNo(long andIncrement);
	
}
