package com.waitnahi.net.service;

import java.util.List;

/**
 * The Interface BaseService.
 *
 * @author pramod
 * @param <T>
 *            the generic type
 * @since 15-Apr-2017
 */
public interface BaseService<T> {

	/**
	 * Find one.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	public T findOne(final Long id);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<T> findAll();

	/**
	 * Delete by id.
	 *
	 * @param id
	 *            the id
	 */
	public void deleteById(final Long id);

	/**
	 * Delete.
	 *
	 * @param entity
	 *            the entity
	 */
	public void delete(final T entity);

	/**
	 * Delete all.
	 */
	void deleteAll();

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 * @return the t
	 */
	public T save(final T entity);

	/**
	 * Save or update.
	 *
	 * @param entity
	 *            the entity
	 * @return the t
	 */
	public T saveOrUpdate(final T entity);

	/**
	 * Update.
	 *
	 * @param entity
	 *            the entity
	 * @return the t
	 */
	public T update(final T entity);

	/**
	 * Save all multiple entities
	 *
	 * @param entities
	 *            the entities
	 * @return the list
	 */
	List<T> saveAll(List<T> entities);
}
