package gr.demokritos.meetingscheduler.datalayer.repositories;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gr.demokritos.meetingscheduler.datalayer.utils.Logger;
import gr.demokritos.meetingscheduler.datalayer.utils.LoggerFactory;

public abstract class AbstractRepository<T> implements Repository<T> {
	
	@PersistenceContext(unitName = "MeetingSchedule")
	private EntityManager entityManager;

	private String tableName;
	
	Logger logger = LoggerFactory.getLogger(AbstractRepository.class);

	public AbstractRepository(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public T add(T item) {
		if (item != null) {
			entityManager.persist(item);
			flushChanges();
			return item;
		}
		return null;
	}

	@Override
	public Iterable<T> add(Iterable<T> items) {
		List<T> persistedItems = new ArrayList<>();
		for (T item : items) {
			if (item != null)
				persistedItems.add(add(item));
		}
		return persistedItems;

	}

	@Override
	public T update(T item) {
		if (item != null) {
			entityManager.merge(item);
			flushChanges();
			return item;
		}
		return null;
	}

	@Override
	public void remove(T item) {
		if (item != null) {
			item = entityManager.merge(item);
			entityManager.remove(item);
			flushChanges();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> nativeQuery(String query) {
		Query nativeQuery;
		if (query != null) {
			nativeQuery = entityManager.createNativeQuery(query, getType());
		} else {
			nativeQuery = entityManager.createNativeQuery("select * from " + tableName, getType());
		}

		return nativeQuery.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> jpqlQuery(String query) {
		Query jpqlQuery;
		if (query != null) {
			jpqlQuery = entityManager.createQuery(query);
		} else {
			jpqlQuery = entityManager.createNativeQuery("select * from " + tableName, getType());
		}
		return jpqlQuery.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> jpqlQueryWithParams(String query, Map<String, Object> parameters) {
		Query jpqlQuery;
		if (query != null) {
			jpqlQuery = entityManager.createQuery(query);
			for(Entry<String, Object> parameter : parameters.entrySet()) {
				jpqlQuery.setParameter(parameter.getKey(), parameter.getValue());
			}
		} else {
			jpqlQuery = entityManager.createNativeQuery("select * from " + tableName, getType());
		}
		return jpqlQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> namedQuery(String queryName, Map<String, Object> parameters) {
		if (queryName != null) {
			if (parameters == null)
				return entityManager.createNamedQuery(queryName).getResultList();
			else {
				Query query = entityManager.createNamedQuery(queryName);
				for (Entry<String, Object> parameter : parameters.entrySet()) {
					query.setParameter(parameter.getKey(), parameter.getValue());
				}
				return query.getResultList();
			}
		}

		return null;
	}

	@Override
	public Object getSingleResult(String queryName, Map<String, Object> parameters) {
		if (queryName != null) {
			if (parameters == null)
				return entityManager.createNamedQuery(queryName).getSingleResult();
			else {
				Query query = entityManager.createNamedQuery(queryName);
				for (Entry<String, Object> parameter : parameters.entrySet()) {
					query.setParameter(parameter.getKey(), parameter.getValue());
				}
				return query.getSingleResult();
			}
		}
		return null;
	}
	
	@Override
	public void flushChanges() {
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	private Class<T> getType() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<T>) type.getActualTypeArguments()[0];
	}

}
