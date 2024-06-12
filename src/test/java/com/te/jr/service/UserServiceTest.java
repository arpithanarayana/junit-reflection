package com.te.jr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.te.jr.dto.PostDTO;
import com.te.jr.dto.UserDTO;
import com.te.jr.dto.UserRegDTO;
import com.te.jr.entity.Post;
import com.te.jr.entity.User;
import com.te.jr.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	private UserRegDTO userRegDTO;
	private PostDTO postDTO;
	private User user;
	private Post post;
	
	@BeforeEach
	public void setup() {
		postDTO = new PostDTO("moral", "moral for life");
		userRegDTO = new UserRegDTO("king", "king@gmil.com", List.of(postDTO));
	}
	
	@Test
	public void testUser() throws Exception{
		Method method = UserServiceImpl.class.getDeclaredMethod("saveUser", UserRegDTO.class);
		method.setAccessible(true);
		
		PostDTO testPost = new PostDTO("yoga", "yoga book is good");
		UserRegDTO testUser = new UserRegDTO("smith" , "smith@gmail.com", List.of(testPost));
		
		Integer userId = (Integer) method.invoke(userServiceImpl, testUser);
		assertNotNull(userId);
		
//		User savedUser = userRepository.findById(userId).orElse(null);
//		assertEquals("smith", savedUser.getName());
//		assertEquals("smith@gmail.com", savedUser.getEmail());
//		assertEquals("yoga", savedUser.getPosts().get(0).getTitle());
//		assertEquals("yoga book is good", savedUser.getPosts().get(0).getContent());
		System.out.println("test cases sucessfully passed");
	}
	
	@BeforeEach
	public void setup1() {
		post = new Post(2, "moral", "moral for life");
        user = new User(2, "king", "king@gmail.com", List.of(post));
        userRepository.save(user);
	}
	
	@Test
	@Rollback(true)
	public void testGetUserById() throws Exception{
		int userId = user.getId();
		Method method = UserServiceImpl.class.getDeclaredMethod("getUserById", Integer.class);
		method.setAccessible(true);
		
		UserRegDTO userRegDTO = (UserRegDTO) method.invoke(userServiceImpl, userId);
		assertNotNull(userRegDTO);
		assertNotNull(userRegDTO.getPostDTOs());
	}
	
	@Test
	public void getAllUser() throws Exception{
		Method method = UserServiceImpl.class.getDeclaredMethod("getAllUser");
		method.setAccessible(true);
		
		List<UserRegDTO> users = (List<UserRegDTO>)method.invoke(userServiceImpl);
		assertTrue(users.size()>0);
	}
	
	@BeforeEach
	void setup3() {
		user = new User();
		user.setId(2);
		userRepository.save(user);
	}
	
	@Test
	public void deleteUserById() throws Exception{
		int userId = user.getId();
		Method method = UserServiceImpl.class.getDeclaredMethod("deleteByUserId", Integer.class);
		method.setAccessible(true);
		
	    boolean result = (boolean) method.invoke(method, userId);
		assertFalse(userRepository.findById(userId).isPresent());
		assertTrue(result);
	}

}
