package com.waitnahi.net.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.waitnahi.net.dto.DeveloperDto;
import com.waitnahi.net.model.Developer;

/**
 * @author Pramod Maurya
 * @since : Apr 18, 2017
 */
@Repository("developerRepository")
public interface DeveloperRepository extends GenericRepository<Developer, Long>{
	
	@Query("SELECT NEW com.waitnahi.net.dto.DeveloperDto(dev.id, dev.fullName, dev.profile) FROM Developer dev")
	public List<DeveloperDto> findAllDeveloper();

}
