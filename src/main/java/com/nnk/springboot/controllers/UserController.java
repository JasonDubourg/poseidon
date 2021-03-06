package com.nnk.springboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import com.nnk.springboot.userDto.UserDto;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/user/list")
	public String home(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "user/list";
	}

	@GetMapping("/user/add")
	public String addUser(Model model) {
		UserDto user = new UserDto();
		model.addAttribute(user);
		return "user/add";
	}

	@PostMapping("/user/validate")
	public String validate(@Valid UserDto user, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			userService.saveUser(user);
			model.addAttribute("users", userService.findAll());
			return "redirect:/user/list";
		}
		return "user/add";
	}

	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		user.setPassword("");
		model.addAttribute("user", user);
		return "user/update";
	}

	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid UserDto user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/update";
		}
		userService.update(user);
		model.addAttribute("users", userService.findAll());
		return "redirect:/user/list";
	}

	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		userService.delete(user);
		model.addAttribute("users", userService.findAll());
		return "redirect:/user/list";
	}
}
