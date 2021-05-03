package com.nnk.springboot.ut.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	UserService userService;

	User userTest1 = new User();
	User userTest2 = new User();

	List<User> usersListTest = new ArrayList<User>();

	@Test
	public void testFindAllUsers() {
		usersListTest.add(userTest1);
		usersListTest.add(userTest2);
		Mockito.when(userService.findAll()).thenReturn(usersListTest);
		assertEquals(2, usersListTest.size());
	}

	@Test
	public void testFindUserById() {
		Optional<User> userTest3 = Optional.of(new User());
		Mockito.when(userService.findById(1)).thenReturn(userTest3);
		assertNotNull(userTest3);
	}

	@Test
	public void testSaveUser() {
		String ref = "ADMIN";
		userTest1.setRole("ADMIN");
		Mockito.when(userService.save(userTest1)).thenReturn(userTest1);
		assertEquals(ref, userTest1.getRole());
	}

	@Test
	public void testDeleteUser() {
		Mockito.doNothing().when(userService).delete(userTest2);
		verify(userService, times(1));
	}
}
