package gr.demokritos.meetingscheduler.business.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.business.dto.MeetingMemberDto;
import gr.demokritos.meetingscheduler.business.mappers.MeetingMemberMapper;
import gr.demokritos.meetingscheduler.business.utils.Utilities;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.MeetingMember;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;
import gr.demokritos.meetingscheduler.datalayer.repositories.MeetingMemberRepository;

/**
 * Session Bean implementation class MeetingMemberBean
 */
@Stateless
@LocalBean
public class MeetingMemberBean {

    private MeetingMemberMapper meetingMemberMapper = new MeetingMemberMapper();

    @Inject
    @JpaRepo
    private MeetingMemberRepository meetingMemberRepository;

    public MeetingMemberBean() {
    
    }
    
    public void saveMeetingMember(MeetingMemberDto meetingMemberDto) {
    	MeetingMember meetingMember = meetingMemberMapper.convertMeetingMemberDtoToMeetingMember(meetingMemberDto);
    	meetingMemberRepository.add(meetingMember);
    }
    
    public void updateMeetingMember(MeetingMemberDto meetingMemberDto) {
    	MeetingMember meetingMember = meetingMemberMapper.convertMeetingMemberDtoToMeetingMember(meetingMemberDto);
    	meetingMemberRepository.update(meetingMember);
    }
    
    public void removeMeetingMember(MeetingMemberDto meetingMemberDto) {
		MeetingMember meetingMember = meetingMemberMapper.convertMeetingMemberDtoToMeetingMember(meetingMemberDto);
		meetingMemberRepository.remove(meetingMember);
	}
    
    public List<MeetingMemberDto> getAllMeetingMemberDto() {
    	return getMeetingMemberDtos(meetingMemberRepository.findAllMeetingMembers());
    }
    
    public List<MeetingMemberDto> getMeetingMemberByMeeting(Long meetingId) {
    	return getMeetingMemberDtos(meetingMemberRepository.findMeetingMemberByMeeting(meetingId));
    }
    
    public List<MeetingMemberDto> getMeetingMemberByMember(Long memberId) {
    	return getMeetingMemberDtos(meetingMemberRepository.findMeetingMemberByMember(memberId));
    }
    
    public List<MeetingMemberDto> getMeetingMemberByAttended(Boolean isAttended) {
    	return getMeetingMemberDtos(meetingMemberRepository.findMeetingMemberByAttended(Utilities.booleanToString(isAttended)));
    }
    
    public List<MeetingMemberDto> getMeetingMemberByMemberAndAttended(Long memberId, Boolean attended) {
    	return getMeetingMemberDtos(meetingMemberRepository.findMeetingMemberByMemberAndAttended(memberId, Utilities.booleanToString(attended)));
    }
    
    public List<MeetingMemberDto> getMeetingMemberByMeetingAndAttended(Long meetingId, Boolean attended) {
    	return getMeetingMemberDtos(meetingMemberRepository.findMeetingMemberByMeetingAndAttended(meetingId, Utilities.booleanToString(attended)));
    }
    
    public List<MeetingMemberDto> getMeetingMemberByMemberAndMeeting(Long memberId, Long meetingId) {
    	return getMeetingMemberDtos(meetingMemberRepository.findMeetingMemberByMemberAndMeeting(memberId, meetingId));
    }
    
    public List<MeetingMemberDto> getMeetingMemberByMemberAndMeetingAndAttended(Long memberId, Long meetingId, Boolean attended) {
    	return getMeetingMemberDtos(meetingMemberRepository.findMeetingMemberByMeetingMemberAndAttended(meetingId, memberId, Utilities.booleanToString(attended)));
    }
    
    private List<MeetingMemberDto> getMeetingMemberDtos(List<MeetingMember> meetingMembers) {
		List<MeetingMemberDto> meetingMemberDtos = new ArrayList<>();
		if (!CollectionUtils.isEmpty(meetingMembers)) {
			meetingMembers.forEach(meetingMember -> {
				MeetingMemberDto meetingMemberDto = meetingMemberMapper.convertMeetingMemberToMeetingMemberDto(meetingMember);
				meetingMemberDtos.add(meetingMemberDto);
			});
		}
		return meetingMemberDtos;
	}

}
