package com.waitnahi.net.service;

import java.util.List;

import com.waitnahi.net.dto.DeveloperDto;
import com.waitnahi.net.model.Developer;

/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
public interface DeveloperService extends BaseService<Developer>{
	
	public List<DeveloperDto> getAllDeveloper();

}
