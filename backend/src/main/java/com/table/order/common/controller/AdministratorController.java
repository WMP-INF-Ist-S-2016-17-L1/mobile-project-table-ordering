package com.table.order.common.controller;

import com.table.order.common.repository.UserRepository;
import com.table.order.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/users")
public class AdministratorController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
