package gr.demokritos.meetingscheduler.datalayer.repositories;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Day;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@JpaRepo
@Dependent
public class DayRepository extends AbstractRepository<Day> {

	public DayRepository() {
		super(DbConstants.DAY_TABLE);
	}
	
	public List<Day> findAllDays() {
		return namedQuery(DbConstants.DAY_FIND_ALL, null);
	}

	public Day findDayById(Long id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<Day> days = namedQuery(DbConstants.DAY_FIND_BY_ID, parameters);
		if (!CollectionUtils.isEmpty(days)) {
			return days.get(0);
		}
		return null;
	}
	
	public List<Day> findDayByName(String name) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", name);
		return namedQuery(DbConstants.DAY_FIND_BY_NAME, parameters);
	}
	
	public Day findDayByDate(LocalDate date) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("date", date);
		List<Day> days = namedQuery(DbConstants.DAY_FIND_BY_DATE, parameters);
		if (!CollectionUtils.isEmpty(days)) {
			return days.get(0);
		}
		return null;
	}
	
	public Day findDayByNameAndDate(String name, LocalDate date) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", name);
		parameters.put("date", date);
		List<Day> days = namedQuery(DbConstants.DAY_FIND_BY_NAME_AND_DATE, parameters);
		if (!CollectionUtils.isEmpty(days)) {
			return days.get(0);
		}
		return null;
	}
	
}
