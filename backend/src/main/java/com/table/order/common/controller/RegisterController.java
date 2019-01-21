package com.table.order.common.controller;

import com.table.order.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.table.order.common.security.model.UserCredentials;

@RepositoryRestController
public class RegisterController {

	private UserService userService;

	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/users/signupClient")
	public ResponseEntity<?> saveClient(@RequestBody UserCredentials user) {
		userService.registerClient(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping(value = "/users/signupRestaurateur")
    public ResponseEntity<?> saveRestaurateur(@RequestBody UserCredentials user) {
        userService.registerRestaurateur(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
