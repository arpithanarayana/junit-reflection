package com.te.jr.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.te.jr.dto.PostDTO;
import com.te.jr.dto.UserRegDTO;
import com.te.jr.response.SuccessResponse;
import com.te.jr.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {
	private final UserService userService;
	
	@GetMapping(path = "/dummy")
	public UserRegDTO dummy() {
		return UserRegDTO.builder().name("abc").email("abc@gmail.com")
				.postDTOs(List.of(PostDTO.builder().title("abc").content("abcd").build())).build();
	}
	
	@PostMapping(path = "/saveUser")
	public ResponseEntity<SuccessResponse> saveUser(@RequestBody UserRegDTO userRegDTO){
		return ResponseEntity.ofNullable(SuccessResponse.builder()
				.message("user details saved sucessfully")
				.data(userService.saveUser(userRegDTO)).status(HttpStatus.CREATED)
				.timestamp(LocalDateTime.now()).build());
	}
	
	@GetMapping(path = "/getUserById/{id}")
	public ResponseEntity<SuccessResponse> getUserById(@PathVariable Integer id){
		return ResponseEntity.ofNullable(SuccessResponse.builder()
				.message("user details feteched sucessfully")
				.data(userService.getUserById(id))
				.status(HttpStatus.ACCEPTED)
				.timestamp(LocalDateTime.now())
				.build());
	}
	
	@GetMapping(path = "/getAllUser")
	public ResponseEntity<SuccessResponse> getAllUser(){
		return ResponseEntity.ofNullable(SuccessResponse.builder()
				.message("user details feteched sucessfully")
				.data(userService.getAllUser())
				.status(HttpStatus.ACCEPTED)
				.timestamp(LocalDateTime.now())
				.build());
	}
	
	@DeleteMapping(path = "/deleteByUserId/{id}")
	public ResponseEntity<SuccessResponse> deleteByUserId(@PathVariable Integer id){
		return ResponseEntity.ofNullable(SuccessResponse.builder()
				.message("user details delete sucessfully")
				.data(userService.deleteByUserId(id))
				.status(HttpStatus.ACCEPTED)
				.timestamp(LocalDateTime.now())
				.build());
	}
	
	

}
