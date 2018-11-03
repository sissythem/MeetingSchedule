package gr.demokritos.meetingscheduler.business.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.business.dto.DayDto;
import gr.demokritos.meetingscheduler.business.mappers.DayMapper;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Day;
import gr.demokritos.meetingscheduler.datalayer.repositories.DayRepository;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;

/**
 * Session Bean implementation class DayBean
 */
@Stateless
@LocalBean
public class DayBean {
    private DayMapper dayMapper = new DayMapper();

    @Inject
    @JpaRepo
    private DayRepository dayRepository;

    public DayBean() {
    
    }
    
    public void saveDay(DayDto dayDto) {
    	Day day = dayMapper.convertDayDtoToDay(dayDto);
    	dayRepository.add(day);
    }
    
    public void updateDay(DayDto dayDto) {
    	Day day = dayMapper.convertDayDtoToDay(dayDto);
    	dayRepository.update(day);
    }
    
    public void removeDay(DayDto dayDto) {
    	Day day = dayMapper.convertDayDtoToDay(dayDto);
    	dayRepository.remove(day);
    }
    
    public List<DayDto> getAllDays() {
    	return getDayDtos(dayRepository.findAllDays());
    }
    
    public DayDto getDayById(Long id) {
    	return dayMapper.convertDayToDayDto(dayRepository.findDayById(id));
    }
    
    public DayDto getDayByDate(LocalDate date) {
    	return dayMapper.convertDayToDayDto(dayRepository.findDayByDate(date));
    }
    
    public DayDto getDayByNameAndDate(String name, LocalDate date) {
    	return dayMapper.convertDayToDayDto(dayRepository.findDayByNameAndDate(name, date));
    }
    
    public List<DayDto> getDaysByName(String name) {
    	return getDayDtos(dayRepository.findDayByName(name));
    }
    
    private List<DayDto> getDayDtos(List<Day> days) {
		List<DayDto> dayDtos = new ArrayList<>();
		if (!CollectionUtils.isEmpty(days)) {
			days.forEach(day -> {
				DayDto dayDto = dayMapper.convertDayToDayDto(day);
				dayDtos.add(dayDto);
			});
		}
		return dayDtos;
	}

}
