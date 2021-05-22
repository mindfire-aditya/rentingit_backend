package com.mindfire.rentingit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ujjwalk
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentingIt/resources")
public class HomeController {

	/**
	 * @return all content
	 */
	@GetMapping("/all")

	public String allAccess() {
		return "HELLLO WORLD THIS IA A PUBLIC CONTENT";
	}

	/**
	 * @return content based upon roles USER can access the content
	 */
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	/**
	 * @return content based upon roles ADMIN can access the content
	 */
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}