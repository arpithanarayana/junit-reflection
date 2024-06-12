package com.te.jr.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.te.jr.dto.PostDTO;
import com.te.jr.dto.UserRegDTO;
import com.te.jr.entity.Post;
import com.te.jr.entity.User;
import com.te.jr.exception.UserIdNotFoundException;
import com.te.jr.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public Integer saveUser(UserRegDTO userRegDTO) {
		List<Post> posts = userRegDTO.getPostDTOs().stream()
				.map(e -> Post.builder().title(e.getTitle()).content(e.getContent()).build()).toList();
		User user = User.builder().name(userRegDTO.getName()).email(userRegDTO.getEmail()).posts(posts).build();
		userRepository.save(user);
		return user.getId();
	}

	@Override
	public UserRegDTO getUserById(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserIdNotFoundException("user id is not found"));
		UserRegDTO userRegDTO = UserRegDTO.builder().name(user.getName()).email(user.getEmail())
				.postDTOs(user.getPosts().stream()
						.map(e -> PostDTO.builder().title(e.getTitle()).content(e.getContent()).build()).toList())
				.build();
		return userRegDTO;
	}

	@Override
	public List<UserRegDTO> getAllUser() {
		List<UserRegDTO> list = userRepository.findAll().stream()
				.map(e -> UserRegDTO.builder().name(e.getName()).email(e.getEmail())
						.postDTOs(e.getPosts().stream()
								.map(f -> PostDTO.builder().title(f.getTitle()).content(f.getContent()).build())
								.collect(Collectors.toList()))
						.build())
				.collect(Collectors.toList());
		return list;
	}

	@Override
	public boolean deleteByUserId(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserIdNotFoundException("user id is not found"));
		if (user != null) {
			userRepository.delete(user);
			return true;
		}
		return false;
	}

}
