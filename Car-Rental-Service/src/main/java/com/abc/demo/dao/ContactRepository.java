package com.abc.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.demo.dto.ContactDTO;
@Repository
public interface ContactRepository extends JpaRepository<ContactDTO, Long> {

}
