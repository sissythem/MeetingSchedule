package gr.demokritos.meetingscheduler.business.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.business.dto.AvailabilityDto;
import gr.demokritos.meetingscheduler.business.mappers.AvailabilityMapper;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Availability;
import gr.demokritos.meetingscheduler.datalayer.repositories.AvailabilityRepository;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;

/**
 * Session Bean implementation class AvailabilityBean
 */
@Stateless
@LocalBean
public class AvailabilityBean {
	private AvailabilityMapper availabilityMapper = new AvailabilityMapper();

	@Inject
	@JpaRepo
	private AvailabilityRepository availabilityRepository;

	public AvailabilityBean() {

	}
	
	public void saveAvailability(AvailabilityDto availabilityDto) {
		Availability availability = availabilityMapper.convertAvailabilityDtoToAvailability(availabilityDto);
		availabilityRepository.add(availability);
	}
	
	public void updateAvailability(AvailabilityDto availabilityDto) {
		Availability availability = availabilityMapper.convertAvailabilityDtoToAvailability(availabilityDto);
		availabilityRepository.update(availability);
	}

	public void removeAvailability(AvailabilityDto availabilityDto) {
		Availability availability = availabilityMapper.convertAvailabilityDtoToAvailability(availabilityDto);
		availabilityRepository.remove(availability);
	}
	
	public List<AvailabilityDto> getAllAvailabilities() {
		return getAvailabilityDtos(availabilityRepository.findAllAvailabilities());
	}

	public AvailabilityDto getAvailabilityById(Long id) {
		return availabilityMapper.convertAvailabilityToAvailabilityDto(availabilityRepository.findAvailabilityById(id));
	}
	
	public List<AvailabilityDto> getAvailabilitiesByMember(Long memberId) {
		return getAvailabilityDtos(availabilityRepository.findAvailabilityByMember(memberId));
	}
	
	public List<AvailabilityDto> getAvailabilitiesByDay(Long dayId) {
		return getAvailabilityDtos(availabilityRepository.findAvailabilityByDay(dayId));
	}
	
	public List<AvailabilityDto> getAvailabilitiesByTimezone(Long timezoneId) {
		return getAvailabilityDtos(availabilityRepository.findAvailabilityByTimezone(timezoneId));
	}
	
	public List<AvailabilityDto> getAvailabilitiesByDayAndTimezone(Long dayId, Long timezoneId) {
		return getAvailabilityDtos(availabilityRepository.findAvailabilityByDayAndTimezone(dayId, timezoneId));
	}
	
	public List<AvailabilityDto> getAvailabilitiesByMemberAndDay(Long memberId, Long dayId) {
		return getAvailabilityDtos(availabilityRepository.findAvailabilityByMemberAndDay(memberId, dayId));
	}
	
	public List<AvailabilityDto> getAvailabilityByMemberDayAndTimezone(Long memberId, Long dayId, Long timezoneId) {
		return getAvailabilityDtos(availabilityRepository.findAvailabilityByMemberDayAndTimezone(memberId, dayId, timezoneId));
	}
	
	private List<AvailabilityDto> getAvailabilityDtos(List<Availability> availabilities) {
		List<AvailabilityDto> availabilityDtos = new ArrayList<>();
		if (!CollectionUtils.isEmpty(availabilities)) {
			availabilities.forEach(availability -> {
				AvailabilityDto availabilityDto = availabilityMapper.convertAvailabilityToAvailabilityDto(availability);
				availabilityDtos.add(availabilityDto);
			});
		}
		return availabilityDtos;
	}

}
