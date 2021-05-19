package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.userDto.UserDto;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public User update(UserDto userDto) {
		User user = new User();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setFullname(userDto.getFullname());
		user.setUsername(userDto.getUsername());
		user.setRole(userDto.getRole());
		user.setPassword(encoder.encode(userDto.getPassword()));
		user.setId(userDto.getId());
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public void saveUser(@Valid UserDto userDto) {
		User newUser = new User();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		newUser.setPassword(encoder.encode(userDto.getPassword()));
		newUser.setFullname(userDto.getFullname());
		newUser.setUsername(userDto.getUsername());
		newUser.setRole(userDto.getRole());
		userRepository.save(newUser);
	}

}
