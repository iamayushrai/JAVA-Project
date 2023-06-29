package com.abc.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abc.demo.dao.VehicleRepository;
import com.abc.demo.dto.BrandDTO;
import com.abc.demo.dto.VehicleDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;
@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	@Override
	public VehicleDTO register(VehicleDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		VehicleDTO vehicleDTO = vehicleRepository.findByModelName(dto.getModelName());
		if(vehicleDTO!=null) {
			throw new DuplicateRecordException(dto.getModelName()+" Already exist");
		}
		return vehicleRepository.save(dto);
	}

	@Override
	public VehicleDTO update(VehicleDTO entity, long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		VehicleDTO b = vehicleRepository.findById(id).get();
	    if(b.getId()!=0) {
	     b.setVehicleName(entity.getVehicleName());
	     b.setVehicleDescription(entity.getVehicleDescription());
	     b.setVehicleImage(entity.getVehicleImage());
	     b.setModelName(entity.getModelName());
	     b.setPrice(entity.getPrice());
	     b.setSeater(entity.getSeater());
	     b.setBrand(entity.getBrand());
	     b.setVehicleImage(entity.getVehicleImage());
	    }
	    else
	    {
	      throw new RecordNotFoundException("Not found");
	    }
	    vehicleRepository.save(b);
	    return b;
	}

	@Override
	public VehicleDTO findByPk(long id) {
		// TODO Auto-generated method stub
		return vehicleRepository.findById(id).get();
	}

	@Override
	public List<VehicleDTO> list() {
		// TODO Auto-generated method stub
		return vehicleRepository.findAll();
	}

	@Override
	public Page<VehicleDTO> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable =PageRequest.of(pageNo-1, pageSize);
		return vehicleRepository.findAll(pageable);
	}


	@Override
	public void deleteVehicleById(long id) {
		// TODO Auto-generated method stub
		vehicleRepository.deleteById(id);
	}

	@Override
	public VehicleDTO getBrandById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleDTO> getByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return vehicleRepository.findByKeyword(keyword);
	}

	@Override
	public void deletecVehicleById(long id) {
		// TODO Auto-generated method stub
		vehicleRepository.deleteById(id);
	}

	@Override
	public VehicleDTO getVehicleById(Long id) {
		// TODO Auto-generated method stub
		return vehicleRepository.findById(id).get();
	}

	@Override
	public List<VehicleDTO> showAllVehiclesByBrandId(long id) {
		// TODO Auto-generated method stub
		return vehicleRepository.findAllVehicleByBrandId(id);
	}

}
