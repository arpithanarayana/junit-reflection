package com.te.jr.service;

import java.util.List;

import com.te.jr.dto.UserRegDTO;

public interface UserService {

	Integer saveUser(UserRegDTO userRegDTO);

	UserRegDTO getUserById(Integer id);

	List<UserRegDTO> getAllUser();

	boolean deleteByUserId(Integer id);

}
