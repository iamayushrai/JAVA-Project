package com.abc.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.abc.demo.dto.UserDTO;
import com.abc.demo.dto.VehicleDTO;
import com.abc.demo.exception.DuplicateRecordException;

public interface UserService {

	UserDTO authenticate(String login,String password);
	
	UserDTO register(UserDTO dto) throws DuplicateRecordException;
	
	UserDTO update(UserDTO entity,long id);
	
	UserDTO findByPk(long id);

	Page<UserDTO> findByPage(int pageNo, int pageSize);

	List<UserDTO> getByKeyword(String keyword);

	void deleteUserById(long id);

	UserDTO getUserById(Long id);

	public boolean changePassword(long id, String oldPassword, String newPassword);
	
	
	
}
