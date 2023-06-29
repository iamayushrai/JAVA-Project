package com.abc.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abc.demo.dao.BrandRespository;
import com.abc.demo.dto.BrandDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandRespository brandRepository;
	
	@Override
	public BrandDTO register(BrandDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		BrandDTO brandDTO = brandRepository.findByCarBrand(dto.getCarBrand());
		if(brandDTO!=null) {
			throw new DuplicateRecordException(brandDTO.getCarBrand()+" Already exist");
		}
		return brandRepository.save(dto);
	}

	@Override
	public BrandDTO update(BrandDTO entity, long id) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		BrandDTO b = brandRepository.findById(id).get();
	    if(b.getId()!=0) {
	     b.setCarBrand(entity.getCarBrand());
	    }
	    else
	    {
	      throw new RecordNotFoundException("Not found");
	    }
	    brandRepository.save(b);
	    return b;
	}

	@Override
	public BrandDTO findByPk(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandDTO> list() {
		// TODO Auto-generated method stub
		return brandRepository.findAll();
	}

	@Override
	public Page<BrandDTO> findByPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return brandRepository.findAll(pageable);
	}

	@Override
	public void deletecBrandById(long id) {
		// TODO Auto-generated method stub
		brandRepository.deleteById(id);
	}

	@Override
	public BrandDTO getBrandById(Long id) {
		// TODO Auto-generated method stub
		return brandRepository.findById(id).get();
	}

	@Override
	public List<BrandDTO> getByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return brandRepository.findByKeyword(keyword);
	}

}
