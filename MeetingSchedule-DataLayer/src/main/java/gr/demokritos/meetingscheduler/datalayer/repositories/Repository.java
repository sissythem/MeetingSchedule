package gr.demokritos.meetingscheduler.datalayer.repositories;

import java.util.List;
import java.util.Map;

public interface Repository<T> {

	T add(T item);

	Iterable<T> add(Iterable<T> items);

	T update(T item);

	void remove(T item);

	List<T> nativeQuery(String query);

	List<T> namedQuery(String queryName, Map<String, Object> parameters);

	List<T> jpqlQuery(String query);

	List<T> jpqlQueryWithParams(String query, Map<String, Object> parameters);

	Object getSingleResult(String queryName, Map<String, Object> parameters);

	void flushChanges();

}
