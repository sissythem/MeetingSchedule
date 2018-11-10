package gr.demokritos.meetingscheduler.business.beans;

import gr.demokritos.meetingscheduler.business.dto.TimezoneDto;
import gr.demokritos.meetingscheduler.business.mappers.TimezoneMapper;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Timezone;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;
import gr.demokritos.meetingscheduler.datalayer.repositories.TimezoneRepository;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class TimezoneBean
 */
@Stateless
@LocalBean
public class TimezoneBean {

    private TimezoneMapper timezoneMapper = new TimezoneMapper();

    @Inject
    @JpaRepo
    private TimezoneRepository timezoneRepository;

    public TimezoneBean() {
    
    }

    public boolean timezoneExists(LocalTime startTime, LocalTime endTime) {
        return timezoneRepository.findTimezoneByStartTimeAndEndTime(startTime, endTime) != null;
    }

    public TimezoneDto addTimezone(TimezoneDto timezoneDto) {
        Timezone timezone = timezoneMapper.convertTimezoneDtoToTimezone(timezoneDto);
        timezone = timezoneRepository.add(timezone);
        timezoneDto = timezoneMapper.convertTimezoneToTimezoneDto(timezone);
        return timezoneDto;
    }

    public void updateTimezone(TimezoneDto timezoneDto) {
        Timezone timezone = timezoneMapper.convertTimezoneDtoToTimezone(timezoneDto);
        timezoneRepository.update(timezone);
    }

    public void removeTimezone(TimezoneDto timezoneDto) {
        Timezone timezone = timezoneMapper.convertTimezoneDtoToTimezone(timezoneDto);
        timezoneRepository.remove(timezone);
    }

    public List<TimezoneDto> getAllTimezones() {
        return getTimezoneDtos(timezoneRepository.findAllTimezones());
    }

    public List<TimezoneDto> getAllTimezones(String sortString) {
        return getTimezoneDtos(timezoneRepository.findAllTimezones(sortString));
    }

    public TimezoneDto getTimezoneById(Long id) {
        return timezoneMapper.convertTimezoneToTimezoneDto(timezoneRepository.findDayById(id));
    }

    public List<TimezoneDto> getTimezoneByStartTime(LocalTime startTime) {
        return getTimezoneDtos(timezoneRepository.findTimezoneByStartTime(startTime));
    }

    public List<TimezoneDto> getTimezoneByEndTime(LocalTime endTime) {
        return getTimezoneDtos(timezoneRepository.findTimezoneByEndTime(endTime));
    }

    public TimezoneDto getTimezoneByStartAndEndTime(LocalTime startTime, LocalTime endTime) {
        return timezoneMapper.convertTimezoneToTimezoneDto(timezoneRepository.findTimezoneByStartTimeAndEndTime(startTime, endTime));
    }

    private List<TimezoneDto> getTimezoneDtos(List<Timezone> timezones) {
        List<TimezoneDto> timezoneDtos= new ArrayList<>();
        if (!CollectionUtils.isEmpty(timezones)) {
            timezones.forEach(timezone -> {
                TimezoneDto timezoneDto = timezoneMapper.convertTimezoneToTimezoneDto(timezone);
                timezoneDtos.add(timezoneDto);
            });
        }
        return timezoneDtos;
    }

}
