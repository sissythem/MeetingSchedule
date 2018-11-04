package gr.demokritos.meetingscheduler.business.beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.mappers.MeetingMapper;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Meeting;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;
import gr.demokritos.meetingscheduler.datalayer.repositories.MeetingRepository;

/**
 * Session Bean implementation class MeetingBean
 */
@Stateless
@LocalBean
public class MeetingBean {

    private MeetingMapper meetingMapper = new MeetingMapper();

    @Inject
    @JpaRepo
    private MeetingRepository meetingRepository;

    public MeetingBean() {
    
    }
    
    public void addMeeting(MeetingDto meetingDto) {
    	Meeting meeting = meetingMapper.convertMeetingDtoToMeeting(meetingDto);
    	meetingRepository.add(meeting);
    }
    
    public void updateMeeting(MeetingDto meetingDto) {
    	Meeting meeting = meetingMapper.convertMeetingDtoToMeeting(meetingDto);
    	meetingRepository.update(meeting);
    }
    
    public void removeMeeting(MeetingDto meetingDto) {
    	Meeting meeting = meetingMapper.convertMeetingDtoToMeeting(meetingDto);
    	meetingRepository.remove(meeting);
    }
    
    public List<MeetingDto> getAllMeetings() {
    	return getMeetingDtos(meetingRepository.findAllMeetings());
    }

    public List<MeetingDto> getAllMeetings(String sortString) {
        return getMeetingDtos(meetingRepository.findAllMeetings(sortString));
    }

    public MeetingDto getMeetingById(Long id) {
    	return meetingMapper.convertMeetingToMeetingDto(meetingRepository.findMeetingById(id));
    }
    
    public MeetingDto getMeetingByName(String name) {
    	return meetingMapper.convertMeetingToMeetingDto(meetingRepository.findMeetingByName(name));
    }
    
    public List<MeetingDto> getMeetingsByDate(LocalDate date) {
    	return getMeetingDtos(meetingRepository.findMeetingsByDate(date));
    }
    
    public List<MeetingDto> getMeetingsByStartTime(LocalTime startTime) {
    	return getMeetingDtos(meetingRepository.findMeetingsByStartTime(startTime));
    }
    
    public List<MeetingDto> getMeetingsByEndTime(LocalTime endTime) {
    	return getMeetingDtos(meetingRepository.findMeetingsByEndTime(endTime));
    }
    
    public List<MeetingDto> getMeetingsByStatus(String completed) {
    	return getMeetingDtos(meetingRepository.findMeetingsByStatus(completed));
    }
    
    private List<MeetingDto> getMeetingDtos(List<Meeting> meetings) {
		List<MeetingDto> meetingDtos = new ArrayList<>();
		if (!CollectionUtils.isEmpty(meetings)) {
			meetings.forEach(meeting -> {
				MeetingDto meetingDto = meetingMapper.convertMeetingToMeetingDto(meeting);
				meetingDtos.add(meetingDto);
			});
		}
		return meetingDtos;
	}

}
