package com.coelhocaique.user.resource;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coelhocaique.user.dto.UserDTO;
import com.coelhocaique.user.service.UserService;

@RequestMapping("/")
@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO userDTO){
		
		userDTO = userService.create(userDTO);
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> get(@PathVariable("id") String id){
		
		UserDTO userDTO = userService.find(id);
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.valueOf(userDTO.getCode()));
	}
	
	@GetMapping("/auth/{key}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> authenticate(@PathVariable("key") String key){
		
		UserDTO userDTO = userService.authenticate(key);
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.valueOf(userDTO.getCode()));
	}
	
	@DeleteMapping("/{key}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> delete(@PathVariable("key") String key){
		
		UserDTO userDTO = userService.delete(key);
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.valueOf(userDTO.getCode()));
	}
}
