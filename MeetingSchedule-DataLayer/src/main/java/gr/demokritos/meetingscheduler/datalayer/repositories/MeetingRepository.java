package gr.demokritos.meetingscheduler.datalayer.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Meeting;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@JpaRepo
@Dependent
public class MeetingRepository extends AbstractRepository<Meeting> {

	public MeetingRepository() {
		super(DbConstants.MEETING_TABLE);
	}
	
	public List<Meeting> findAllMeetings() {
		return namedQuery(DbConstants.MEETING_FIND_ALL, null);
	}

	public Meeting findMeetingById(Long id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<Meeting> meetings = namedQuery(DbConstants.MEETING_FIND_BY_ID, parameters);
		if (!CollectionUtils.isEmpty(meetings)) {
			return meetings.get(0);
		}
		return null;
	}
	
	public List<Meeting> findMeetingByName(String name) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", name);
		return namedQuery(DbConstants.MEETING_FIND_BY_NAME, parameters);
	}
	
	public List<Meeting> findMeetingByDate(LocalDate date) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("date", date);
		return namedQuery(DbConstants.MEETING_FIND_BY_DATE, parameters);
	}
	
	public List<Meeting> findMeetingByStartTime(LocalTime startTime) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("startTime", startTime);
		return namedQuery(DbConstants.MEETING_FIND_BY_START_TIME, parameters);
	}
	
	public List<Meeting> findMeetingByEndTime(LocalTime endTime) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("endTime", endTime);
		return namedQuery(DbConstants.MEETING_FIND_BY_END_TIME, parameters);
	}
	
	public List<Meeting> findMeetingByEndTime(String completed) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("completed", completed);
		return namedQuery(DbConstants.MEETING_FIND_BY_COMPLETED, parameters);
	}

}
