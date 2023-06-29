package com.abc.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abc.demo.dao.UserRepository;
import com.abc.demo.dto.UserDTO;
import com.abc.demo.dto.VehicleDTO;
import com.abc.demo.exception.DuplicateRecordException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO authenticate(String login, String password) {
		UserDTO dto = userRepository.findByLoginAndPassword(login, password);
		return dto;
	}

	@Override
	public UserDTO register(UserDTO dto) throws DuplicateRecordException {
		UserDTO entity = userRepository.findByLogin(dto.getLogin());
		if (entity != null) {
			throw new DuplicateRecordException("Email is already registerd");
		}
		return userRepository.save(dto);
	}

	@Override
	public UserDTO update(UserDTO entity, long id) {
		// TODO Auto-generated method stub
		UserDTO entity1 = userRepository.findById(id).get();
		if (entity1.getId() != 0) {
			System.out.println("whether getting entity" + entity.getId() + entity.getLastName());
			entity1.setFirstName(entity.getFirstName());
			entity1.setLastName(entity.getLastName());
			entity1.setLogin(entity.getLogin());
			entity1.setGender(entity.getGender());
			entity1.setContactNo(entity.getContactNo());
			entity1.setDob(entity.getDob());
		}
		userRepository.save(entity1);
		return entity1;
	}

	@Override
	public UserDTO findByPk(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public Page<UserDTO> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return userRepository.findAll(pageable);
	}

	@Override
	public List<UserDTO> getByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return userRepository.findByKeyword(keyword);
	}

	@Override
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public UserDTO getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public boolean changePassword(long id, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		UserDTO dtoExist = findByPk(id);
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
			dtoExist.setPassword(newPassword);
			userRepository.save(dtoExist);
			return true;
		} else {
			return false;
		}
	}

}
