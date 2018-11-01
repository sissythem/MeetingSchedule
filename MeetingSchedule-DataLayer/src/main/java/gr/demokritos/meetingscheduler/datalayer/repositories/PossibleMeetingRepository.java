package gr.demokritos.meetingscheduler.datalayer.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeeting;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@JpaRepo
@Dependent
public class PossibleMeetingRepository extends AbstractRepository<PossibleMeeting> {

	public PossibleMeetingRepository() {
		super(DbConstants.POSSIBLE_MEETING_TABLE);
	}

	public List<PossibleMeeting> findAllPossibleMeetings() {
		return namedQuery(DbConstants.POSSIBLE_MEETING_FIND_ALL, null);
	}

	public PossibleMeeting findPossibleMeetingById(Long id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<PossibleMeeting> meetings = namedQuery(DbConstants.POSSIBLE_MEETING_FIND_BY_ID, parameters);
		if (!CollectionUtils.isEmpty(meetings)) {
			return meetings.get(0);
		}
		return null;
	}

	public List<PossibleMeeting> findPossibleMeetingByMeeting(Long meetingId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("meetingId", meetingId);
		return namedQuery(DbConstants.POSSIBLE_MEETING_FIND_BY_MEETING, parameters);
	}

	public List<PossibleMeeting> findPossibleMeetingByDay(Long dayId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("dayId", dayId);
		return namedQuery(DbConstants.POSSIBLE_MEETING_FIND_BY_DAY, parameters);
	}

	public List<PossibleMeeting> findPossibleMeetingByTimezone(Long timezoneId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("timezoneId", timezoneId);
		return namedQuery(DbConstants.POSSIBLE_MEETING_FIND_BY_TIMEZONE, parameters);
	}

	public List<PossibleMeeting> findPossibleMeetingByMeetingAndDay(Long meetingId, Long dayId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("meetingId", meetingId);
		parameters.put("dayId", dayId);
		return namedQuery(DbConstants.POSSIBLE_MEETING_FIND_BY_MEETING_AND_DAY, parameters);
	}

	public List<PossibleMeeting> findPossibleMeetingByDayAndTimezone(Long dayId, Long timezoneId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("dayId", dayId);
		parameters.put("timezoneId", timezoneId);
		return namedQuery(DbConstants.POSSIBLE_MEETING_FIND_BY_DAY_AND_TIMEZONE, parameters);
	}

	public List<PossibleMeeting> findPossibleMeetingByMeetingDayAndTimezone(Long meetingId, Long dayId,
			Long timezoneId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("meetingId", meetingId);
		parameters.put("dayId", dayId);
		parameters.put("timezoneId", timezoneId);
		return namedQuery(DbConstants.POSSIBLE_MEETING_FIND_BY_MEETING_DAY_AND_TIMEZONE, parameters);
	}
}
