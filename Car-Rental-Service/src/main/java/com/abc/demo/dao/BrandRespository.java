package com.abc.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abc.demo.dto.BrandDTO;
@Repository
public interface BrandRespository extends JpaRepository<BrandDTO, Long> {

	BrandDTO findByCarBrand(String carBrand);

	@Query(value = "select * from c_brand b where b.car_brand like %:keyword%",nativeQuery = true)
	List<BrandDTO> findByKeyword(@Param("keyword") String keyword);

}
