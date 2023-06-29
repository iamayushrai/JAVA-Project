package com.abc.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abc.demo.dto.BrandDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;

public interface BrandService {

	BrandDTO register(BrandDTO dto) throws DuplicateRecordException;
	
	BrandDTO update(BrandDTO entity,long id) throws RecordNotFoundException;
	
	BrandDTO findByPk(long id);
	
	List<BrandDTO> list();

	Page<BrandDTO> findByPage(int pageNo, int pageSize);

	void deletecBrandById(long id);

	BrandDTO getBrandById(Long id);

	List<BrandDTO> getByKeyword(String keyword);
}
