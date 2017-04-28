package com.waitnahi.net.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waitnahi.net.dto.DeveloperDto;
import com.waitnahi.net.service.DeveloperService;

/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
@RestController
@RequestMapping("/developer")
public class DeveloperRestController {

	@Autowired
	private DeveloperService developerService;

	/**
	 * @PreAuthorize has 'hasRole()'. The hasRole expression assumes a 'ROLE_'
	 *               prefix on all role names. So 'ADMIN' here is actually
	 *               stored as 'ROLE_ADMIN' in database!
	 **/
	@RequestMapping(value = "/developers", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasRole('ADMIN')")
	private List<DeveloperDto> getAllDeveloper() {
		return developerService.getAllDeveloper();
	}

}
