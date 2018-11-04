package gr.demokritos.meetingscheduler.datalayer.repositories;

import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Timezone;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;
import org.apache.commons.collections4.CollectionUtils;

import javax.enterprise.context.Dependent;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JpaRepo
@Dependent
public class TimezoneRepository extends AbstractRepository<Timezone> {

    public TimezoneRepository() {
        super(DbConstants.TIMEZONE_TABLE);
    }

    public List<Timezone> findAllTimezones() {
        return namedQuery(DbConstants.TIMEZONE_FIND_ALL, null);
    }

    public List<Timezone> findAllTimezones(String sortString) {
        return jpqlQuery("SELECT t FROM Timezone t " + sortString);
    }

    public Timezone findDayById(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        List<Timezone> timezones = namedQuery(DbConstants.TIMEZONE_FIND_ID, parameters);
        if (!CollectionUtils.isEmpty(timezones)) {
            return timezones.get(0);
        }
        return null;
    }

    public List<Timezone> findTimezoneByStartTime(LocalTime startTime) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startTime", startTime);
        return namedQuery(DbConstants.TIMEZONE_FIND_START_TIME, parameters);
    }

    public List<Timezone> findTimezoneByEndTime(LocalTime endTime) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("endTime", endTime);
        return namedQuery(DbConstants.TIMEZONE_FIND_END_TIME, parameters);
    }

    public Timezone findTimezoneByStartTimeAndEndTime(LocalTime startTime, LocalTime endTime) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        List<Timezone> timezones = namedQuery(DbConstants.TIMEZONE_FIND_START_TIME_AND_END_TIME, parameters);
        if (!CollectionUtils.isEmpty(timezones)) {
            return timezones.get(0);
        }
        return null;
    }

}
