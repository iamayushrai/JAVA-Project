package com.abc.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abc.demo.dto.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

	UserDTO findByLogin(String login);

	UserDTO findByLoginAndPassword(String login, String password);

	@Query(value = "select * from c_user b where b.first_name like %:keyword%",nativeQuery = true)
	List<UserDTO> findByKeyword(String keyword);

}
