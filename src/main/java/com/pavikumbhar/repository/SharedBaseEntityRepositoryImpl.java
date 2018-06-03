package com.pavikumbhar.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
/**
 * 
 * @author pavikumbhar
 *
 * @param <T>
 * @param <ID>
 */
public class SharedBaseEntityRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>implements SharedBaseEntityRepository<T, ID> {

	private EntityManager entityManager;

	public SharedBaseEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;

	}

	public T findByNameCustom(String name) {
		List<T> resultList = new ArrayList<T>();
		TypedQuery<T> query = entityManager.createQuery("FROM " + getDomainClass().getName() + " where name=:name",
				getDomainClass());
		resultList = query.setParameter("name", name).getResultList();
		return resultList.stream().findFirst().orElse(null);
	}

	/**
	 * 
	 * @param conditions
	 * @param relationTofetch
	 * @return
	 */
	public T findAssocEntity(Map<String, Object> conditions, String... relationTofetch) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(getDomainClass());
		Root<T> root = query.from(getDomainClass());
		for (String value : relationTofetch) {
			root.fetch(value, JoinType.INNER);
		}
		for (Map.Entry<String, Object> condition : conditions.entrySet()) {
			Predicate predicateJoin = builder.equal(root.get(condition.getKey()), condition.getValue());
			predicates.add(predicateJoin);

		}
		query.select(root).where(builder.and(predicates.toArray(new Predicate[] {})));
		TypedQuery<T> q = entityManager.createQuery(query);
		return q.getResultList().stream().findFirst().orElse(null);
	}

}
