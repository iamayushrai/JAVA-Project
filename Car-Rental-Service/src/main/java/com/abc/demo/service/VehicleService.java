package com.abc.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abc.demo.dto.VehicleDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;

public interface VehicleService {

	VehicleDTO register(VehicleDTO dto) throws DuplicateRecordException;
	
	VehicleDTO update(VehicleDTO entity,long id) throws RecordNotFoundException;
	
	VehicleDTO findByPk(long id);
	
	List<VehicleDTO> list();

	Page<VehicleDTO> findByPage(int pageNo, int pageSize);

	void deletecVehicleById(long id);

	VehicleDTO getBrandById(Long id);

	List<VehicleDTO> getByKeyword(String keyword);

	void deleteVehicleById(long id);

	VehicleDTO getVehicleById(Long id);

	List<VehicleDTO> showAllVehiclesByBrandId(long id);
}
