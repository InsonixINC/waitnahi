package com.waitnahi.net.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The Interface GenericRepository: interface for all dao classes
 *
 * @param <T> the generic entity type
 * @param <ID> the generic identifier type
 * @author pramod
 * @since 15-Apr-2017
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
