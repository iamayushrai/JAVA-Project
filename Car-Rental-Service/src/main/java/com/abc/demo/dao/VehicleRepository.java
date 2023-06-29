package com.abc.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abc.demo.dto.BrandDTO;
import com.abc.demo.dto.VehicleDTO;

public interface VehicleRepository extends JpaRepository<VehicleDTO, Long> {

	VehicleDTO findByModelName(String modelName);

	@Query(value = "select * from c_vehicle b where b.vehicle_name like %:keyword%",nativeQuery = true)
	List<VehicleDTO> findByKeyword(@Param("keyword") String keyword);

	List<VehicleDTO> findAllVehicleByBrandId(long id);

}
