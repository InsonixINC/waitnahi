package com.waitnahi.net.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waitnahi.net.dto.DeveloperDto;
import com.waitnahi.net.model.Developer;
import com.waitnahi.net.repository.DeveloperRepository;
import com.waitnahi.net.service.DeveloperService;

/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
@Transactional
@Service("developerService")
public class DeveloperServiceImpl extends BaseServiceImpl<Developer> implements DeveloperService {

	private DeveloperRepository developerRepository;

	@Autowired
	public void setDeveloperRepository(DeveloperRepository developerRepository) {
		this.developerRepository = developerRepository;
		setGenericRepository(developerRepository);
	}

	@Override
	public List<DeveloperDto> getAllDeveloper() {
		return developerRepository.findAllDeveloper();
	}

}
