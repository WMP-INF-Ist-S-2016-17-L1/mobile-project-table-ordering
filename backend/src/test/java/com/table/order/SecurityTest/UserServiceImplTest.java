package com.table.order.SecurityTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.table.order.common.repository.UserRepository;
import com.table.order.common.security.exception.UnauthorizedException;
import com.table.order.common.security.model.Role;
import com.table.order.common.security.model.User;
import com.table.order.common.security.model.UserCredentials;
import com.table.order.common.service.RoleService;
import com.table.order.common.service.UserService;
import com.table.order.common.service.helper.UserHelper;

public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;

	@Mock
	UserHelper userHelper;

	@Mock
	BCryptPasswordEncoder bcryptEncoder;

	@Mock
	RoleService roleService;

	@InjectMocks
	UserService userService;

	User user;
	UserCredentials userCredentials;
	Role role;
	String password;
	User user1;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		role = new Role(3L, "ROLE_USER", "User Role");
		user = new User(1L, "name", "password", role);
		userCredentials = new UserCredentials("name", "pd");
		user1 = new User();
	}

//	@Test
//	public void updateRestaurantIdForLoggedUser_IncorrectAuthorities_shouldThrowException() {
//		String restaurantApiId = "venueId";
//
//		when(userHelper.isLoggedRestaurateur()).thenReturn(false);
//
//		assertThrows(UnauthorizedException.class, () -> userService.assignRestaurantToLoggedUser(restaurantApiId));
//	}

//	@Test
//	public void updateRestaurantIdForLoggedUser_CorrectAuthorities_shouldTellDbToUpdateUser()
//			throws UnauthorizedException {
//		String restaurantApiId = "venueId";
//		String dumbo = "dumbo";
//
//		when(userHelper.getLoggedUserUsername()).thenReturn(dumbo);
//		when(userHelper.isLoggedRestaurateur()).thenReturn(true);
//
//		userService.assignRestaurantToLoggedUser(restaurantApiId);
//
//		verify(userRepository).updateRestarantIdByUsername(dumbo, restaurantApiId);
//	}

	@Test
	public void getAuthority_Test() {
		assertAll(() -> assertNotNull(userService.getAuthority(user)),
				() -> assertEquals(user.getRoles().getName(), "ROLE_USER"));
	}

	@Test
	public void getAuthorityFailUserWithoutRole_Test() {
		assertThrows(NullPointerException.class, () -> {
			userService.getAuthority(user1);
		});
	}

	@Test
	public void loadUser_Test() {
		when(userRepository.findByUsername(anyString())).thenReturn(user);

		assertNotNull(userService.loadUserByUsername("exampleUsername"));
	}

	@Test
	public void loadUserFail_Test() {
		when(userRepository.findByUsername(anyString())).thenReturn(user1);

		assertThrows(NullPointerException.class, () -> {
			userService.loadUserByUsername("exampleUsername");
		});
	}
}
