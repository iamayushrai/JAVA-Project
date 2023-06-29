package com.abc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.dao.ContactRepository;
import com.abc.demo.dto.ContactDTO;
@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;
	@Override
	public void register(ContactDTO contactDTO) {
		// TODO Auto-generated method stub
		contactRepo.save(contactDTO);
	}

}
