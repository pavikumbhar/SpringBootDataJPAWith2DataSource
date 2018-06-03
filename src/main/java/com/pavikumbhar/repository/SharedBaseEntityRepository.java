package com.pavikumbhar.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author pavikumbhar
 *
 *  we added the @NoRepositoryBean annotation. This is necessary because otherwise,
 *  the default Spring behavior is to create an implementation for all subinterfaces of Repository
 */
@NoRepositoryBean
public interface SharedBaseEntityRepository<T,ID > extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	public T findAssocEntity(Map<String, Object> conditions, String... relationTofetch);
	 
	public T findByNameCustom(String name);
}