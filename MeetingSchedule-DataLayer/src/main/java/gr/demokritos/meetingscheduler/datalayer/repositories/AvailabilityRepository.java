package gr.demokritos.meetingscheduler.datalayer.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Availability;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@JpaRepo
@Dependent
public class AvailabilityRepository extends AbstractRepository<Availability> {

    public AvailabilityRepository() {
        super(DbConstants.AVAILABILITY_TABLE);
    }

    public List<Availability> findAllAvailabilities() {
        return namedQuery(DbConstants.AVAILABILITY_FIND_ALL, null);
    }

    public Availability findAvailabilityById(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        List<Availability> availabilities = namedQuery(DbConstants.AVAILABILITY_FIND_BY_ID, parameters);
        if (!CollectionUtils.isEmpty(availabilities)) {
            return availabilities.get(0);
        }
        return null;
    }

    public List<Availability> findAvailabilityByMember(Long memberId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("memberId", memberId);
        return namedQuery(DbConstants.AVAILABILITY_FIND_BY_MEMBER, parameters);
    }

    public List<Availability> findAvailabilityByDay(Long dayId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dayId", dayId);
        return namedQuery(DbConstants.AVAILABILITY_FIND_BY_DAY, parameters);
    }

    public List<Availability> findAvailabilityByTimezone(Long timezoneId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("timezoneId", timezoneId);
        return namedQuery(DbConstants.AVAILABILITY_FIND_BY_TIMEZONE, parameters);
    }

    public List<Availability> findAvailabilityByDayAndTimezone(Long dayId, Long timezoneId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dayId", dayId);
        parameters.put("timezoneId", timezoneId);
        return namedQuery(DbConstants.AVAILABILITY_FIND_BY_DAY_AND_TIMEZONE, parameters);
    }

    public List<Availability> findAvailabilityByMemberAndDay(Long memberId, Long dayId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dayId", dayId);
        parameters.put("memberId", memberId);
        return namedQuery(DbConstants.AVAILABILITY_FIND_BY_MEMBER_AND_DAY, parameters);
    }

    public List<Availability> findAvailabilityByMemberDayAndTimezone(Long memberId, Long dayId, Long timezoneId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dayId", dayId);
        parameters.put("memberId", memberId);
        parameters.put("timezoneId", timezoneId);
        return namedQuery(DbConstants.AVAILABILITY_FIND_BY_MEMBER_DAY_AND_TIMEZONE, parameters);
    }

    public List<Availability> findAvailabilityByMeeting(Long meetingId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("meetingId", meetingId);
        return namedQuery(DbConstants.AVAILABILITY_FIND_BY_MEETING, parameters);
    }

    public List<Availability> findAvailabilityByMeetingAndAvailability(Long meetingId, String availability) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("meetingId", meetingId);
        parameters.put("availability", availability);
        return namedQuery(DbConstants.AVAILABILITY_FIND_BY_MEETING_AND_AVAILABILITY, parameters);
    }

}
