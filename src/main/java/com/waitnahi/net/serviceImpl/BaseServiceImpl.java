package com.waitnahi.net.serviceImpl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;

import com.waitnahi.net.repository.GenericRepository;

/**
 * @author pramod
 * @since 15-Apr-2017
 */
public abstract class BaseServiceImpl<T> {

	private GenericRepository<T, Long> genericRepository;
	
	
	/**
	 * Gets the generic repository.
	 *
	 * @return the generic repository
	 */
	public GenericRepository<T, Long> getGenericRepository() {
		return genericRepository;
	}

	/**
	 * Sets the generic repository.
	 *
	 * @param genericRepository the generic repository
	 */
	public void setGenericRepository(GenericRepository<T, Long> genericRepository) {
		this.genericRepository = genericRepository;
	}

	/**
	 * Find one.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	public T findOne(final Long id) {
		notNull(id, " id to be fetched can't be null");
		return genericRepository.findOne(id);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<T> findAll() {
		return genericRepository.findAll();
	}

	/**
	 * Delete by id.
	 *
	 * @param entityId
	 *            the entity id
	 */
	@Modifying
	@Transactional
	public void deleteById(final Long entityId) {
		notNull(entityId, "id to be deleted can't be null");
		genericRepository.delete(entityId);
	}

	/**
	 * Delete.
	 *
	 * @param entity
	 *            the entity
	 */
	@Transactional
	public void delete(final T entity) {
		notNull(entity, " entity to be deleted can't be null");
		genericRepository.delete(entity);
	}

	/**
	 * Delete all.
	 */
	@Modifying
	@Transactional
	public void deleteAll() {
		genericRepository.deleteAll();
	}

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 */
	@Transactional
	public T save(final T entity) {
		notNull(entity, " can't to be saved cannot be null");
		return genericRepository.save(entity);
	}

	/**
	 * Save or update.
	 *
	 * @param entity
	 *            the entity
	 */
	@Transactional
	public T saveOrUpdate(final T entity) {
		notNull(entity, " entity to be saved can't be null");
		return genericRepository.save(entity);
	}

	/**
	 * Update.
	 *
	 * @param entity
	 *            the entity
	 */
	@Transactional
	public T update(final T entity) {
		notNull(entity, " entity to be updated can't be null");
		return genericRepository.save(entity);

	}

	@Transactional
	public List<T> saveAll(List<T> entities) {
		return genericRepository.save(entities);
	}

}
